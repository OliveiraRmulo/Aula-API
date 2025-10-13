package org.serratec.exercicio_aula4.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome do funcionário é obrigatório")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@CPF(message = "O CPF informado não é válido")
	@Column(name = "cpf", nullable = false, unique = true, length = 11)
	private String Cpf;
	
	@NotNull(message = "O salário é obrigatório")
	@DecimalMin(value = "0.00", inclusive = false, message = "O salário deve ser maior que zero")
	@Digits(integer = 8, fraction = 2, message = "O salário deve ter no máximo 8 dígitos inteiros e 2 decimais")
	@Column(name = "salario", nullable = false, precision = 10, scale = 2)
	//99999999.99 ou ########.## -> precision = 10, scale = 2
	private BigDecimal salario;
	
	@NotNull(message = "A data de admissão é obrigatória")
	@PastOrPresent(message = "A data de admissão não pode ser no futuro")
	@Column(name = "data_admissao", nullable = false)
	private LocalDate dataAdmissao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
}
