package lexis.exceptions;

public class LoginIsUsedException extends RuntimeException{
	LoginIsUsedException(String mensage){
		super(mensage);
	}

}
