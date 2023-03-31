package br.com.conam.desafiojsf.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.conam.desafiojsf.util.BaseEntity;

@Entity
@Table(name = "entidade_brasao")
public class EntidadeBrasao implements BaseEntity {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -5477586229043422522L;
	
	@Id
	@SequenceGenerator(name = "ENTIDADE_BRASAO_ID_SEQ", sequenceName = "entidade_brasao_id_entidade_brasao_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTIDADE_BRASAO_ID_SEQ")
    @Column(name = "id_entidade_brasao")
    private Long id;

    private byte[] brasao;

    @Column(name = "mime_type")
    private String mimeType;

    public byte[] getBrasao() {
        return brasao;
    }

    public void setBrasao(final byte[] brasaoObjeto) {
        if (brasaoObjeto == null) {
            this.brasao = new byte[0];
        } else {
            this.brasao = Arrays.copyOf(brasaoObjeto, brasaoObjeto.length);
        }
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
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
