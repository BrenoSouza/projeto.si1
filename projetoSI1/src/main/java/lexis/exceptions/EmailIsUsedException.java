package lexis.exceptions;

public class EmailIsUsedException extends RuntimeException {

	private static final long serialVersionUID = 8982724515700128573L;

	public EmailIsUsedException(String mensage) {
		super(mensage);
	}
}
