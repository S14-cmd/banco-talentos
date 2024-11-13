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
import com.sonotalento.model.Perfil;
import com.sonotalento.repository.PerfilRepository;
import com.sonotalento.service.PerfilService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/perfil")
public class PerfilResource {
	
	@Autowired
	private PerfilRepository repository;

	@Autowired
	private PerfilService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PutMapping("/{id}")
	public ResponseEntity<Perfil> atualizar(@PathVariable Integer id, @RequestBody Perfil perfil) {
		Perfil perfilSalvo = service.atualizar(id, perfil);
		return ResponseEntity.ok(perfilSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Perfil> buscarPeloCodigo(@PathVariable Integer id) {
		return this.repository.findById(id)
				  .map(perfil -> ResponseEntity.ok(perfil))
				  .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Perfil> inserir(@RequestBody Perfil perfil, HttpServletResponse response) {
		Perfil perfilSalvo = repository.save(perfil);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, perfilSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(perfilSalvo);
	}
	
	@GetMapping
	public List<Perfil> listar() {
		return repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
}
