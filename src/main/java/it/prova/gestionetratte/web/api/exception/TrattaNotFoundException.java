package it.prova.gestionetratte.web.api.exception;

public class TrattaNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public TrattaNotFoundException(String message) {
		super(message);
	}
}
