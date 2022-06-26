package com.pizzaria.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pizzaria.app.dto.UsuarioDto;

import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50)
  private String nome;

  @Column(length = 50)
  private String email;

  private String senha;

  public Usuario(String nome, String email, String senha) {
    this.setNome(nome);
    this.setEmail(email);
    this.setSenha(senha);
  }

  public UsuarioDto converterDto() {
    return new UsuarioDto(this);
  }
}
