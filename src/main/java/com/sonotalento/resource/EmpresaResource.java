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
import com.sonotalento.model.Empresa;
import com.sonotalento.repository.EmpresaRepository;
import com.sonotalento.service.EmpresaService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository repository;
	
	@Autowired
	private EmpresaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("/{cnpj}")
	public ResponseEntity<Empresa> buscarPeloCodigo(@PathVariable Long cnpj) {
		return this.repository.findById(cnpj)
				  .map(empresa -> ResponseEntity.ok(empresa))
				  .orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{cnpj}")
	public ResponseEntity<Empresa> atualizar(@PathVariable Long cnpj, @RequestBody Empresa empresa) {
		Empresa empresaSalvo = service.atualizar(cnpj, empresa);
		return ResponseEntity.ok(empresaSalvo);
	}
	
	@DeleteMapping("/{cnpj}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cnpj) {
		repository.deleteById(cnpj);
	}
	
	@PostMapping
	public ResponseEntity<Empresa> inserir(@RequestBody Empresa empresa, HttpServletResponse response) {
		Empresa empresaSalvo = repository.save(empresa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, empresaSalvo.getCnpj()));
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalvo);
	}
	
	@GetMapping
	public List<Empresa> listar() {
		return repository.findAll();
	}
}
