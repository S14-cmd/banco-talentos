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
import com.sonotalento.model.Vagas;
import com.sonotalento.repository.VagasRepository;
import com.sonotalento.service.VagasService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/vagas")
public class VagasResource {
	
	@Autowired
	private VagasRepository repository;
	
	@Autowired
	private VagasService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PutMapping("/{id}")
	public ResponseEntity<Vagas> atualizar(@PathVariable Integer id, @RequestBody Vagas vagas) {
		Vagas vagasSalvo = service.atualizar(id, vagas);
		return ResponseEntity.ok(vagasSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vagas> buscarPeloCodigo(@PathVariable Integer id) {
		return this.repository.findById(id)
				  .map(vaga -> ResponseEntity.ok(vaga))
				  .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Vagas> inserir(@RequestBody Vagas vagas, HttpServletResponse response) {
		Vagas vagasSalvo = repository.save(vagas);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, vagasSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(vagasSalvo);
	}
	
	@GetMapping
	public List<Vagas> listar() {
		return repository.findAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}
