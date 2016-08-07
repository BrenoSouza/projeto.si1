package lexis.exceptions;

public class EmailIsUsedException extends RuntimeException {
	public EmailIsUsedException(String mensage){
		super(mensage);
	}
}
