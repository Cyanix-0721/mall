package com.mole.auth.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mole.auth.utils.JwtUtil;
import com.mole.common.client.user.UserClient;
import com.mole.common.dto.auth.AdminResult;
import com.mole.common.dto.auth.LoginRequest;
import com.mole.common.dto.auth.RefreshTokenResult;
import com.mole.common.entity.Response;
import com.mole.common.entity.user.Admin;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "Admin Login management")
public class AdminLoginController {

	private final static long ACCESS_TTL = 1000 * 60 * 60 * 24L;
	private final static long REFRESH_TTL = 1000 * 60 * 60 * 24 * 30L;
	private final UserClient userClient;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final JwtUtil jwtUtilAccess = new JwtUtil(ACCESS_TTL);
	private final JwtUtil jwtUtilFresh = new JwtUtil(REFRESH_TTL);
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@PostMapping("/login")
	@Operation(summary = "Login admin")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login successful, returns the Admin data"),
			@ApiResponse(responseCode = "404", description = "Admin not found, returns an error message"),
			@ApiResponse(responseCode = "403", description = "Admin is forbidden, returns an error message"),
			@ApiResponse(responseCode = "401", description = "Invalid password, returns an error message")
	})

	public ResponseEntity<Response<AdminResult>> loginAdmin(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		// 通过用户名查找用户
		ResponseEntity<Response<Admin>> adminEntity = userClient.findAdminByUsername(username);
		// 如果用户不存在，返回404错误
		if (adminEntity == null) {
			return ResponseEntity.status(404).body(Response.error("Admin not found"));
		}
		Admin admin = Objects.requireNonNull(adminEntity.getBody()).getData();
		// 如果用户状态为0，返回403错误
		if (admin.getStatus() == 0) {
			return ResponseEntity.status(403).body(Response.error("Admin is forbidden"));
		}

		// 如果密码匹配，进行以下操作
		if (passwordEncoder.matches(password, admin.getPassword())) {
			System.out.println("=============密码匹配成功=============");
			// 生成JWT令牌
			Map<String, Object> claims = new HashMap<>();
			claims.put("username", username);
			// 生成accessToken和refreshToken
			System.out.println("=============Token=============");
			String accessToken = jwtUtilAccess.generateToken(username, claims);
			System.out.println("accessToken: " + accessToken);
			String refreshToken = jwtUtilFresh.generateToken(username, claims);
			System.out.println("refreshToken: " + refreshToken);

			// 创建UserResult对象
			AdminResult adminResult = new AdminResult();
			adminResult.setSuccess(true);
			AdminResult.Data data = new AdminResult.Data();
			data.setAvatar(admin.getIcon());
			data.setUsername(admin.getUsername());
			data.setNickname(admin.getNickname());
			String[] roles = {"admin", String.valueOf(admin.getStatus())};
			data.setRoles(roles);
			// 使用用户等级ID查找用户等级名称
			data.setAccessToken(accessToken);
			data.setRefreshToken(refreshToken);
			data.setExpires(new Date(System.currentTimeMillis() + ACCESS_TTL));
			System.out.println("=============DATA=============");
			System.out.println("data: " + data);
			adminResult.setData(data);

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
			return ResponseEntity.ok(Response.ok(adminResult));
		} else {
			// 如果密码不匹配，返回401错误
			return ResponseEntity.status(401).body(Response.error("Unauthorized"));
		}
	}

	@PostMapping("/register")
	@Operation(summary = "Register admin")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member registered"),
			@ApiResponse(responseCode = "409", description = "Username already exists")
	})
	public ResponseEntity<Response<Admin>> registerAdmin(String username, String password) {
		try {
			userClient.findAdminByUsername(username);
			return ResponseEntity.status(409).body(Response.error("Username already exists"));
		} catch (FeignException e) {
			if (e.status() != 404) {
				throw e;
			}
		}

		Admin newAdmin = new Admin();
		newAdmin.setUsername(username);
		newAdmin.setPassword(password);

		ResponseEntity<Response<Admin>> createdAdmin = userClient.createAdmin(newAdmin);
		return ResponseEntity.status(201).body(Response.ok(Objects.requireNonNull(createdAdmin.getBody()).getData()));
	}

	@PostMapping("/refresh-token")
	@Operation(summary = "Refresh token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
			@ApiResponse(responseCode = "401", description = "Invalid token")
	})
	public ResponseEntity<Response<RefreshTokenResult>> refreshTokenAdmin(
			@RequestHeader("Authorization")
			@Parameter(description = "Use refreshToken to refreshed")
			String refreshToken) {
		if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
			return ResponseEntity.status(401).body(Response.error("Invalid token"));
		}
		try {
			// 验证refreshToken是否过期
			DecodedJWT decodedJWT = jwtUtilFresh.validateToken(refreshToken);
			System.out.println("refreshToken: " + refreshToken);
			System.out.println("Decoded refreshToken: " + decodedJWT.getToken());

			// 从Redis中获取refreshToken
			ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
			String storedRefreshToken = ops.get(decodedJWT.getSubject() + ":refresh");
			System.out.println("Stored refreshToken: " + storedRefreshToken);

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
	}
}