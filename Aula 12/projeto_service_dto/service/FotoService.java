package org.serratec.projeto_service_dto.service;

import java.io.IOException;
import java.util.Optional;

import org.serratec.projeto_service_dto.entity.Foto;
import org.serratec.projeto_service_dto.entity.Funcionario;
import org.serratec.projeto_service_dto.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;

	public Foto inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setFuncionario(funcionario);
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());

		return fotoRepository.save(foto);
	}

	@Transactional
	public Foto buscarPorIdFuncionario(Long id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);

		Optional<Foto> foto = fotoRepository.findByFuncionario(funcionario);

		if (!foto.isPresent()) {
			return null;
		} else {
			return foto.get();
		}

	}

}
