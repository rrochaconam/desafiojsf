package br.com.conam.desafiojsf.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.conam.desafiojsf.enumeration.TipoEntidade;
import br.com.conam.desafiojsf.util.BaseEntity;
import br.com.conam.desafiojsf.util.CnpjUtils;

@Entity
@Table(name = "entidade")
public class Entidade implements BaseEntity {
	private static final long serialVersionUID = -5477586229043422522L;
	
	@Id
	@SequenceGenerator(name = "ENTIDADE_ID_SEQ", sequenceName = "entidade_id_entidade_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTIDADE_ID_SEQ")
	@Column(name = "id_entidade")
	private Long id;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "esfera")
	private Integer esfera;

	@Column(name = "nome")
	private String nome;

	@Column(name = "poder")
	private Integer poder;

	@Column(name = "codigo_municipio")
	private Integer codigoMunicipio;

	@Column(name = "nome_municipio")
	private String nomeMunicipio;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_entidade_brasao")
	@JsonIgnore
	private EntidadeBrasao entidadeBrasao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_entidade")
	private TipoEntidade tipoEntidade;

	@Column(name = "mensageria_token")
	private String mensageriaToken;
	
	@Column(name = "mensageria_id_cliente")
	private String mensageriaIdCliente;

	public boolean isPrevidencia() {
		return this.getTipoEntidade().isPrevidencia();
	}

	public boolean isPrefeitura() {
		return this.getTipoEntidade().isPrefeitura();
	}

	public boolean isCamaraMunicipal() {
		return this.getTipoEntidade().isCamaraMunicipal();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(final String cnpj) {
		this.cnpj = CnpjUtils.formatarCNPJ(cnpj);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(final Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getEsfera() {
		return esfera;
	}

	public void setEsfera(final Integer esfera) {
		this.esfera = esfera;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public Integer getPoder() {
		return poder;
	}

	public void setPoder(final Integer poder) {
		this.poder = poder;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(final Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(final String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public TipoEntidade getTipoEntidade() {
		return tipoEntidade;
	}

	public void setTipoEntidade(final TipoEntidade tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}

	public EntidadeBrasao getEntidadeBrasao() {
		return entidadeBrasao;
	}

	public void setEntidadeBrasao(final EntidadeBrasao entidadeBrasao) {
		this.entidadeBrasao = entidadeBrasao;
	}
	
	public String getMensageriaToken() {
		return mensageriaToken;
	}

	public void setMensageriaToken(String mensageriaToken) {
		this.mensageriaToken = mensageriaToken;
	}

	public String getMensageriaIdCliente() {
		return mensageriaIdCliente;
	}

	public void setMensageriaIdCliente(String mensageriaIdCliente) {
		this.mensageriaIdCliente = mensageriaIdCliente;
	}

	@Override
	public int hashCode() {
		return defaultHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return defaultEquals(obj);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
}