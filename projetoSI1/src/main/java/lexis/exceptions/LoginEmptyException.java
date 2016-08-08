package lexis.exceptions;

public class LoginEmptyException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8902710208773621642L;

	public LoginEmptyException(String mensage){
		super(mensage);
	}
}
