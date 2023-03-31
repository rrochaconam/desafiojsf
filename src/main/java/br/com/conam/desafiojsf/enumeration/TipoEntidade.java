package br.com.conam.desafiojsf.enumeration;

public enum TipoEntidade {
	PREFEITURA(1, "Prefeitura Municipal"),
	CAMARA_MUNICIPAL(2, "Câmara Municipal"),
	FUNDO_ESPECIAL(3, "Fundo Especial"),
	SAAE(4, "SAAE"),
	FUNDACAO_PUBLICA(5, "Fundação Pública"),
	EMPRESA_PUBLICA_NAO_DEPENDENTE(6, "Empresa Pública Não Dependente"),
	SOCIEDADE_ECONOMIA_MISTA_NAO_DEPENDENTE(7, "Sociedade de Economia Mista Não Dependente"),
	EMPRESA_PUBLICA_DEPENDENTE(8, "Empresa Pública Dependente"),
	SOCIEDADE_ECONOMIA_MISTA_DEPENDENTE(9, "Sociedade de Economia Mista Dependente"),
	AUTARQUIA(10, "Autarquia"),
	RPPS(99, "RPPS");

	private final Integer codigo;
	private final String descricao;

	private TipoEntidade(final Integer codigo, final String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoEntidade fromValue(final Integer codigo) {
		for (TipoEntidade t : TipoEntidade.values()) {
			if (t.codigo.equals(codigo)) {
				return t;
			}
		}
		return null;
	}

	public boolean isPrevidencia() {
		return this == RPPS;
	}

	public boolean isPrefeitura() {
		return this == PREFEITURA;
	}

	public boolean isCamaraMunicipal() {
		return this == CAMARA_MUNICIPAL;
	}

	public boolean isAdministracaoDireta() {
		return this == PREFEITURA || this == CAMARA_MUNICIPAL;
	}

	public boolean isAutarquia() {
		return this == AUTARQUIA || this == RPPS;
	}

	public boolean isFundacao() {
		return this == FUNDO_ESPECIAL || this == FUNDACAO_PUBLICA;
	}

	public boolean isEmpresaDependente() {
		return this == EMPRESA_PUBLICA_DEPENDENTE || this == SOCIEDADE_ECONOMIA_MISTA_DEPENDENTE;
	}

	public boolean isEmpresaNaoDependente() {
		return this == EMPRESA_PUBLICA_NAO_DEPENDENTE || this == SOCIEDADE_ECONOMIA_MISTA_NAO_DEPENDENTE;
	}
}