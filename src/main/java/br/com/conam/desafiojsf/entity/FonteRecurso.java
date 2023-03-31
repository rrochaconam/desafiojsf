package br.com.conam.desafiojsf.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.conam.desafiojsf.enumeration.SituacaoModo;
import br.com.conam.desafiojsf.enumeration.TipoFonteRecurso;
import br.com.conam.desafiojsf.util.BaseEntity;

@Entity
@Table(name = "fonte_recurso")
public class FonteRecurso implements BaseEntity {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -5477586229043422522L;
	
	@Id
	@SequenceGenerator(name = "FONTE_RECURSO_ID_SEQ", sequenceName = "fonte_recurso_id_fonte_recurso_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FONTE_RECURSO_ID_SEQ")
    @Column(name = "id_fonte_recurso")
    private Long id;
	
	@Column(name = "codigo", length = 2)
    private String codigo;

	@Column(name = "descricao", length = 255)
    private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fim_vigencia")
    private Date fimVigencia;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inicio_vigencia")
    private Date inicioVigencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao")
    private SituacaoModo situacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
    private TipoFonteRecurso tipo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public SituacaoModo getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoModo situacao) {
		this.situacao = situacao;
	}

	public TipoFonteRecurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoFonteRecurso tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return defaultHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return defaultEquals(obj);
	}

	public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
