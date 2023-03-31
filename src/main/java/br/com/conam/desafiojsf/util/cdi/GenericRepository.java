package br.com.conam.desafiojsf.util.cdi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.conam.desafiojsf.util.ApplicationException;

/**
 * Classe base para manipular objetos com o banco.
 * @author Ronen
 */
@RequestScoped @Default
public class GenericRepository implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1245285684694725631L;

	/**
	 * Enum identificar AND ou OR
	 */
	public enum Occur {AND, OR}


	@Inject
	private EntityManager entityManager;

	public GenericRepository() {
	}

	public GenericRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#insert(T)
	 */
	public <T> T insert(T object) {
		if (object == null) {
			throw new IllegalArgumentException("Param object is null");
		}
		entityManager.persist(object);
		return object;
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#update(T)
	 */
	public <T> T update(T object) {
		if (object == null) {
			throw new IllegalArgumentException("Param object is null");
		}
		return entityManager.merge(object);
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#remove(T)
	 */
	public <T> void remove(T object) {
		if (object == null) {
			throw new IllegalArgumentException("Param object is null");
		}
		entityManager.remove(object);
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#findById(java.lang.Class, java.lang.Object)
	 */
	public <T> T findById(Class<T> clazz, Object pk) {
		if (pk == null) {
			return null;
		}
		return entityManager.find(clazz, pk);
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#getEntityManager()
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#setEntityManager(javax.persistence.EntityManager)
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#list(java.lang.Class)
	 */
	public <T> List<T> list(Class<T> clazz) {
		TypedQuery<T> query = this.entityManager.createQuery("from " + clazz.getName(), clazz);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#search(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public <T> List<T> search(Class<T> clazz, String sql, Object... params) {
		TypedQuery<T> query = this.entityManager.createQuery(sql, clazz);
		int i = 1;
		for (Object param : params)
			query.setParameter(i++, param);
		try {
			return query.getResultList();
		} finally {
			query = null;
			sql = null;
			params = null;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#search(java.lang.Class, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Object)
	 */
	public <T> List<T> search(Class<T> clazz, String sql, Integer start, Integer limit, Object... params) {
		TypedQuery<T> query = this.entityManager.createQuery(sql, clazz);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		int i = 1;
		for (Object param : params)
			query.setParameter(i++, param);
		try {
			return query.getResultList();
		} finally {
			query = null;
			sql = null;
			start = null;
			limit = null;
			params = null;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#searchUniqueResult(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public <T> T searchUniqueResult(Class<T> clazz, String sql, Object... params) {
		TypedQuery<T> query = this.entityManager.createQuery(sql, clazz);
		T singleResult = null;
		try {
			query.setFirstResult(0);
			query.setMaxResults(1);
			int i = 1;
			for (Object param : params)
				query.setParameter(i++, param);
			singleResult = (T) query.getSingleResult();
		} catch (NoResultException noResult) {
			// se nao encontrar entao retorna null
		} catch (NonUniqueResultException noUniqueResult) {
			System.err.println(sql);
			noUniqueResult.printStackTrace();
		} finally {
			query = null;
			sql = null;
			params = null;
			clazz = null;
		}
		return singleResult;
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#count(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public Number count(String fieldCount, String sqlAfterCount, Object... params) {
		Query queryCount = this.entityManager.createQuery("SELECT COUNT(" + fieldCount + ")" + sqlAfterCount);
		try {
			int i = 0;
			for (Object param : params) {
				queryCount.setParameter(i++, param);
			}
			return (Number) queryCount.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (queryCount != null)
				queryCount = null;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.devlogic.helper.bo.GenericRepository#search(java.lang.Class, br.com.devlogic.helper.pagination.Pagination, java.lang.String, java.lang.String, java.lang.Object)
	 */
	public <T> void search(Class<T> clazz, Pagination<T> pagination, String sql, String fieldCount, Object... params) {
		// executa count
		Integer posicaoFrom = sql.toUpperCase().indexOf("FROM ");
		String sqlAfterCount = sql.substring(posicaoFrom, sql.length());
		pagination.setNumRegTotal(count(fieldCount, sqlAfterCount, params).intValue());
		if (pagination.getNumPage() == null || pagination.getNumPage().equals(0)) {
			pagination.setNumPage(new Integer(1));
		}
		// caso o numero da pagina informado seja maior que o numero de
		// registros encontrado
		Integer limit = new BigDecimal(pagination.getNumRegTotal() / (double) pagination.getNumRegByPage()).setScale(0, BigDecimal.ROUND_UP).intValue();
		pagination.setLimit(limit);
		if (limit > 0 && pagination.getNumPage().compareTo(limit) == 1) {
			pagination.setNumPage(limit);
		}
		StringBuilder queryOrder = new StringBuilder();
		// seja ordenacao
		if (pagination.getOrderBy() != null || pagination.getFieldOrder() != null) {
			queryOrder.append(" ORDER BY " + (pagination.getFieldOrder() != null ? pagination.getFieldOrder() : pagination.getOrderBy()) + (pagination.getSortBy() == 1 ? " ASC " : " DESC "));
		}
		// logica identificar os intervalos do filtro
		TypedQuery<T> query = this.entityManager.createQuery(sql + queryOrder.toString(), clazz);
		query.setFirstResult((pagination.getNumPage() - 1) * pagination.getNumRegByPage());
		query.setMaxResults(pagination.getNumRegByPage());
		int i = 1;
		for (Object param : params) {
			query.setParameter(i++, param);
		}
		try {
			pagination.setArrList(query.getResultList());
		} finally {
			query = null;
			clazz = null;
			sql = null;
			fieldCount = null;
			params = null;
			pagination = null;
			posicaoFrom = null;
			sqlAfterCount = null;
			limit = null;
			queryOrder = null;
		}
	}

	@SuppressWarnings("unchecked")
	public void search(Pagination<?> pagination, Query query) {
		if (pagination.getNumPage() == null || pagination.getNumPage() <= 0) {
			pagination.setNumPage(new Integer(1));
		}
		if (pagination.getNumRegTotal() == null) {
			throw new IllegalArgumentException("Numero total de registros nao informado");
		}
		Integer limit = new BigDecimal(pagination.getNumRegTotal() / (double) pagination.getNumRegByPage()).setScale(0, BigDecimal.ROUND_UP).intValue();
		pagination.setLimit(limit);
		if (limit > 0 && pagination.getNumPage().compareTo(limit) == 1) {
			pagination.setNumPage(limit);
		}
		// logica identificar os intervalos do filtro
		query.setFirstResult((pagination.getNumPage() - 1) * pagination.getNumRegByPage());
		query.setMaxResults(pagination.getNumRegByPage());
		try {
			pagination.setArrList(query.getResultList());
		} finally {
			query = null;
			pagination = null;
			limit = null;
		}
	}

	public String buildCondition(Collection<String> sentences, Occur occur) {
		StringBuilder condition = new StringBuilder();
		for (String string : sentences) {
			if (condition.length() > 0) {
				condition.append(" ");
				condition.append(occur.name());
				condition.append(" ");
			}
			condition.append(string);
		}
		if (condition.length() > 0) {
			condition.insert(0, " where ");
		}
		return condition.toString();
	}

	public void excluir(String clazz, Object pk) {
		if (pk == null) {
			throw new IllegalArgumentException("Param object is null");
		}
		String hql = "DELETE FROM " + clazz + " WHERE id=:id";
		Query query = entityManager.createQuery(hql);
		query.setParameter("id", pk);
		query.executeUpdate();
		
	}

	public void commit() {
		entityManager.getTransaction().commit();
	}

	public void rollback() {
		entityManager.getTransaction().rollback();
	}

	public void begin() {
		entityManager.getTransaction().begin();
	}

	public boolean isActive() {
		return entityManager.getTransaction().isActive();
	}
	
	public <T> T updateCommit(T object) {
		if (object == null) {
			throw new IllegalArgumentException("Param object is null");
		}
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		object = entityManager.merge(object);
		commit();
		return object;
	}
	
	public void delete(final String sql, Object... params) {
		Query query = this.entityManager.createNativeQuery(sql);
		int i = 1;
		for (Object param : params) {
			query.setParameter(i++, param);
		}
		query.executeUpdate();
    }

	public void update(final String sql, Object... params) {
		Query query = this.entityManager.createNativeQuery(sql);
		int i = 1;
		for (Object param : params) {
			query.setParameter(i++, param);
		}
		query.executeUpdate();
    }

	@SuppressWarnings("unchecked")
	public List<Object> query(final String sql, Object... params) {
		Query query = this.entityManager.createNativeQuery(sql);
		if (params != null) {
			int i = 1;
			for (Object param : params) {
				query.setParameter(i++, param);
			}
		}
		return query.getResultList();
    }

	public void flushAndClear() {
		entityManager.flush();
		entityManager.clear();
	}

	public void clear() {
		entityManager.clear();
	}
	
	public void removeById(Long id, String className) throws ApplicationException {
		try {
			entityManager
			.createQuery("delete from " + className + " t where t.id=:id")
			.setParameter("id", id)
			.executeUpdate();
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao." + className + ".excluir.ERRO", exception);
		}
	}
}