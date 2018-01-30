package es.cic.curso.pruebas.cine.excepciones;

public class NoHayMasEntradasException extends CineException{

/**
	 * 
	 */
	private static final long serialVersionUID = 9139994127570048176L;

public NoHayMasEntradasException(String mensajeError){
	super(mensajeError);
}
}
