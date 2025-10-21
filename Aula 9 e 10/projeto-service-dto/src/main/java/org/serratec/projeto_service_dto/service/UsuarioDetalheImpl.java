package org.serratec.projeto_service_dto.service;

import org.serratec.projeto_service_dto.entity.Usuario;
import org.serratec.projeto_service_dto.exception.EmailException;
import org.serratec.projeto_service_dto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalheImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		if (usuario == null) {
			throw new EmailException("Usuário não encontrado");
		}
		
		return usuario;
	}

}
