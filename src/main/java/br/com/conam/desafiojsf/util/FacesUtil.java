package br.com.conam.desafiojsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void addMsgError(String mensagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("mensagemErro", facesMessage);
	}
	
	public static void addMsgWarn(String mensagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("mensagemWarn", facesMessage);
	}
	
	public static void addMsgInfo(String mensagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("mensagemSucesso", facesMessage);
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}
}
