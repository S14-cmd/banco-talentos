package com.sonotalento.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sonotalento.event.RecursoCriadoEvent;
import com.sonotalento.model.Candidato;
import com.sonotalento.repository.CandidatoRepository;
import com.sonotalento.service.CandidatoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/candidatos")
public class CandidatoResource {
	
	@Autowired
	private CandidatoRepository repository;
	
	@Autowired
	private CandidatoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PutMapping("/{id}")
	public ResponseEntity<Candidato> atualizar(@PathVariable Integer id, @RequestBody Candidato candidato) {
		Candidato candidatoSalvo = service.atualizar(id, candidato);
		return ResponseEntity.ok(candidatoSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Candidato> buscarPeloCodigo(@PathVariable Integer id) {
		return this.repository.findById(id)
				  .map(candidato -> ResponseEntity.ok(candidato))
				  .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Candidato> inserir(@RequestBody Candidato candidato, HttpServletResponse response) {
		Candidato candidatoSalvo = repository.save(candidato);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, candidatoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(candidatoSalvo);
	}
	
	@GetMapping
	public List<Candidato> listar() {
		return repository.findAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
}
