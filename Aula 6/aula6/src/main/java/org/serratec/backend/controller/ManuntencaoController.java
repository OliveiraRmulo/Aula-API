package org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Manutencao;
import org.serratec.backend.repository.ManutencaoRepository;
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
@RequestMapping("/manutencoes")
public class ManuntencaoController {
	
	@Autowired
	private ManutencaoRepository manutencaoRepository;
	
	@GetMapping
	public ResponseEntity<List<Manutencao>> listar() {
		return ResponseEntity.ok(manutencaoRepository.findAll());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable Long id) {
		Optional<Manutencao> manutencao = manutencaoRepository.findById(id);

		if (manutencao.isPresent()) {
			return ResponseEntity.ok(manutencao.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Manutencao criarManutencao(@Valid @RequestBody Manutencao manutencao) {
		return manutencaoRepository.save(manutencao);
	}

}
