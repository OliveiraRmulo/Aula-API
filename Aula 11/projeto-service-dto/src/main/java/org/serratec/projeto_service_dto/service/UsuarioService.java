package org.serratec.projeto_service_dto.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.serratec.projeto_service_dto.dto.UsuarioDTO;
import org.serratec.projeto_service_dto.dto.UsuarioInserirDTO;
import org.serratec.projeto_service_dto.entity.Perfil;
import org.serratec.projeto_service_dto.entity.Usuario;
import org.serratec.projeto_service_dto.entity.UsuarioPerfil;
import org.serratec.projeto_service_dto.exception.EmailException;
import org.serratec.projeto_service_dto.exception.SenhaException;
import org.serratec.projeto_service_dto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<UsuarioDTO> listarTodos() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario usuario : usuarios) {
			usuariosDTO.add(new UsuarioDTO(usuario));
		}
		
		return usuariosDTO;
		
		/*
		 * return usuarioRepository .findAll() .stream() .map(UsuarioDTO::new)
		 * .collect(Collectors.toList());
		 */
	}
	
	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioDTO) throws EmailException {
		
		if(!usuarioDTO.getSenha().equalsIgnoreCase(usuarioDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não conferem");
		}
		
		if (usuarioRepository.findByEmail(usuarioDTO.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));
		
		Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
		for (Perfil perfil : usuarioDTO.getPerfis()) {
			perfil = perfilService.buscarPorId(perfil.getId());
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
			usuarioPerfis.add(usuarioPerfil);
		}
		usuario.setUsuarioPerfis(usuarioPerfis);
		usuario = usuarioRepository.save(usuario);
		
		return new UsuarioDTO(usuario);
	}
}
