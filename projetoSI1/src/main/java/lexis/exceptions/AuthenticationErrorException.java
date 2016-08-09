package lexis.exceptions;

public class AuthenticationErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6488605397730785601L;

	public AuthenticationErrorException(String mensage) {
		super(mensage);
	}
}
