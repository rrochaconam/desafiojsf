package br.com.conam.desafiojsf.util.cdi;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.conam.desafiojsf.util.ApplicationException;
import br.com.conam.desafiojsf.util.SessaoApp;

@WebFilter(filterName = "logFilter", urlPatterns = "/pages/*")
public class LogWebFilter implements Filter {

    @Inject
    private SessaoApp sessao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            if (sessao != null && sessao.getIsLogado()) {
                LogThreadLocal.setChave(sessao.getOperador().getChave());
            }
        } catch (Exception e) {
        	try {
				throw new ApplicationException(e);
			} catch (ApplicationException e1) {
				e1.printStackTrace();
			}
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    	
    }
    
}
