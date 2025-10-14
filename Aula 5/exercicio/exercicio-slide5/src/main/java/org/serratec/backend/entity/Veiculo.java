package org.serratec.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "veiculo")
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "A placa é obrigatória")
	@Size(max = 7, message = "A placa deve ter no máximo 7 caracteres")
	@Column(nullable = false, length = 7)
	private String placa;
	
	@NotBlank(message = "A marca é obrigatória")
	@Size(max = 30, message = "A marca deve ter no máximo 30 caracteres")
	@Column(nullable = false, length = 30)
	private String marca;
	
	@NotBlank(message = "O modelo é obrigatório")
	@Size(max = 40, message = "O modelo deve ter no máximo 40 caracteres")
	@Column(nullable = false, length = 40)
	private String modelo;
	
	@Embedded
	@Valid
	private Caracteristica caracteristica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	
}
