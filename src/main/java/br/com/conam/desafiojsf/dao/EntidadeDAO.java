package br.com.conam.desafiojsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.conam.desafiojsf.entity.Entidade;
import br.com.conam.desafiojsf.util.ApplicationException;

public class EntidadeDAO extends GenericDAO<Entidade> {

	/**
	 * Realiza a busca de cargos por filtro
	 * @param nome
	 * @param situacao
	 * @return
	 * @throws ApplicationException
	 */
	public Entidade obterFetch(Long id) {
		CriteriaBuilder builder = repository.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Entidade> criteria = builder.createQuery(Entidade.class);
		Root<Entidade> root = criteria.from(Entidade.class);
		criteria.select(root).distinct(true);

		List<Predicate> condicoes = new ArrayList<>();
		condicoes.add(builder.equal(root.get("id").as(Long.class), id));
		root.fetch("entidadeBrasao", JoinType.LEFT);
		
		criteria.where(condicoes.toArray(new Predicate[condicoes.size()]));

		List<Entidade> lista = repository.getEntityManager().createQuery(criteria).getResultList();
		if(lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}
	
	/**
	 * Realiza a busca de cargos por filtro
	 * @param nome
	 * @param situacao
	 * @return
	 * @throws ApplicationException
	 */
	public List<Entidade> pesquisar(Integer codigo) throws ApplicationException {
		try {
			CriteriaBuilder builder = repository.getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Entidade> criteria = builder.createQuery(Entidade.class);
			Root<Entidade> root = criteria.from(Entidade.class);
			criteria.select(root).distinct(true);

			List<Predicate> condicoes = new ArrayList<>();
			
			// codigo
			if(codigo != null) {
				condicoes.add(builder.equal(root.get("codigo").as(Integer.class), codigo));
			}
			
			criteria.where(condicoes.toArray(new Predicate[condicoes.size()]));
			criteria.orderBy(builder.asc(root.get("nome")));

			return repository.getEntityManager().createQuery(criteria).getResultList();
			
		} catch (Exception e) {
			throw new ApplicationException("message.default", e);
		}
	}
	
	/**
	 * Retorna os conextos de entidade
	 * @param contexto
	 * @return
	 */
	public List<Entidade> listarEntidades() {
		CriteriaBuilder builder = repository.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Entidade> criteria = builder.createQuery(Entidade.class);
		Root<Entidade> root = criteria.from(Entidade.class);
		criteria.select(root);

//		List<Predicate> condicoes = new ArrayList<>();
//		condicoes.add(builder.equal(root.get("contexto").as(String.class), contexto));
//		criteria.where(condicoes.toArray(new Predicate[condicoes.size()]));

		return repository.getEntityManager().createQuery(criteria).getResultList();
	}

	
}
