package org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Veiculo;
import org.serratec.backend.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@GetMapping
	public ResponseEntity<List<Veiculo>> listar() {
		return ResponseEntity.ok(veiculoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);

		if (veiculo.isPresent()) {
			return ResponseEntity.ok(veiculo.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo criar(@Valid @RequestBody Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		veiculo.setId(id);
		veiculo = veiculoRepository.save(veiculo);

		return ResponseEntity.ok(veiculo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		veiculoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
