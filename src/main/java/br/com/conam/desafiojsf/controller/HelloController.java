package br.com.conam.desafiojsf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.conam.desafiojsf.business.FonteRecursoBusiness;
import br.com.conam.desafiojsf.entity.FonteRecurso;
import br.com.conam.desafiojsf.util.ApplicationException;
import br.com.conam.desafiojsf.util.cdi.Transaction;

@ManagedBean
@RequestScoped
public class HelloController implements Serializable {
    
	private static final long serialVersionUID = -126464751228150501L;

	String mensagem;
	
	@Inject
	private FonteRecursoBusiness fonteRecursoBusiness;
	
	List<FonteRecurso> fonteRecursoList = new ArrayList<>();
	
	@PostConstruct
	public void init(){
	}

	public void submit() {
		Date currentDate = new Date();
		try {
			fonteRecursoList = fonteRecursoBusiness.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "Data/hora: " + e.getMessage();
			return;
		}
		mensagem = "Data/hora: " + currentDate.toString() + " >> Listar";
	}

	public void clean() {
		Date currentDate = new Date();
		mensagem = "Data/hora:" + currentDate.toString() + " >> Limpar";
	}
	
	public String getMensagem() {
       return mensagem;
    }

	public List<FonteRecurso> getFonteRecursoList() {
		return fonteRecursoList;
	}
    
}
