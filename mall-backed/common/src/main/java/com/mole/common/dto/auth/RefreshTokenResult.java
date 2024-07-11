package com.mole.common.dto.auth;

import lombok.Data;

import java.util.Date;

@Data
public class RefreshTokenResult {
	private boolean success;
	private Data data;

	@lombok.Data
	public static class Data {
		private String accessToken;
		private String refreshToken;
		private Date expires;
	}
}