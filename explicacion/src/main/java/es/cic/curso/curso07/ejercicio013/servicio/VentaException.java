package es.cic.curso.curso07.ejercicio013.servicio;

public class VentaException extends RuntimeException {

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
