package br.com.conam.desafiojsf.util.cdi;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.conam.desafiojsf.dto.OperadorDTO;
import br.com.conam.desafiojsf.util.SessaoApp;

public class SessaoResourcesProducer {

	/**
     * Sessão ativa.
     */
    @Inject
    private SessaoApp sessao;

    /**
     * Gera o operador logado.
     * @return o operador logado.
     */
    @Produces
    @SessionScoped
    @Named("operadorLogado")
    public OperadorDTO produceOperador() {
        return sessao.getOperador();
    }
}

