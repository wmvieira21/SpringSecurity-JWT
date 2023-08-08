package com.vieira.productJWT.service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vieira.productJWT.domain.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);

			String token = JWT.create().withIssuer("auth-product-api").withSubject(user.getLogin())
					.withExpiresAt(getExpirationDate()).sign(algorithm);

			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error as generating Token");
		}
	}

	public String validadeToken(String token) {
		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("auth-product-api").build().verify(token).getSubject();

		} catch (JWTVerificationException e) {
			return "";
		}
	}

	private Instant getExpirationDate() {
		return Instant.now().plus(Duration.ofHours(2)).atOffset(ZoneOffset.of("-03:00")).toInstant();
	}
}
