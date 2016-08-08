package lexis.exceptions;

public class EmailEmptyException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5889482132169228748L;

	public EmailEmptyException(String mensage){
		super(mensage);
	}
}
