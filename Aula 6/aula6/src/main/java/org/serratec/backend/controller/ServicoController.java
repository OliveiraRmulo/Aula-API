package org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Servico;
import org.serratec.backend.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

	@Autowired
	private ServicoRepository servicoRepository;

	@GetMapping
	public ResponseEntity<List<Servico>> listar() {
		return ResponseEntity.ok(servicoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
		Optional<Servico> servico = servicoRepository.findById(id);

		if (servico.isPresent()) {
			return ResponseEntity.ok(servico.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico criar(@Valid @RequestBody Servico servico) {
		return servicoRepository.save(servico);
	}
	
	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Servico> criarLista(@Valid @RequestBody List<Servico> servicos) {
		return servicoRepository.saveAll(servicos);
	}
}
