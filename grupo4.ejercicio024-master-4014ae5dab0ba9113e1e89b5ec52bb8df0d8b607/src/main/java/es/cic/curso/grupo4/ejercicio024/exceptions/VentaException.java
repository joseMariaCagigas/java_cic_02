package es.cic.curso.grupo4.ejercicio024.exceptions;


public class VentaException extends RuntimeException {

	private static final long serialVersionUID = 8157985888896931344L;

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
