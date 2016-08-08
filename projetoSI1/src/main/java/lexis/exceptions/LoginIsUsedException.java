package lexis.exceptions;

public class LoginIsUsedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3836843766684522227L;

	public LoginIsUsedException(String mensage){
		super(mensage);
	}

}
