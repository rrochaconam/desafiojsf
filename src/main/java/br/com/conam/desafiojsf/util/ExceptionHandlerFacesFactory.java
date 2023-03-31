package br.com.conam.desafiojsf.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Fabrica customizada para tratar exceções do JSF
 */
public class ExceptionHandlerFacesFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory parent;

    public ExceptionHandlerFacesFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    /**
     * Retorna instÃ¢ncia da classe que irÃ¡ manipular as exceÃ§Ãµes do JSF.
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler result = parent.getExceptionHandler();
        result = new ExceptionHandlerFaces(result);
        return result;
    }
}