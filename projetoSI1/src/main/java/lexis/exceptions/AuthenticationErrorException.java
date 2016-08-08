package lexis.exceptions;

public class AuthenticationErrorException extends RuntimeException {
	public AuthenticationErrorException(String mensage) {
		super(mensage);
	}
}
