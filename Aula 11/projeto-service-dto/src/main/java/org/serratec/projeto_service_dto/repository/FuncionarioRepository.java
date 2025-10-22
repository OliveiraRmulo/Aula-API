package org.serratec.projeto_service_dto.repository;

import java.util.List;

import org.serratec.projeto_service_dto.dto.FuncionarioSalarioDTO;
import org.serratec.projeto_service_dto.entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query("SELECT f FROM Funcionario f WHERE f.salario>= :salarioMinimo AND " + " f.salario <= :salarioMaximo")
	Page<Funcionario> buscarSalario(Double salarioMinimo, Double salarioMaximo, Pageable pageable);

	@Query("SELECT f FROM Funcionario f WHERE UPPER(f.nome) " + " like UPPER(CONCAT('%', :paramNome, '%'))")
	Page<Funcionario> buscarPorNome(String paramNome, Pageable pageable);

	@Query(value = "select date_part('year',age(now(), data_nascimento)) as idade,avg(salario) as mediaSalario,min(salario) as menorSalario,"
			+ " max(salario) as maiorSalario," + "count(*) as totalFuncionarios " + " from funcionario "
			+ " group by idade " + " having count(*)>1 " + " order by idade desc ", nativeQuery = true)
	List<FuncionarioSalarioDTO> buscaSalariosPorIdade();
	
	Page<Funcionario>findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);

	Page<Funcionario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
