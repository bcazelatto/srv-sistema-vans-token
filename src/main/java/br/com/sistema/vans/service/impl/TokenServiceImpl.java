package br.com.sistema.vans.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.sistema.vans.exception.TokenException;
import br.com.sistema.vans.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{

	@Value("${token.secret}")
	private String secret;
	
	@Override
	public String gerarToken() {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API Sistemas de Vans")
					.withExpiresAt(dataExpiracao())
					.sign(algoritmo);
		} catch (TokenException e) {
			throw new TokenException(e.getMessage());
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
