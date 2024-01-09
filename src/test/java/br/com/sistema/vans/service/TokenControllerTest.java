package br.com.sistema.vans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

	@Mock
	TokenService service;
	
	@Test
	void testarGeracaoDoToken() {
		String secret = "123456";
		Instant data = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
		
		var algoritmo = Algorithm.HMAC256(secret);
		 
		
		when(service.gerarToken()).thenReturn(JWT.create()
				.withIssuer("API Sistemas de Vans")
				.withExpiresAt(data)
				.sign(algoritmo));
		
		String tokenGerado = service.gerarToken();
		
        String tokenEsperado = JWT.create()
                .withIssuer("API Sistemas de Vans")
                .withExpiresAt(data)
                .sign(algoritmo);
		
        assertEquals(tokenEsperado, tokenGerado);
	}

}
