package org.serratec.projeto_service_dto.repository;

import org.serratec.projeto_service_dto.entity.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository  extends JpaRepository<UsuarioPerfil, Long> {

}
