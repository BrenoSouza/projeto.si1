package lexis.exceptions;

public class EmailEmptyException extends RuntimeException {
	public EmailEmptyException(String mensage){
		super(mensage);
	}
}
