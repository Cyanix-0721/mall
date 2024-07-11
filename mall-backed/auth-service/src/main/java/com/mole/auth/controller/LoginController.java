package com.mole.auth.controller;

import com.mole.auth.utils.JwtUtil;
import com.mole.common.client.user.UserClient;
import com.mole.common.dto.auth.LoginRequest;
import com.mole.common.dto.auth.UserResult;
import com.mole.common.dto.auth.password;
import com.mole.common.entity.Response;
import com.mole.common.entity.user.Member;
import com.mole.common.entity.user.MemberLevel;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@Tag(name = "Login management")
public class LoginController {

	private final static long ACCESS_TTL = 1000 * 60 * 60 * 24L;
	private final static long REFRESH_TTL = 1000 * 60 * 60 * 24 * 30L;
	private final UserClient userClient;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final JwtUtil jwtUtilAccess = new JwtUtil(ACCESS_TTL);
	private final JwtUtil jwtUtilFresh = new JwtUtil(REFRESH_TTL);
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@PostMapping("/login")
	@Operation(summary = "Login member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login successful, returns the member data"),
			@ApiResponse(responseCode = "404", description = "Member not found, returns an error message"),
			@ApiResponse(responseCode = "403", description = "Member is forbidden, returns an error message"),
			@ApiResponse(responseCode = "401", description = "Invalid password, returns an error message")
	})

	public ResponseEntity<Response<UserResult>> login(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		// 通过用户名查找用户
		ResponseEntity<Response<Member>> memberEntity = userClient.findByUsername(username);
		System.out.println(memberEntity);
		// 如果用户不存在，返回404错误
		if (memberEntity == null) {
			return ResponseEntity.status(404).body(Response.error("Member not found"));
		}
		Member member = Objects.requireNonNull(memberEntity.getBody()).getData();
		// 如果用户状态为0，返回403错误
		if (member.getStatus() == 0) {
			return ResponseEntity.status(403).body(Response.error("Member is forbidden"));
		}

		// 如果密码匹配，进行以下操作
		if (passwordEncoder.matches(password, member.getPassword())) {
			System.out.println("=============密码匹配成功=============");
			// 生成JWT令牌
			Map<String, Object> claims = new HashMap<>();
			claims.put("username", username);
			claims.put("roles", member.getMemberLevelId());
			// 生成accessToken和refreshToken
			System.out.println("=============Token=============");
			String accessToken = jwtUtilAccess.generateToken(username, claims);
			System.out.println("accessToken: " + accessToken);
			String refreshToken = jwtUtilFresh.generateToken(username, claims);
			System.out.println("refreshToken: " + refreshToken);

			// 创建UserResult对象
			UserResult userResult = new UserResult();
			userResult.setSuccess(true);
			UserResult.Data data = new UserResult.Data();
			data.setAvatar(member.getIcon());
			data.setUsername(member.getUsername());
			data.setNickname(member.getNickname());
			// 使用用户等级ID查找用户等级名称
			ResponseEntity<Response<MemberLevel>> roles = userClient.findMemberLevelById(member.getMemberLevelId());
			data.setRoles(Objects.requireNonNull(roles.getBody()).getData().getLevelName());
			data.setAccessToken(accessToken);
			data.setRefreshToken(refreshToken);
			data.setExpires(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
			System.out.println("=============DATA=============");
			System.out.println("data: " + data);
			userResult.setData(data);

			// 将令牌存储到redis
			ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
			System.out.println("=============Storing to Redis=============");
			System.out.println("Key: " + username + ":access");
			System.out.println("Value: " + accessToken);
			System.out.println("Expiration: " + jwtUtilAccess.getTTL() + " milliseconds");

			assert accessToken != null;
			ops.set(username + ":access", accessToken, jwtUtilAccess.getTTL(), TimeUnit.MILLISECONDS);

			// 输出将要存储的数据
			System.out.println("Key: " + username + ":refresh");
			System.out.println("Value: " + refreshToken);
			System.out.println("Expiration: " + jwtUtilFresh.getTTL() + " milliseconds");
			assert refreshToken != null;
			ops.set(username + ":refresh", refreshToken, jwtUtilFresh.getTTL(), TimeUnit.MILLISECONDS);

			// 返回UserResult对象
			return ResponseEntity.ok(Response.ok(userResult));
		} else {
			// 如果密码不匹配，返回401错误
			return ResponseEntity.status(401).body(Response.error("Unauthorized"));
		}
	}

	@PostMapping("/register")
	@Operation(summary = "Register member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member registered"),
			@ApiResponse(responseCode = "409", description = "Username already exists")
	})
	public ResponseEntity<Response<Member>> register(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		try {
			userClient.findByUsername(username);
			return ResponseEntity.status(409).body(Response.error(409, "Username already exists"));
		} catch (FeignException e) {
			if (e.status() != 404) {
				throw e;
			}
		}

		Member newMember = new Member();
		newMember.setUsername(username);
		newMember.setPassword(password);        // 密码加密在 MemberController 进行

		ResponseEntity<Response<Member>> createdMember = userClient.create(newMember);
		return ResponseEntity.status(201).body(Response.ok(Objects.requireNonNull(createdMember.getBody()).getData()));
	}

	@PostMapping("/change-password")
	@Operation(summary = "Change member password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Password changed successfully"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "401", description = "Invalid old password")
	})
	public ResponseEntity<Response<Member>> changePassword(@RequestBody password passwordRequest, @RequestHeader("Username") String username) {
		String oldPassword = passwordRequest.getOldPassword();
		String newPassword = passwordRequest.getNewPassword();

		System.out.println(username);
		ResponseEntity<Response<Member>> memberEntity = userClient.findByUsername(username);
		if (memberEntity == null) {
			return ResponseEntity.status(404).body(Response.error("Member not found"));
		}

		Member member = Objects.requireNonNull(memberEntity.getBody()).getData();

		if (passwordEncoder.matches(oldPassword, member.getPassword())) {
			member.setPassword(passwordEncoder.encode(newPassword));
			ResponseEntity<Response<Member>> updatedMember = userClient.update(member);

			if (updatedMember != null) {
				return ResponseEntity.ok(Response.ok(Objects.requireNonNull(updatedMember.getBody()).getData()));
			} else {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} else {
			return ResponseEntity.status(401).body(Response.error("Invalid old password"));
		}
	}

/*	@PostMapping("/refresh-token")
	@Operation(summary = "Refresh token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
			@ApiResponse(responseCode = "401", description = "Invalid token")
	})
	public ResponseEntity<Response<RefreshTokenResult>> refreshToken(
			@RequestHeader("Authorization")
			@Parameter(description = "The old token that needs to be refreshed")
			String refreshToken) {
		try {
			// 验证refreshToken是否过期
			DecodedJWT decodedJWT = jwtUtilFresh.validateToken(refreshToken);

			// 从Redis中获取refreshToken
			ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
			String storedRefreshToken = ops.get(decodedJWT.getSubject() + ":refresh");

			// 验证从Redis中获取的refreshToken和传入的refreshToken是否一致
			if (!refreshToken.equals(storedRefreshToken)) {
				return ResponseEntity.status(401).body(Response.error("Invalid token"));
			}

			// 生成一个具有相同声明的新token(accessToken/refreshToken仅过期时间不同)
			Map<String, Object> claims = new HashMap<>(decodedJWT.getClaims());
			String newAccessToken = jwtUtilAccess.generateToken(decodedJWT.getSubject(), claims);

			// 创建RefreshTokenResult对象
			RefreshTokenResult refreshTokenResult = new RefreshTokenResult();
			refreshTokenResult.setSuccess(true);
			RefreshTokenResult.Data data = new RefreshTokenResult.Data();
			data.setAccessToken(newAccessToken);
			data.setRefreshToken(refreshToken);
			data.setExpires(new Date(System.currentTimeMillis() + jwtUtilFresh.getTTL()));
			refreshTokenResult.setData(data);

			return ResponseEntity.ok(Response.ok(refreshTokenResult));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(Response.error("Invalid token"));
		}
	}*/

	@PostMapping("/logout")
	@Operation(summary = "Logout member")
	public ResponseEntity<Response<String>> logout(@RequestHeader("Username") String username) {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.getOperations().delete(username + ":access");
		ops.getOperations().delete(username + ":refresh");

		return ResponseEntity.ok(Response.ok("成功登出"));
	}
}