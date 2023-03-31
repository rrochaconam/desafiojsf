package br.com.conam.desafiojsf.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.conam.desafiojsf.dto.OperadorDTO;
import br.com.conam.desafiojsf.entity.Entidade;

@Named
@SessionScoped
public class SessaoApp implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5238394885969745929L;

	private OperadorDTO operador = new OperadorDTO();
	private Entidade entidade;
	private String contexto = "conam";
	private String token = "";
	private boolean configurado = false;
	private Integer anoExercicio;
	
	private Entidade entidadeContexto;
	
	/**
	 * 
	 * @return
	 */
	public Boolean getIsLogado() {
		if(operador != null && operador.getId() != null){
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna a imagem para exibir na tela
	 * @return
	 * @throws IOException
	 */
	public StreamedContent getImage() throws IOException {
		if(entidade == null || entidade.getId() == null){
			return null;
		}
		if(entidade.getEntidadeBrasao() == null){
			return null;
		}
		if(entidade.getEntidadeBrasao().getBrasao() == null){
			return null;
		}
		return new DefaultStreamedContent(new ByteArrayInputStream((byte[])entidade.getEntidadeBrasao().getBrasao()), entidade.getEntidadeBrasao().getMimeType());
    }
	
	/**
	 * 
	 * @return
	 */
	protected FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
	}

	public OperadorDTO getOperador() {
		return operador;
	}

	public void setOperador(OperadorDTO operador) {
		this.operador = operador;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isConfigurado() {
		return configurado;
	}

	public void setConfigurado(boolean configurado) {
		this.configurado = configurado;
	}

	public Integer getAnoExercicio() {
		return anoExercicio;
	}

	public void setAnoExercicio(Integer anoExercicio) {
		this.anoExercicio = anoExercicio;
	}

	public Entidade getEntidadeContexto() {
		return entidadeContexto;
	}

	public void setEntidadeContexto(Entidade entidadeContexto) {
		this.entidadeContexto = entidadeContexto;
	}


	
}
