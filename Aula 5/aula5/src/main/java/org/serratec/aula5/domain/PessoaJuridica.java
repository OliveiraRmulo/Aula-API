package org.serratec.aula5.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Fornecedor {
	
	@Column
	private String cnpj;
	
	@Column
	private String razaoSocial;
	
	@Column
	private String insEstadual;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getInsEstadual() {
		return insEstadual;
	}
	public void setInsEstadual(String insEstadual) {
		this.insEstadual = insEstadual;
	}
	
}
