package com.sonotalento.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sonotalento.model.Empresa;
import com.sonotalento.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repository;

	public Empresa atualizar(Long id, Empresa empresa) {
		Empresa empresaSalvo = buscarPeloCodigo(id);
		BeanUtils.copyProperties(empresa, empresaSalvo, "id");
		return this.repository.save(empresaSalvo);
	}
	
	private Empresa buscarPeloCodigo(Long id) {
		Empresa empresaSalvo = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));;
		return empresaSalvo;
	}
}
