package org.serratec.aula4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@NotNull(message = "Matrícula é obrigatória")
	@Column(name = "matricula", nullable = false, unique = true)
	private Long matricula;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@NotBlank(message = "Telefone é obrigatório")
	@Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
	@Column(name = "telefone", length = 15)
	private String telefone;

	public Aluno(Long matricula, String nome, String telefone) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Aluno() {

	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
