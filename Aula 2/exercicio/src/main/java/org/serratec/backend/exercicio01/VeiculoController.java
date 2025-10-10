package org.serratec.backend.exercicio01;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

	private final List<Veiculo> veiculos = new ArrayList<>();
	private Long idCounter = 1L;

	@PostMapping
	public Veiculo adicionar(@RequestBody Veiculo veiculo) {
		veiculo.setId(idCounter++);
		veiculos.add(veiculo);
		return veiculo;
	}

	@GetMapping
	public List<Veiculo> listar() {
		return veiculos;
	}

	@GetMapping("/{id}")
	public Veiculo buscar(@PathVariable Long id) {
		return veiculos.stream().filter(v -> v.getId().equals(id)).findFirst()
				.orElseThrow(() -> new NoSuchElementException("Veículo não encontrado"));
	}

	@PutMapping("/{id}")
	public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo novo) {
		for (Veiculo v : veiculos) {
			if (v.getId().equals(id)) {
				v.setMarca(novo.getMarca());
				v.setModelo(novo.getModelo());
				return v;
			}
		}
		throw new NoSuchElementException("Veículo não encontrado");
	}

	@DeleteMapping("/{id}")
	public String remover(@PathVariable Long id) {
		boolean removido = veiculos.removeIf(v -> v.getId().equals(id));
		if (removido) {
			return "Veículo removido com sucesso";
		}
		throw new NoSuchElementException("Veículo não encontrado");
	}
}