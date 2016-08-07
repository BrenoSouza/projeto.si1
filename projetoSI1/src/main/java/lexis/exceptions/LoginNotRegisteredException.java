package lexis.exceptions;

public class LoginNotRegisteredException extends RuntimeException{
	public LoginNotRegisteredException(String mensage){
		super(mensage);
	}
}
