package br.com.conam.desafiojsf.util;

public class ValidacaoException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2024946204788769369L;

	private Object[] params = new Object[0];

	public ValidacaoException(String key18n, Object... params) {
		super(key18n);
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}
}
