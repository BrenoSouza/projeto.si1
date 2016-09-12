package lexis.exceptions;

public class LoginInvalidException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3547247862543220384L;

	public LoginInvalidException(String mensage) {
		super(mensage);
	}
	
}
