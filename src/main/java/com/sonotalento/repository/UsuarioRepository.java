package com.sonotalento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonotalento.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
