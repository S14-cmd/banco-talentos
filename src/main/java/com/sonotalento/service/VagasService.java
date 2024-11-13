package com.sonotalento.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sonotalento.model.Vagas;
import com.sonotalento.repository.VagasRepository;

@Service
public class VagasService {

	@Autowired
	private VagasRepository repository;

	public Vagas atualizar(Integer id, Vagas vagas) {
		Vagas vagasSalvo = buscarPeloCodigo(id);
		BeanUtils.copyProperties(vagas, vagasSalvo, "id");
		return this.repository.save(vagasSalvo);
	}
	
	private Vagas buscarPeloCodigo(Integer id) {
		Vagas vagasSalvo = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));;
		return vagasSalvo;
	}
}
