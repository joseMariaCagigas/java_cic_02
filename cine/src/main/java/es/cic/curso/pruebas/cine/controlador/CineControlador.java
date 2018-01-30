package es.cic.curso.pruebas.cine.controlador;

import es.cic.curso.pruebas.cine.excepciones.CineException;
import es.cic.curso.pruebas.cine.repository.venta.Venta;

public interface CineControlador {

	Long vender(Long idSesion, int numEntradas) throws CineException;

	double calcularImporte(int numEntradas);

	boolean hayEntradas(Long idSesion, int numEntradas);

	boolean estaCerrada(Long idSesion);

	Venta cambiarEntradas(Long idVenta, Long idSesion) throws CineException;

	Long devolverEntradas(Long idVenta);

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#recaudarSala(java.lang.Long)
	 */
	double recaudarSala(Long idSala);

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#recaudarCine(java.lang.Long)
	 */
	double recaudarCine();

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#verSitiosLibres(java.lang.Long)
	 */
	int verSitiosLibres(Long idSesion);

}