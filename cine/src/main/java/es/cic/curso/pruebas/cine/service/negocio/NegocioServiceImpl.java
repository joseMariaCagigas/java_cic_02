package es.cic.curso.pruebas.cine.service.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.pruebas.cine.excepciones.CineException;
import es.cic.curso.pruebas.cine.excepciones.NoHayMasEntradasException;
import es.cic.curso.pruebas.cine.excepciones.SesionCerradaException;
import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;
import es.cic.curso.pruebas.cine.repository.venta.Venta;
import es.cic.curso.pruebas.cine.service.sala.SalaService;
import es.cic.curso.pruebas.cine.service.sesion.SesionService;
import es.cic.curso.pruebas.cine.service.ventas.VentasService;

@Service
public class NegocioServiceImpl implements NegocioService {

	@Autowired
	SesionService sesionService;
	
	@Autowired
	SalaService salaService;
	
	@Autowired
	VentasService ventaService;
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.negocio.Negocio#vender(java.lang.Long, int)
	 */
	@Override
	public Long vender(Long idSesion, int numEntradas) throws CineException{
		Sesion sesion = sesionService.obtenerSesion(idSesion);
		if(estaCerrada(idSesion)){
			throw new SesionCerradaException("La sesion está cerrada");
		}
		if(!hayEntradas(idSesion, numEntradas)){
			throw new NoHayMasEntradasException("No hay entradas suficientes");
		}
		sesion.setAsientosOcupados(sesion.getAsientosOcupados() + numEntradas);
		sesionService.actualizarSesion(sesion);
		Long idSala = sesion.getSala().getId();
		double importe = calcularImporte(numEntradas);
		
		return ventaService.aniadirVenta(idSala, idSesion, numEntradas, importe);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.negocio.Negocio#calcularImporte(int)
	 */
	@Override
	public double calcularImporte(int numEntradas){
		Sesion sesion = new Sesion();
		double importe = sesion.PRECIO * numEntradas;
		if(numEntradas < 5){
			return importe;
		}else{
			return ((importe)-((importe * 10)/100));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.negocio.Negocio#hayEntradas(java.lang.Long, int)
	 */
	@Override
	public boolean hayEntradas(Long idSesion, int numEntradas){
		Sesion sesion = sesionService.obtenerSesion(idSesion);
		Sala sala = salaService.obtenerSala(sesion.getSala().getId());
		int asientosLibres = sala.getAforo() - sesion.getAsientosOcupados();
		
		if(asientosLibres >= numEntradas){
			return true;
		}
		
		return false;
	}
		/* (non-Javadoc)
		 * @see es.cic.curso.pruebas.cine.negocio.Negocio#estaCerrada(java.lang.Long)
		 */
		@Override
		public boolean estaCerrada(Long idSesion){
		Sesion sesion = sesionService.obtenerSesion(idSesion);
		if(sesion.isEstaCerrada()){
			return true;
		}
		return false;
	}
		/* (non-Javadoc)
		 * @see es.cic.curso.pruebas.cine.negocio.Negocio#cerrarSesion(java.lang.Long)
		 */
		@Override
		public void cerrarSesion(Long idSesion){
		Sesion sesion = sesionService.obtenerSesion(idSesion);
		sesion.setEstaCerrada(true);
	}
		/* (non-Javadoc)
		 * @see es.cic.curso.pruebas.cine.negocio.Negocio#cambiarEntradas(java.lang.Long, java.lang.Long)
		 */
		@Override
		public Venta cambiarEntradas(Long idVenta, Long idSesion) throws CineException{
		Venta venta = ventaService.obtenerVenta(idVenta);
		Sesion sesion = sesionService.obtenerSesion(venta.getSesion().getId());
		Sesion sesionNueva = sesionService.obtenerSesion(idSesion);
		
		if(estaCerrada(idSesion)){
			throw new SesionCerradaException("La sesion está cerrada");
		}
		if(!hayEntradas(idSesion, venta.getNumEntradas())){
			throw new NoHayMasEntradasException("No hay entradas suficientes");
		}
		
		sesion.setAsientosOcupados(sesion.getAsientosOcupados() - venta.getNumEntradas());
		sesionNueva.setAsientosOcupados(sesionNueva.getAsientosOcupados() + venta.getNumEntradas());
		venta.setSesion(sesionNueva);
		venta.setSesion(sesionNueva);
		Venta ventaMod = ventaService.actualizarVenta(venta);
		
		return ventaMod;
		
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.negocio.Negocio#devolverEntradas(java.lang.Long)
	 */
	@Override
	public Long devolverEntradas(Long idVenta){
		Venta venta = ventaService.obtenerVenta(idVenta);
		ventaService.borrarVenta(idVenta);
		
		return venta.getId();
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.negocio.Negocio#recaudarCine()
	 */
	@Override
	public double recaudarCine(){
		List<Venta>ventas = ventaService.obtenerVentas();
		double importe = 0.0;
		for(Venta venta :  ventas){
			importe += venta.getImporte();
		}

		return importe;
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.negocio.Negocio#recaudarSala(java.lang.Long)
	 */
	@Override
	public double recaudarSala(Long idSala){
		List<Venta>ventas = ventaService.obtenerVentas();
		double importe = 0.0;
		for(Venta venta :  ventas){
			if(venta.getSala().getId() == idSala){
				importe += venta.getImporte();
			}
		}

		return importe;
	}
	

	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.negocio.Negocio#verSitiosLibres(java.lang.Long)
	 */
	@Override
	public int verSitiosLibres(Long idSesion){
		Sesion sesion = sesionService.obtenerSesion(idSesion);
		Sala sala = salaService.obtenerSala(sesion.getSala().getId());
		int sitiosLibres = sala.getAforo() - sesion.getAsientosOcupados();
		return sitiosLibres;
	}
	
}