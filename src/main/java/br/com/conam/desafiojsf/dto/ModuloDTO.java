package br.com.conam.desafiojsf.dto;

import java.io.Serializable;

public class ModuloDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4788001018717973873L;
	
	private String sigla;
	private String nome;
	
	public ModuloDTO() {
	}

	public ModuloDTO(String sigla) {
		super();
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
