package org.serratec.backend.enums;

import org.serratec.backend.exception.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Combustivel {

	ALCOOL(1,"Alcool"),
	GASOLINA(2,"Gasolina"),
	FLEX(3,"Flex"),
	DIESEL(4,"Diesel");
	
	private Integer codigo;
	private String descricao;
	
	@JsonCreator
	public static Combustivel verifica(Integer value) throws EnumValidationException {
		
		for (Combustivel combustivel : values()) {
			if(value.equals(combustivel.getCodigo())) {
				return combustivel;
			}
		}
		
		throw new EnumValidationException("Combustível inválido. Valores válidos: 1 - Alcool, 2 - Gasolina, 3 - Flex, 4 - Diesel");
	}
	
	private Combustivel(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
