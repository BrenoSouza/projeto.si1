package lexis.exceptions;

public class EmailNotRegisteredException extends RuntimeException {
	public EmailNotRegisteredException(String mensage){
		super(mensage);
	}
}
