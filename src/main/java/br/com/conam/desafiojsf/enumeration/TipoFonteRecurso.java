package br.com.conam.desafiojsf.enumeration;

import javax.validation.constraints.NotNull;

public enum TipoFonteRecurso {
	
	ORCAMENTARIA("Orçamentária"), 
	ESPECIAL("Especial"),
	EXTRAORDINARIA("Extraordinária");
	
	@NotNull
	private String descricao;
	
	private TipoFonteRecurso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoFonteRecurso fromValue(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		for(TipoFonteRecurso valor: TipoFonteRecurso.values()) {
			
			if(valor.getDescricao().equals(descricao)) {
				return valor;
			}
		}
		
		return null;
	}
}