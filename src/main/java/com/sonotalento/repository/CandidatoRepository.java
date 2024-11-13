package com.sonotalento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonotalento.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

}
