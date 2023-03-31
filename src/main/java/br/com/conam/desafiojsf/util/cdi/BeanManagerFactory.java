package br.com.conam.desafiojsf.util.cdi;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory que obtem o BeanManager que é um facade do container CDI, com o qual pode-se efetuar look up dos beans gerenciados.
 */
public final class BeanManagerFactory {

    /**
     * Construtor privado.
     */
    private BeanManagerFactory() {
    }

    /**
     * Nome JNDI do bean manager CDI.
     */
    private static final String JNDI_NAME = "java:comp/env/BeanManager";

    /**
     * Logger para esta classe.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanManagerFactory.class);

    /**
     * Obtém o gerenciador de beans CDI.
     * @return o BeanManager ativo
     */
    public static BeanManager getBeanManager() {
        try {
            InitialContext context = new InitialContext();
//            Map<String, Object> map = toMap(context);
//            for (Entry<String, Object> e : map.entrySet()) {
//				System.out.println(e.getKey() + " ----> " + e.getValue());
//			}
            return (BeanManager) context.lookup(JNDI_NAME);
        } catch (NamingException e) {
            LOGGER.debug("BeanManager não encontrado.", e);
        }
        return null;
    }
    
    
//    public static Map<String, Object> toMap(Context ctx) throws NamingException {
//        String namespace = ctx instanceof InitialContext ? ctx.getNameInNamespace(): "";
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        System.out.println("> Listing namespace: " + namespace);
//        NamingEnumeration<NameClassPair> list = ctx.list(namespace);
//        while (list.hasMoreElements()) {
//            NameClassPair next = list.next();
//            String name = next.getName();
//            String jndiPath = namespace + name;
//            Object lookup;
//            try {
//            	System.out.println("> Looking up name: " + jndiPath);
//                Object tmp = ctx.lookup(jndiPath);
//                if (tmp instanceof Context) {
//                    lookup = toMap((Context) tmp);
//                } else {
//                    lookup = tmp.toString();
//                }
//            } catch (Throwable t) {
//                lookup = t.getMessage();
//            }
//            map.put(name, lookup);
//
//        }
//        return map;
//    }
    
}
