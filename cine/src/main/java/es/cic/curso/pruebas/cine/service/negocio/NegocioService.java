package es.cic.curso.pruebas.cine.service.negocio;

import es.cic.curso.pruebas.cine.excepciones.CineException;
import es.cic.curso.pruebas.cine.repository.venta.Venta;

public interface NegocioService {

	Long vender(Long idSesion, int numEntradas) throws CineException;

	double calcularImporte(int numEntradas);

	boolean hayEntradas(Long idSesion, int numEntradas);

	boolean estaCerrada(Long idSesion);

	void cerrarSesion(Long idSesion);

	Venta cambiarEntradas(Long idVenta, Long idSesion) throws CineException;

	Long devolverEntradas(Long idVenta);

	double recaudarCine();

	double recaudarSala(Long idSala);

	int verSitiosLibres(Long idSesion);

}