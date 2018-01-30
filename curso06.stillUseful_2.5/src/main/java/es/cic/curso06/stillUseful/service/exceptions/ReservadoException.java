package es.cic.curso06.stillUseful.service.exceptions;

public class ReservadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174526353929008832L;

	public ReservadoException(String reservado) {
		
		reservado = "Producto Reservado";
	}

}
