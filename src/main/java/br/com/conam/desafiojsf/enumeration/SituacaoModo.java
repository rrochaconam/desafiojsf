package br.com.conam.desafiojsf.enumeration;

import javax.validation.constraints.NotNull;

public enum SituacaoModo {
	
	ATIVO("Ativo"), 
	INATIVO("Inativo");
	
	@NotNull
	private String descricao;
	
	private SituacaoModo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static SituacaoModo fromValue(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		for(SituacaoModo valor: SituacaoModo.values()) {
			
			if(valor.getDescricao().equals(descricao)) {
				return valor;
			}
		}
		
		return null;
	}
}