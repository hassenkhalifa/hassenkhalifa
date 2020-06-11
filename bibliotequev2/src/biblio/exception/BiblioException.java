package biblio.exception;


public class BiblioException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BiblioException() {
		super("Pb sur l'application bibliothèque");
	}
	public BiblioException(String message) {
		super(message);
	}
}