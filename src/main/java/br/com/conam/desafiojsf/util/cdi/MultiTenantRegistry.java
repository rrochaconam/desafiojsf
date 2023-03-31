package br.com.conam.desafiojsf.util.cdi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads Tenants from DB, creates EntityManagerFactories for them.
 */
@ApplicationScoped
public class MultiTenantRegistry {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultiTenantRegistry.class);

    private final Map<String, EntityManagerFactory> entityManagerFactories = new HashMap<>();
    
    private String contexto;

    /**
     * Returns EntityManagerFactory from the cache. EMF is created during tenant registration and initialization.
     * @see #startupTenants()
     */
    public EntityManagerFactory getEntityManagerFactory(final String tenantName) {
        if (!hasTenant(tenantName)) {
            initTenant(tenantName);
        }
        return entityManagerFactories.get(tenantName);
    }

    private void initTenant(final String tenant) {
        final EntityManagerFactory emf = createEntityManagerFactory(tenant);
        entityManagerFactories.put(tenant, emf);
        LOGGER.info("Tenant " + tenant + " loaded.");
    }

    /**
     * Create new {@link EntityManagerFactory} using this tenant's schema.
     * @param tenant Tenant used to retrieve schema name
     * @return new EntityManagerFactory
     */
    private EntityManagerFactory createEntityManagerFactory(final String tenant) {
        Map<String, String> props = new LinkedHashMap<String, String>();
        LOGGER.debug("Creating entity manager factory on schema '" + tenant + "' for tenant '" + tenant + "'.");
        props.put("hibernate.default_schema", tenant);
        return Persistence.createEntityManagerFactory("default", props);
    }

    private boolean hasTenant(final String tenantName) {
        return entityManagerFactories.containsKey(tenantName);
    }

    public String getContexto() {
 		return contexto;
 	}

 	public void setContexto(String contexto) {
 		this.contexto = contexto;
 	}
}
