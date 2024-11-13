package com.sonotalento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonotalento.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
