package br.com.conam.desafiojsf.util.cdi;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.conam.desafiojsf.dto.OperadorDTO;


public class EntityManagerFactoryComponent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerFactoryComponent.class);

	@Inject
	private MultiTenantRegistry multiTenantRegistry;
	
	@Produces
	@RequestScoped
	private EntityManager getEntityManager() {
		OperadorDTO operador = getOperador();
		String currentTenant = (operador != null? operador.getTenant() : null);
		if(currentTenant == null) {
			currentTenant = multiTenantRegistry.getContexto();
		}
        LOGGER.debug("Returning connection for tenant " + currentTenant);
		return multiTenantRegistry.getEntityManagerFactory(currentTenant).createEntityManager();

	}

	
//	@Produces
//	@RequestScoped
//	public EntityManager create() {
//		return getEntityManager();
//	}

	public void end(@Disposes EntityManager entity) {
		entity.close();
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	private OperadorDTO getOperador() {
		LOGGER.trace("Procurando Operador via bean manager");
		BeanManager beanManager = BeanManagerFactory.getBeanManager();
		try {
			Set<Bean<?>> operadorSet = beanManager.getBeans("operadorLogado");
			if (!operadorSet.isEmpty()) {
				LOGGER.trace("Producer de Operador encontrado. Tentando produzi-lo.");
				return (OperadorDTO) operadorSet.iterator().next().create(beanManager.createCreationalContext((Contextual) null));
			}
		} catch (Exception exception) {
			LOGGER.debug("Erro ao produzir o bean operador.");
		}
		return null;
	}

}
