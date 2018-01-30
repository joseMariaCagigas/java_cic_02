package es.cic.curso.grupo2.ejercicio024.servicio;

import static es.cic.curso.grupo2.ejercicio024.dominio.Sesion.PRECIO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo2.ejercicio024.dominio.*;

@Transactional
@Service
public class CineServiceImpl implements CineService
{
	@Autowired
	SalaService salaService;
	
	@Autowired
	SesionService sesionService;
	
	@Autowired
	PeliculaService peliculaService;
	
	@Autowired
	VentaService ventaService;
	
	
	/* (non-Javadoc)
	 * @see es.cic.curso.grupo2.ejercicio024.servicio.CineService#venderEntradas(java.lang.Long, int)
	 */
	@Override
	public Long venderEntradas(Long sesionId, int numEntradas) throws NotEnoughException{
		if(!comprobarDisponibilidad(sesionId, numEntradas)){
			throw new NotEnoughException("No hay entradas suficientes", null);
		}
		
		Sesion sesion = sesionService.leeSesion(sesionId);
		sesion.setAsientosDisponibles(sesion.getAsientosDisponibles() - numEntradas);
		
		double importe = calcularImporte(numEntradas);
		Venta v = new Venta(numEntradas, sesion, importe);
		Venta venta = ventaService.nuevaVenta(v);
		
		return venta.getId();
		
		}
	
	private boolean comprobarDisponibilidad(Long sesionId, int numEntradas){
		Sesion sesion = sesionService.leeSesion(sesionId);
		return (sesion.getAsientosDisponibles() >= numEntradas);
	}
	
	@Override
	public double calcularImporte(int numEntradas){
		if(numEntradas < 5){
			return numEntradas * PRECIO;
		}else{
			return (numEntradas * PRECIO) - (((numEntradas * PRECIO)*10)/100);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.grupo2.ejercicio024.servicio.CineService#listarVentas()
	 */
	@Override
	public List listarVentas(){
		return ventaService.getVentas();
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.grupo2.ejercicio024.servicio.CineService#recaudarSala(java.lang.Long)
	 */
	@Override
	public  double recaudarSala(Long salaId){
		List<Venta>ventas = ventaService.getVentas();
		Sala sala = salaService.leeSala(salaId);
		double recaudacion = 0.0;
		for(Venta v :  ventas){
			if(v.getSesionEntrada().getSala().getId() == salaId){
				recaudacion += v.getImporte();
			}
		}
			
		return recaudacion;
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.grupo2.ejercicio024.servicio.CineService#recaudarCine()
	 */
	@Override
	public  double recaudarCine(){
		List<Venta>ventas = ventaService.getVentas();
		double recaudacion = 0.0;
		for(Venta v :  ventas){
			recaudacion += v.getImporte();
		}
			
		return recaudacion;
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.grupo2.ejercicio024.servicio.CineService#devolver(java.lang.Long)
	 */
	@Override
	public double devolver(Long ventaId){
		Venta venta = ventaService.leeVenta(ventaId);
		double importe = venta.getImporte();
		Sesion sesion = sesionService.leeSesion(venta.getSesionEntrada().getId());
		sesion.setAsientosDisponibles(sesion.getAsientosDisponibles() + venta.getNumeroEntradas());
		ventaService.borraVenta(ventaId);
		return importe;
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.grupo2.ejercicio024.servicio.CineService#cambio(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Long cambio(Long ventaId, Long idSesionNueva) throws NotEnoughException{
		Venta venta = ventaService.leeVenta(ventaId);
		Sesion sesion = sesionService.leeSesion(venta.getSesionEntrada().getId());
		Sesion sesionNueva = sesionService.leeSesion(idSesionNueva);
		
		if(!comprobarDisponibilidad(idSesionNueva, venta.getNumeroEntradas())){
			throw new NotEnoughException("No hay entradas suficientes", null);
		}
		sesion.setAsientosDisponibles(sesion.getAsientosDisponibles() + venta.getNumeroEntradas());
		sesionNueva.setAsientosDisponibles(sesionNueva.getAsientosDisponibles() - venta.getNumeroEntradas());
		venta.setSesionEntrada(sesionNueva);
		
		return venta.getId(); 
	}
	
	//Clase Private de Excepción - NotEnoughException
	public class NotEnoughException extends CineException
	{
		private static final long serialVersionUID = -2189849128980720273L;

		public NotEnoughException(String message, Throwable cause) {
	        super(message,cause);
	    }
	}
	
	//Clase Private de Excepción - SesionCerradaException
	public class SesionCerradaException extends CineException
	{
		private static final long serialVersionUID = 3173060136767946795L;

		public SesionCerradaException(String message, Throwable cause) {
	        super(message, cause);
	    }
	}
	
	//Clase Private de Excepción - CineException
	public class CineException extends Exception
	{
		private static final long serialVersionUID = 3065901188022770089L;

		public CineException(String message, Throwable cause) {
	        super(message,cause);
	    }
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.grupo2.ejercicio024.servicio.CineService#incicializaCine()
	 */
	@Override
	public List incicializaCine() throws NotEnoughException{
		Pelicula peli1 = new Pelicula("Torrente");
		Pelicula peli2 = new Pelicula("Red");
		Pelicula peli3 = new Pelicula("Red 2");
		
		peliculaService.nuevaPelicula(peli1);
		peliculaService.nuevaPelicula(peli2);
		peliculaService.nuevaPelicula(peli3);
		
		Sala sala1 = new Sala(1, 100);
		Sala sala2 = new Sala(2, 50);
		Sala sala3 = new Sala(3, 30);
		
		Long isSala1 = salaService.nuevaSala(sala1);
		Long isSala2 = salaService.nuevaSala(sala2);
		Long isSala3 = salaService.nuevaSala(sala3);
		
		Sesion sesion1 = new Sesion(peli1, sala1, 17.00);
		Sesion sesion2 = new Sesion(peli1, sala1, 19.00);
		Sesion sesion3 = new Sesion(peli1, sala1, 21.00);
		
		Sesion sesion4 = new Sesion(peli2, sala2, 17.00);
		Sesion sesion5 = new Sesion(peli2, sala2, 19.00);
		Sesion sesion6 = new Sesion(peli2, sala2, 21.00);
		
		Sesion sesion7 = new Sesion(peli3, sala3, 17.00);
		Sesion sesion8 = new Sesion(peli3, sala3, 19.00);
		Sesion sesion9 = new Sesion(peli3, sala3, 21.00);
		
		sesionService.nuevaSesion(sesion1);
		sesionService.nuevaSesion(sesion2);
		sesionService.nuevaSesion(sesion3);
		sesionService.nuevaSesion(sesion4);
		sesionService.nuevaSesion(sesion5);
		sesionService.nuevaSesion(sesion6);
		sesionService.nuevaSesion(sesion7);
		sesionService.nuevaSesion(sesion8);
		sesionService.nuevaSesion(sesion9);
		
		venderEntradas(sesion1.getId(), 1);
		venderEntradas(sesion2.getId(), 1);
		venderEntradas(sesion3.getId(), 1);
		venderEntradas(sesion4.getId(), 1);
		venderEntradas(sesion5.getId(), 1);
		venderEntradas(sesion6.getId(), 1);
		venderEntradas(sesion7.getId(), 1);
		venderEntradas(sesion8.getId(), 1);
		venderEntradas(sesion9.getId(), 1);
		
		List<Long>idSalas = new ArrayList<>();
		idSalas.add(isSala1);
		idSalas.add(isSala2);
		idSalas.add(isSala3);
		
		return idSalas;
	}
}