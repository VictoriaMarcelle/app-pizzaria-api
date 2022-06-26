package com.pizzaria.app.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pizzaria.app.dto.MensagemErroDto;
import com.pizzaria.app.dto.UsuarioForm;
import com.pizzaria.app.models.Usuario;
import com.pizzaria.app.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @GetMapping
  public List<Usuario> getAll() {
    return usuarioService.findAll();
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> save(@RequestBody @Valid UsuarioForm payload, UriComponentsBuilder uriBuilder) {
    if(usuarioService.validateUniqueEmail(payload.getEmail())) {
      Usuario usuario = usuarioService.save(payload);
      URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

      return ResponseEntity.created(uri).body(usuario.converterDto()); 
    }

    return ResponseEntity.badRequest().body(new MensagemErroDto("Email já cadastrado"));
  }

}
