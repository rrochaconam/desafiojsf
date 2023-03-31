package br.com.conam.desafiojsf.util.cdi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

/**
 * Classe utilitária para acesso a beans CDI. Seu uso só faz sentido em um contexto gerenciado.
 */
public final class CdiUtil {

    /**
     * Construtor privado.
     */
    private CdiUtil() {

    }

    /**
     * Obtém um bean CDI a partir de seu tipo.
     * @param <T> Tipo do bean desejado.
     * @param beanType Tipo do bean desejado
     * @param qualifiers Qualificadores para especificar o tipo do objeto devolvido entre as múltiplas implementações possíveis.
     * @return Um bean CDI do tipo informada
     * @throws IllegalArgumentException Se não existir um bean do tipo {@code T}, ou se existir mais de bean do tipo {@code T}.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(final Type beanType, final Annotation... qualifiers) {
        BeanManager manager = BeanManagerFactory.getBeanManager();
        if (manager != null) {
            Iterator<Bean<?>> it = manager.getBeans(beanType, qualifiers).iterator();
            if (!it.hasNext()) {
                throw new IllegalArgumentException("No bean of type " + beanType + " available for injection");
            }
            Bean<T> bean = (Bean<T>) it.next();
            if (it.hasNext()) {
                throw new IllegalArgumentException("Multiple beans of type " + beanType + " available for injection");
            }
            CreationalContext<T> creationalContext = manager.createCreationalContext(bean);
            return (T) manager.getReference(bean, beanType, creationalContext);
        }
        return null;
    }

}
