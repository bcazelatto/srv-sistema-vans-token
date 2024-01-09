package br.com.sistema.vans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.vans.service.TokenService;

@RestController
@RequestMapping("/gerar")
public class TokenController {
	
	@Autowired
	private TokenService service;
	
	@GetMapping
	public ResponseEntity<String> gerar() {

	return ResponseEntity.ok(service.gerarToken());
	
	}
	
}