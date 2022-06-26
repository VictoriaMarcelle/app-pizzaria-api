package com.pizzaria.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.pizzaria.app.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {

  @NotBlank(message = "Campo nome é obrigatório")
  @Length(min = 5, max = 50)
  private String nome;

  @NotBlank(message = "Campo e-mail é obrigatório")
  @Email(message = "Campo e-mail precisa ser válido")
  @Length(min = 5, max = 50, message = "Campo e-mail precisar ter entre 5 e 50 caracteres")
  private String email;

  @NotBlank(message = "Campo senha é obrigatório")
  @Length(min = 8, max = 50)
  private String senha;

  public UsuarioForm(Usuario usuario) {
    this.setNome(usuario.getNome());
    this.setEmail(usuario.getEmail());
  }

  public Usuario converter() {
		return new Usuario(this.getNome(), this.getEmail(), this.getSenha());
	}
}