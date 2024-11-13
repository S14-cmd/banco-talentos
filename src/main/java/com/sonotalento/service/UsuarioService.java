package com.sonotalento.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sonotalento.model.Usuario;
import com.sonotalento.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario atualizar(Integer id, Usuario usuario) {
		Usuario usuarioSalvo = buscarPeloCodigo(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return this.repository.save(usuarioSalvo);
	}
	
	private Usuario buscarPeloCodigo(Integer id) {
		Usuario usuarioSalvo = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));;
		return usuarioSalvo;
	}
	
}
