package org.serratec.projeto_service_dto.repository;

import java.util.Optional;

import org.serratec.projeto_service_dto.entity.Foto;
import org.serratec.projeto_service_dto.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository  extends JpaRepository<Foto, Long> {

	public Optional<Foto> findByFuncionario(Funcionario funcionario);
}
