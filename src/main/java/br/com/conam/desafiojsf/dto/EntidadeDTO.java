package br.com.conam.desafiojsf.dto;

import java.io.Serializable;

public class EntidadeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4788001018717973873L;
	
	private Integer codigo;
	private String nome;
	private String tipoEntidade;
	
	public EntidadeDTO() {
	}
	
	public EntidadeDTO(Integer codigo, String tipoEntidade) {
		super();
		this.codigo = codigo;
		this.tipoEntidade = tipoEntidade;
	}


	public EntidadeDTO(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public String getTipoEntidade() {
		return tipoEntidade;
	}

	public void setTipoEntidade(String tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
