package com.pizzaria.app.dto;

import com.pizzaria.app.models.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
  private String nome;
  private String email;

  public UsuarioDto(Usuario usuario) {
    this.setNome(usuario.getNome());
    this.setEmail(usuario.getEmail());
  }
}