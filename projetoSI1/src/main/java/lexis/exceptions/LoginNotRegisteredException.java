package lexis.exceptions;

public class LoginNotRegisteredException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6090468270132055479L;

	public LoginNotRegisteredException(String mensage) {
		super(mensage);
	}
}
