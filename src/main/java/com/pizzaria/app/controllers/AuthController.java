package com.pizzaria.app.controllers;

import javax.validation.Valid;

import com.pizzaria.app.dto.MensagemErroDto;
import com.pizzaria.app.dto.TokenDto;
import com.pizzaria.app.dto.AuthForm;
import com.pizzaria.app.services.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

  @Autowired
	private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<?> authentication(@RequestBody @Valid AuthForm payload) {
    
    UsernamePasswordAuthenticationToken dadosLogin = payload.converter();

    try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.generateToken(authentication);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(new MensagemErroDto("Usuário/senha inválido"));
		}
  }
}
