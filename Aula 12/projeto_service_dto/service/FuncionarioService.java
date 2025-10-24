package org.serratec.projeto_service_dto.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.projeto_service_dto.dto.FuncionarioDTO;
import org.serratec.projeto_service_dto.entity.Funcionario;
import org.serratec.projeto_service_dto.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FotoService fotoService;
	
	public List<FuncionarioDTO> listar() {
		List<FuncionarioDTO> funcionariosDTOs= funcionarioRepository
				.findAll()
				.stream()
				.map(f -> adicionarImagemUri(f)).collect(Collectors.toList());
		return funcionariosDTOs;
	}
	
	public FuncionarioDTO buscarPorId(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		
		return adicionarImagemUri(funcionario.get());
	}
	
	public FuncionarioDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		funcionario = funcionarioRepository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return adicionarImagemUri(funcionario);
	}

	private FuncionarioDTO adicionarImagemUri(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId())
				.toUri();
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setNome(funcionario.getNome());
		funcionarioDTO.setSalario(funcionario.getSalario());
		funcionarioDTO.setDataNascimento(funcionario.getDataNascimento());
		funcionarioDTO.setUrl(uri.toString());
		
		return funcionarioDTO;
	}
}
