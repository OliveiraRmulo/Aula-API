package org.serratec.backend.enums;

import org.serratec.backend.exception.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Categoria {

	HATCH, SEDAN, SUV, PICAPE;
	
	@JsonCreator
	public static Categoria verifica(String value) throws EnumValidationException {
		
		for (Categoria categoria : values()) {
			if(value.equals(categoria.name())) {
				return categoria;
			}
		}
		
		throw new EnumValidationException("Categoria inválida. Valores válidos: HATCH, SEDAN, SUV, PICAPE");
	}
}
