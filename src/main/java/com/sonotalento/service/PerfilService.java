package com.sonotalento.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sonotalento.model.Perfil;
import com.sonotalento.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository repository;

	public Perfil atualizar(Integer id, Perfil perfil) {
		Perfil perfilSalvo = buscarPeloCodigo(id);
		BeanUtils.copyProperties(perfil, perfilSalvo, "id");
		return this.repository.save(perfilSalvo);
	}
	
	private Perfil buscarPeloCodigo(Integer id) {
		Perfil perfilSalvo = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));;
		return perfilSalvo;
	}
}
