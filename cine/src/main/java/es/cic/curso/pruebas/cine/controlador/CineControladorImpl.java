package es.cic.curso.pruebas.cine.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.cic.curso.pruebas.cine.excepciones.CineException;
import es.cic.curso.pruebas.cine.repository.venta.Venta;
import es.cic.curso.pruebas.cine.service.negocio.NegocioService;

@Controller
public class CineControladorImpl implements CineControlador {

	@Autowired
	NegocioService negocioService;
	
	//Creamos todos los métodos que usaremos desde el exterior
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#vender(java.lang.Long, int)
	 */

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#vender(java.lang.Long, int)
	 */
	@Override
	public Long vender(Long idSesion, int numEntradas) throws CineException{
		return negocioService.vender(idSesion, numEntradas);
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#calcularImporte(int)
	 */

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#calcularImporte(int)
	 */
	@Override
	public double calcularImporte(int numEntradas){
		return negocioService.calcularImporte(numEntradas);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#hayEntradas(java.lang.Long, int)
	 */

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#hayEntradas(java.lang.Long, int)
	 */
	@Override
	public boolean hayEntradas(Long idSesion, int numEntradas){
		return negocioService.hayEntradas(idSesion, numEntradas);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#estaCerrada(java.lang.Long)
	 */

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#estaCerrada(java.lang.Long)
	 */
	@Override
	public boolean estaCerrada(Long idSesion){
		return negocioService.estaCerrada(idSesion);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#cambiarEntradas(java.lang.Long, java.lang.Long)
	 */

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#cambiarEntradas(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Venta cambiarEntradas(Long idVenta, Long idSesion) throws CineException{
		return negocioService.cambiarEntradas(idVenta, idSesion);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#devolverEntradas(java.lang.Long)
	 */

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#devolverEntradas(java.lang.Long)
	 */
	@Override
	public Long devolverEntradas(Long idVenta){
		return negocioService.devolverEntradas(idVenta);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#recaudarSala(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#recaudarSala(java.lang.Long)
	 */
	@Override
	public double recaudarSala(Long idSala){
		return negocioService.recaudarSala(idSala);
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#recaudarCine(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#recaudarCine()
	 */
	@Override
	public double recaudarCine(){
		return negocioService.recaudarCine();
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#verSitiosLibres(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.controlador.CineControlador#verSitiosLibres(java.lang.Long)
	 */
	@Override
	public int verSitiosLibres(Long idSesion){
		return negocioService.verSitiosLibres(idSesion);
	}
	
	
}
