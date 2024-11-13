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
import com.sonotalento.model.Usuario;
import com.sonotalento.repository.UsuarioRepository;
import com.sonotalento.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = service.atualizar(id, usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Integer id) {
		return this.repository.findById(id)
				  .map(usuario -> ResponseEntity.ok(usuario))
				  .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = repository.save(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@GetMapping
	public List<Usuario> listar() {
		return repository.findAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}
