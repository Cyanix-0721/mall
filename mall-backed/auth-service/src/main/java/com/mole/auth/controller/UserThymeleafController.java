package com.mole.auth.controller;

import com.mole.auth.utils.JwtUtil;
import com.mole.common.client.user.UserClient;
import com.mole.common.dto.auth.LoginRequest;
import com.mole.common.dto.auth.UserResult;
import com.mole.common.entity.Response;
import com.mole.common.entity.user.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/html")
public class UserThymeleafController {
	private final static long ACCESS_TTL = 1000 * 60 * 60 * 24L;
	private final JwtUtil jwtUtilAccess = new JwtUtil(ACCESS_TTL);
	private final UserClient userClient;
	private final LoginController loginController;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	@PostMapping("/login")
	@Operation(summary = "用户登录")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "登录成功，重定向到个人资料页面"),
			@ApiResponse(responseCode = "400", description = "登录失败，返回登录页面并显示错误消息")
	})
	public ModelAndView loginThymeleaf(@ModelAttribute LoginRequest loginRequest, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		ResponseEntity<Response<UserResult>> loginResult = loginController.login(loginRequest);

		if (loginResult.getStatusCode().is2xxSuccessful()) {
			// 登录成功，重定向到个人资料页面
			modelAndView.setViewName("redirect:/html/profile");
		} else {
			// 登录失败，返回登录页面并显示错误消息
			modelAndView.addObject("error", Objects.requireNonNull(loginResult.getBody()).getMsg());
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}

/*	@GetMapping("/register")
	public ModelAndView registerForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("member", new Member());
		modelAndView.addObject("error", "");
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute Member member) {
		ModelAndView modelAndView = new ModelAndView();
		String username = member.getUsername();
		// Check if the username is valid (only contains English letters, numbers and common symbols)
		if (!Pattern.matches("^[a-zA-Z0-9@.#$%^&*_+-=]*$", username)) {
			modelAndView.addObject("error", "invalid_username");
			modelAndView.setViewName("register");
			return modelAndView;
		}

		Member existingMember = memberService.findByUsername(username);
		if (existingMember != null) {
			// Username already exists
			modelAndView.addObject("error", "username_exists");
			modelAndView.setViewName("register");
			return modelAndView;
		} else {
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			memberService.create(member);

			// Generate JWT token
			Map<String, Object> claims = new HashMap<>();
			claims.put("username", username);
			String accessToken = jwtUtilAccess.generateToken(username, claims);

			// Set token in the Authorization header
			response.setHeader("Authorization", "Bearer " + accessToken);

			// Store the token in Redis
			ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
			ops.set(username + ":access", accessToken, jwtUtilAccess.getTTL(), TimeUnit.MILLISECONDS);

			modelAndView.setViewName("redirect:/html/members/profile");
			return modelAndView;
		}
	}*/

	@GetMapping("/profile")
	public ModelAndView getProfile(HttpServletRequest request) {
		String username = request.getHeader("user-info");
		ResponseEntity<Response<Member>> responseEntity = userClient.findByUsername(username);
		ModelAndView modelAndView = new ModelAndView();
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			modelAndView.addObject("member", Objects.requireNonNull(responseEntity.getBody()).getData());
		}
		modelAndView.setViewName("profile");
		return modelAndView;
	}

/*	@PostMapping("/update")
	public String updateMember(@ModelAttribute Member member) {
		ResponseEntity<Response<Member>> responseEntity = userClient.update(member.getId(), member);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return "redirect:/html/profile";
		} else {
			// handle error
			return "error";
		}
	}*/
}