package lexis.exceptions;

public class LoginEmptyException extends RuntimeException {
	public LoginEmptyException(String mensage){
		super(mensage);
	}
}
