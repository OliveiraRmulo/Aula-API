package org.serratec.projeto_service_dto.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto")
	private Long id;
	
	@Lob
	@Column(name = "dados")
	@JdbcTypeCode(SqlTypes.VARBINARY)
	private byte[] dados;
	
	private String tipo;
	
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	public Foto() {
		super();
	}

}
