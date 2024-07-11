package com.mole.common.client.auth;

import com.mole.common.dto.auth.AdminResult;
import com.mole.common.dto.auth.LoginRequest;
import com.mole.common.dto.auth.UserResult;
import com.mole.common.dto.auth.password;
import com.mole.common.entity.Response;
import com.mole.common.entity.user.Admin;
import com.mole.common.entity.user.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("auth-service")
public interface LoginClient {

	@PostMapping("/login")
	ResponseEntity<Response<UserResult>> login(@RequestBody LoginRequest loginRequest);

	@PostMapping("/register")
	ResponseEntity<Response<Member>> register(@RequestBody LoginRequest loginRequest);

	@PostMapping("/change-password")
	ResponseEntity<Response<Member>> changePassword(@RequestBody password passwordRequest, @RequestHeader("Username") String username);

	@PostMapping("/logout")
	ResponseEntity<Response<String>> logout(@RequestHeader("user-info") String username);

	@PostMapping("/admin/login")
	ResponseEntity<Response<AdminResult>> loginAdmin(@RequestBody LoginRequest loginRequest);

	@PostMapping("/admin/register")
	ResponseEntity<Response<Admin>> registerAdmin(@RequestBody LoginRequest loginRequest);

	@PostMapping("/admin/logout")
	ResponseEntity<Response<String>> logoutAdmin(@RequestHeader("user-info") String username);
}
