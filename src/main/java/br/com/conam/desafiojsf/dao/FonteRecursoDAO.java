package br.com.conam.desafiojsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.conam.desafiojsf.entity.FonteRecurso;
import br.com.conam.desafiojsf.enumeration.SituacaoModo;
import br.com.conam.desafiojsf.util.ApplicationException;

public class FonteRecursoDAO extends GenericDAO<FonteRecurso> {

	public List<FonteRecurso> findAtivos() throws ApplicationException {
		try {
			CriteriaBuilder builder = repository.getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FonteRecurso> criteria = builder.createQuery(FonteRecurso.class);
			Root<FonteRecurso> root = criteria.from(FonteRecurso.class);
			criteria.select(root);

			List<Predicate> condicoes = new ArrayList<>();
			
			condicoes.add(builder.equal(root.get("situacao").as(SituacaoModo.class), SituacaoModo.ATIVO));
			
			criteria.where(condicoes.toArray(new Predicate[condicoes.size()]));

			return repository.getEntityManager().createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new ApplicationException("message.default", e);
		}
	}
}