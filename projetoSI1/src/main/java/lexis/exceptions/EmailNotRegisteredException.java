package lexis.exceptions;

public class EmailNotRegisteredException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6606936879242246816L;

	public EmailNotRegisteredException(String mensage){
		super(mensage);
	}
}
