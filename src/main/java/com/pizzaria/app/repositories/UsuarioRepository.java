package com.pizzaria.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.app.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByEmail(String email);
}
