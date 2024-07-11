package com.mole.common.dto.auth;

import lombok.Data;

import java.util.Date;

@Data
public class AdminResult {
	private boolean success;
	private Data data;

	@lombok.Data
	public static class Data {
		private String avatar;
		private String username;
		private String nickname;
		private String[] roles;
		private String accessToken;
		private String refreshToken;
		private Date expires;
	}
}