package br.com.conam.desafiojsf.util.cdi;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transaction
public class TransactionInterceptor implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5796835575089637844L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object beginTransactionIfNotActive(InvocationContext ic)
			throws Exception {

		boolean newTransaction = false;
		if (!manager.getTransaction().isActive()) {
			manager.getTransaction().begin();
			newTransaction = true;
		}
		Object retVal = null;
		try {
			retVal = ic.proceed();
			if (newTransaction) {
				manager.getTransaction().commit();
			}
		} catch(DoRollBackException c) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} catch (Throwable t) {
			if (newTransaction) {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
				}
			}
			throw t;
		}
		return retVal;
	}

}
