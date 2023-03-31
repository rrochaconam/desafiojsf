package br.com.conam.desafiojsf.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.conam.desafiojsf.util.ApplicationException;
import br.com.conam.desafiojsf.util.cdi.GenericRepository;

public class GenericDAO<T> {
	@Inject
	public GenericRepository repository;

	public T incluir(T objeto) throws ApplicationException {
		try {
			repository.insert(objeto);
			return objeto;
		} catch (PersistenceException exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao." + objeto.getClass() + ".incluir.ERRO", exception);
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao." + objeto.getClass() + ".incluir.ERRO", exception);
		}
	}
	
	public T obter(final Long id, final Class<T> objeto) throws ApplicationException {
		try {
			return repository.findById(objeto, id);
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao." + objeto.getClass() + ".obter.ERRO", exception);
		}
	}

	public T editar(T objeto) throws ApplicationException {
		try {
			return repository.update(objeto);
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao." + objeto.getClass() + ".editar.ERRO", exception);
		}
	}

	public void excluir(T objeto) throws ApplicationException {
		try {
			repository.remove(objeto);
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao." + objeto.getClass() + ".excluir.ERRO", exception);
		}
	}
	
	public Number contar(String fieldCount, String sqlAfterCount, Object... params) {
		return repository.count(fieldCount, sqlAfterCount, params);
	}

	public void begin() throws ApplicationException {
		try {
			repository.begin();
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao.begin.ERRO", exception);
		}
	}
	
	public boolean isActive() throws ApplicationException {
		try {
			return repository.isActive();
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao.begin.ERRO", exception);
		}
	}

	public void commit() throws ApplicationException {
		try {
			repository.commit();
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao.commit.ERRO", exception);
		}
	}

	public void rollback() throws ApplicationException {
		try {
			repository.rollback();
		} catch (Exception exception) {
			throw new ApplicationException("br.com.conam.desafiojsf.dao.rollback.ERRO", exception);
		}
	}

	public void update(final String sql, Object... params) {
		repository.update(sql, params);
    }

	public List<Object> query(final String sql, Object... params) {
		return repository.query(sql, params);
    }


	public void flushAndClear() {
		repository.flushAndClear();
	}

	public void clear() {
		repository.clear();
	}
	
	public void removeById(Long id, String className) throws ApplicationException {
		repository.removeById(id, className);
	}
	
	
}
