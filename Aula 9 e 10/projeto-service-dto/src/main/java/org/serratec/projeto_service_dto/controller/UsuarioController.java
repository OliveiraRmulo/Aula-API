package org.serratec.projeto_service_dto.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projeto_service_dto.dto.UsuarioDTO;
import org.serratec.projeto_service_dto.dto.UsuarioInserirDTO;
import org.serratec.projeto_service_dto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarTodos() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		System.out.println("Usuário autenticado: " + userDetails.getUsername());
		
		return ResponseEntity.ok(usuarioService.listarTodos());
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuarioDTO.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
}
