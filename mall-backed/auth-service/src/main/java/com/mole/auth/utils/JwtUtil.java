package com.mole.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class JwtUtil {

	private final static String SECRET_KEY = "A/.Sss324hah21asdjlkcxb.rt2-09z.sdfm!~";
	private final static Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
	private final long TTL;

	public JwtUtil(long ttl) {
		this.TTL = ttl;
	}

	public String generateToken(String username, Map<String, Object> claims) {
		return JWT.create()
				.withSubject(username)
				.withPayload(claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + TTL))
				.sign(algorithm);
	}

	public DecodedJWT validateToken(String token) {
		// 如果token由"Bearer "开头，去掉"Bearer "，只保留token
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		JWTVerifier verifier = JWT.require(algorithm).build();
		return verifier.verify(token);
	}

	public Map<String, Claim> getClaims(String token) {
		DecodedJWT decodedJWT = validateToken(token);
		return decodedJWT.getClaims();
	}
}