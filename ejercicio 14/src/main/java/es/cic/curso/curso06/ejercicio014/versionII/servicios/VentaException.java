package es.cic.curso.curso06.ejercicio014.versionII.servicios;

public class VentaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentaException() {
		super();
	}

	public VentaException(String message, Throwable cause) {
		super(message, cause);
	}

	public VentaException(String message) {
		super(message);
	}
	
	
}
