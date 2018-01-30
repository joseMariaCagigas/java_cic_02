package es.cic.curso.pruebas.cine.excepciones;

public class CineException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 416970584623969690L;
		public CineException(String mensajeError){
		super(mensajeError);
	}
}
