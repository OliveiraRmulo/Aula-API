package org.serratec.aula4.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.aula4.domain.Aluno;
import org.serratec.aula4.repository.AlunoRepository;
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
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id){
		Optional<Aluno> aluno = alunoRepository.findById(id);
		
		if(aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno inserir(@Valid @RequestBody Aluno aluno){
		return alunoRepository.save(aluno);
	}
	
	@PutMapping("/{id}")
		public ResponseEntity<Aluno> atualizar(@Valid @RequestBody Aluno aluno ,@PathVariable Long id){
			if(!alunoRepository.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			
			aluno.setId(id);
			aluno = alunoRepository.save(aluno);
			return ResponseEntity.ok(aluno);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if(!alunoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		alunoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
