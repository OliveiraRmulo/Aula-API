package org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Proprietario;
import org.serratec.backend.entity.Veiculo;
import org.serratec.backend.repository.ProprietarioRepository;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private ProprietarioRepository proprietarioRepository;

	@GetMapping
	public ResponseEntity<List<Veiculo>> listar() {
		return ResponseEntity.ok(veiculoRepository.findAll());
	}

	@Operation(summary = "Listar veículo por ID", description = "Retorna um veículo específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso."),
			@ApiResponse(responseCode = "404", description = "Veículo não encontrado para o ID fornecido."),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
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
		
		Proprietario proprietario = proprietarioRepository.findById(veiculo.getProprietario().getId())
				.orElseThrow(() -> new RuntimeException("Proprietário não encontrado com o ID: " + veiculo.getProprietario().getId()));
		
		veiculo.setProprietario(proprietario);
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
