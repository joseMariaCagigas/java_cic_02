package es.cic.curso.pruebas.cine.excepciones;

public class SesionCerradaException extends CineException{

/**
	 * 
	 */
	private static final long serialVersionUID = 9139994127570048176L;

public SesionCerradaException(String mensajeError){
	super(mensajeError);
}
}
