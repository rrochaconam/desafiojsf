package br.com.conam.desafiojsf.util.cdi;

/**
 * Excecao usada para informar ao {@link TransactionInterceptor} que o rollback
 * devera ser feito
 */
public class DoRollBackException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -341655187452185266L;

	public DoRollBackException() {
		super();
	}

	public DoRollBackException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoRollBackException(String message) {
		super(message);
	}

	public DoRollBackException(Throwable cause) {
		super(cause);
	}

}
