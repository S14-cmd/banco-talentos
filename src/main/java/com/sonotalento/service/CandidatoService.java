package com.sonotalento.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sonotalento.model.Candidato;
import com.sonotalento.repository.CandidatoRepository;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository repository;

	public Candidato atualizar(Integer id, Candidato candidato) {
		Candidato candidatoSalvo = buscarPeloCodigo(id);
		BeanUtils.copyProperties(candidato, candidatoSalvo, "id");
		return this.repository.save(candidatoSalvo);
	}
	
	private Candidato buscarPeloCodigo(Integer id) {
		Candidato candidatoSalvo = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));;
		return candidatoSalvo;
	}
}
