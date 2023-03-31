package br.com.conam.desafiojsf.util.cdi;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.conam.desafiojsf.util.SessaoApp;

public class LoginFilter implements Filter {
	
	@Inject
	private SessaoApp sessao;
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}
    
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
}
