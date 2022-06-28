package com.pizzaria.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pizzaria.app.dto.UsuarioForm;
import com.pizzaria.app.models.Usuario;
import com.pizzaria.app.repositories.UsuarioRepository;

@Service
public class UsuarioService {
  
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder encoder;

  public Usuario save(UsuarioForm usuarioForm) {
    usuarioForm.setSenha(encoder.encode(usuarioForm.getSenha()));
    Usuario usuario = usuarioRepository.save(usuarioForm.converter());
    return usuario;
  }

  public Boolean validateUniqueEmail(String email) {
    return !usuarioRepository.findByEmail(email).isPresent();
  }

  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }
}