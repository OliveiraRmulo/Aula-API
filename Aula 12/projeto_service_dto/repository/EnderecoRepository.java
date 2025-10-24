package org.serratec.projeto_service_dto.repository;

import org.serratec.projeto_service_dto.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public Endereco findByCep(String cep);
}
