package br.com.conam.desafiojsf.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.SerializationUtils;

import br.com.conam.desafiojsf.dao.FonteRecursoDAO;
import br.com.conam.desafiojsf.entity.FonteRecurso;
import br.com.conam.desafiojsf.util.ApplicationException;
import br.com.conam.desafiojsf.util.cdi.Transaction;

@RequestScoped
public class FonteRecursoBusiness implements Serializable {
	private static final long serialVersionUID = -783404834535332212L;
	
	@Inject
	private FonteRecursoDAO fonteRecursoDAO;
	
	/**
	 * findAll - traz todos os fonteRecursos
	 * @return
	 * @throws ApplicationException
	 */
	public List<FonteRecurso> findAll() throws ApplicationException {
		return fonteRecursoDAO.findAtivos();
	}
	
	/**
	 * altera um fonteRecuro
	 * @param fonteRecurso
	 * @throws ApplicationException
	 */
	@Transaction
	public void incluir(FonteRecurso fonteRecurso) throws ApplicationException {
		fonteRecurso = SerializationUtils.clone(fonteRecurso);
		fonteRecurso.setId(null);
		fonteRecursoDAO.incluir(fonteRecurso);
	}
	
	/**
	 * inclui um fonteRecurso
	 * @param fonteRecurso
	 * @throws ApplicationException
	 */
	@Transaction
	public void alterar(FonteRecurso fonteRecurso) throws ApplicationException {
		fonteRecurso = SerializationUtils.clone(fonteRecurso);
		fonteRecursoDAO.editar(fonteRecurso);
	}
	
	/**
	 * Exclui uma FonteRecurso
	 * @param fonteRecurso
	 * @return
	 * @throws ApplicationException 
	 */
	@Transaction
	public void excluir(FonteRecurso fonteRecurso) throws ApplicationException {
		fonteRecursoDAO.removeById(fonteRecurso.getId(), FonteRecurso.class.getName());
	}
	
}