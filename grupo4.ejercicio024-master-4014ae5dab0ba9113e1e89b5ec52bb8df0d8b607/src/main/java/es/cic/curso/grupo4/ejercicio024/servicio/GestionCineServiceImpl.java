package es.cic.curso.grupo4.ejercicio024.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo4.ejercicio024.dominio.Sala;
import es.cic.curso.grupo4.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo4.ejercicio024.dominio.Venta;
import es.cic.curso.grupo4.ejercicio024.exceptions.VentaException;
import es.cic.curso.grupo4.ejercicio024.repositorio.SalaRepository;
import es.cic.curso.grupo4.ejercicio024.repositorio.SesionRepository;
import es.cic.curso.grupo4.ejercicio024.repositorio.VentaRepository;

@Transactional
@Service
public class GestionCineServiceImpl implements GestionCineService {


	@Autowired
	private VentaRepository ventaRepo;

	@Autowired
	private SalaRepository salaRepo;

	@Autowired
	private SesionRepository sesionRepo;

	@Override
	public Sesion aniadirSesion(Sesion nueva){	
		return sesionRepo.add(nueva);
	}

	@Override
	public Sala aniadirSala(Sala nueva){	
		return salaRepo.add(nueva);

	}

	@Override
	public Venta aniadirVenta(Venta nueva){	
		return ventaRepo.add(nueva);

	}

	@Override
	public List<Venta> obtenerVentas(){
		return ventaRepo.list();
	}

	@Override
	public List<Sala> obtenerSalas(){
		return salaRepo.list();
	}

	@Override
	public List<Sesion> obtenerSesiones(){
		return sesionRepo.list();
	}

	@Override
	public Sala actualizarSala(Sala sala){
		return salaRepo.update(sala);
	}

	@Override
	public Venta actualizarVenta(Venta venta){
		return ventaRepo.update(venta);
	}

	@Override
	public Sesion actualizarSesion(Sesion sesion){
		return sesionRepo.update(sesion);
	}


	@Override
	public double calcularPrecio(int numeroEntradas){

		if(numeroEntradas < 5)

		return numeroEntradas*PRECIOBBDD;
		
		else
			
		return numeroEntradas*PRECIOBBDDREBAJADO;
	}


	@Override
	public boolean hayAsientos(int cuantas,Sesion sesion){


		int butacasDisponibles = sesion.getButacasDisp();

		return butacasDisponibles>=cuantas;
	}


	@Override
	public Venta venderEntradas(int numEntradas,Sesion sesion,Sala sala)	{


		int disponibles  = sesion.getButacasDisp();

		if(numEntradas>disponibles){

			String mensaje = String.format("No existen en la sesion: %d entradas disponibles",
					numEntradas);
			throw new VentaException(mensaje);
		}
		//vendemos		

		int asientos = sesion.getButacasDisp();
		int nuevaDisponibilidad = asientos -numEntradas;
		sesion.setButacasDisp(nuevaDisponibilidad);

		double beneficiosAcumulados;
		double beneficiosTotales;

		if(hayDescuento(numEntradas)){ //si hay descuento

			double precioVenta = numEntradas*PRECIOBBDDREBAJADO;
			beneficiosAcumulados = precioVenta+sala.getRecaudacion();

			sala.setRecaudacion(beneficiosAcumulados);

			//nueva venta
			Venta venta = new Venta(numEntradas,sala,sesion,precioVenta,hayDescuento(numEntradas),true,false,false);
			ventaRepo.add(venta); //persisit
			sesionRepo.update(sesion);
			salaRepo.update(sala);
			
			beneficiosTotales = precioVenta+venta.getBeneficios();
			venta.setBeneficios(beneficiosTotales);
			ventaRepo.update(venta);
			return venta;

		}
		else { //si no hay descuento

			double precioVenta = numEntradas*PRECIOBBDD;
			beneficiosAcumulados = precioVenta+sala.getRecaudacion();
			sala.setRecaudacion(beneficiosAcumulados);

			//nueva venta
			Venta venta = new Venta(numEntradas,sala,sesion,precioVenta,hayDescuento(numEntradas),true,false,false);
			ventaRepo.add(venta); //persisit
			
			beneficiosTotales = precioVenta+venta.getBeneficios();
			venta.setBeneficios(beneficiosTotales);
			ventaRepo.update(venta);
			sesionRepo.update(sesion);
			salaRepo.update(sala);

			return venta;

		}


	}
 

	@Override
	public Venta devolverEntradas (Venta ventaACambiar){

		if(!puedoDevolverEntradas(ventaACambiar)){

 
			String mensaje = String.format("No puedes cambiar %d  estradas",
  
 
					ventaACambiar.getCuantasEntradas());
			throw new VentaException(mensaje);
		}

		//devolvemos

		double precioADevolver = ventaACambiar.getPrecio();
		int butacasDisponibles = ventaACambiar.getSesiones().getButacasDisp();
		int numeroDeButacasADevolver = ventaACambiar.getCuantasEntradas();



		int nuevaDisponibilidad = numeroDeButacasADevolver + butacasDisponibles;
		double nuevaRecaudacion = ventaACambiar.getSala().getRecaudacion() - precioADevolver;

		//guardamos los datos en las sesiones

		Sesion sesionActual = ventaACambiar.getSesiones(); // es solo 1 
		sesionActual.setButacasDisp(nuevaDisponibilidad);

		Sala salaActual = ventaACambiar.getSala();
		salaActual.setRecaudacion(nuevaRecaudacion);

		//creamos la Venta de tipo devolucion

		Venta ventaDevolucion = new Venta(ventaACambiar.getCuantasEntradas(),salaActual,sesionActual,(-1)*precioADevolver,ventaACambiar.isDescuento(),false,true,false);
		ventaRepo.add(ventaDevolucion);

		ventaACambiar.setDevuelta(true);
		ventaRepo.update(ventaACambiar);
		//actualizamos sala y sesion

		sesionRepo.update(sesionActual);
		salaRepo.update(salaActual);


		return ventaDevolucion;
	}

	@Override 
	public void cambioEntradas(Venta ventaACambiar,Sala salaNueva, Sesion sesionNueva){

		int entradas = ventaACambiar.getCuantasEntradas();
		//priemro devolvemos 

		devolverEntradas(ventaACambiar);

		// despues vendemos

		venderEntradas (entradas, sesionNueva, salaNueva);

	}


	@Override
	public double calculaPrecioTotal(Sala sala1, Sala sala2, Sala sala3){


		double recaudacionTotal = 	sala1.getRecaudacion() + 
				sala2.getRecaudacion() +
				sala3.getRecaudacion(); 

		return recaudacionTotal;		
	}
	
	
	@Override
	public double beneficiosTotales() {
		List<Sala> listaSala = salaRepo.list();
		double recaudaTotal = 0.0;
		
		for (Sala sala: listaSala) {
			recaudaTotal += sala.getRecaudacion();
		}
		return recaudaTotal;
	}

	@Override
	public boolean comprobarEstadoSesion(Sesion sesion){

		return sesion.isAbierto();

	}

	@Override
	public Sesion cambiarEstadoSesion(Sesion sesion){

		Sesion sesionAcambiar= sesion;

		if(sesionAcambiar.isAbierto()){

			sesion.setAbierto(false);
		}
		else{
			sesionAcambiar.setAbierto(true);
		}
		return sesionAcambiar;
	}

	@Override
	public boolean hayDescuento(int numeroEntradas){

		if(numeroEntradas>=5){
			return true;
		}
		else return false;

	}

	@Override

	//comprobamos el booleano de devolucion
	public boolean puedoDevolverEntradas(Venta ventaAnterior){

		//comprobamos que es una venta y no una devolucion
		 
		boolean estaDevuelta = ventaAnterior.isDevuelta();
		boolean esVenta = ventaAnterior.isEsVenta();
		boolean esDevolucion = ventaAnterior.isEsDevolucion();
	
		if(esVenta && !estaDevuelta && !esDevolucion){
			
			return true;
		}
		
		else return false;
	}

	//inicializamos el cine

	@Override
	public void inicializar(){


		//inicializamos las 3 salas

		Sala sala1 =new Sala(1,100,0);
		salaRepo.add(sala1);

		Sala sala2 =new Sala(2,50,0);
		salaRepo.add(sala2);

		Sala sala3 =new Sala(1,30,0);
		salaRepo.add(sala3);

		//3 sessiones por sala ,inicializamos las 3 abiertas

		Sesion sala1Sesion1 = new Sesion("Sala Gas Natural Fenosa",1,sala1.getButacasTotales(),sala1,true);
		sesionRepo.add(sala1Sesion1); 

		Sesion sala1Sesion2 = new Sesion("Sala Gas Natural Fenosa",2,sala1.getButacasTotales(),sala1,true);
		sesionRepo.add(sala1Sesion2); 

		Sesion sala1Sesion3 = new Sesion("Sala Gas Natural Fenosa",3,sala1.getButacasTotales(),sala1,true);
		sesionRepo.add(sala1Sesion3); 

		Sesion sala2Sesion1 = new Sesion("3D IMAX DIGITAL",1,sala2.getButacasTotales(),sala2,true);
		sesionRepo.add(sala2Sesion1); 

		Sesion sala2Sesion2 = new Sesion("3D IMAX DIGITAL",2,sala2.getButacasTotales(),sala2,true);
		sesionRepo.add(sala2Sesion2); 

		Sesion sala2Sesion3 = new Sesion("3D IMAX DIGITAL",3,sala2.getButacasTotales(),sala2,true);
		sesionRepo.add(sala2Sesion3); 

		Sesion sala3Sesion1 = new Sesion("Sala VIP",1,sala3.getButacasTotales(),sala3,true);
		sesionRepo.add(sala3Sesion1); 

		Sesion sala3Sesion2 = new Sesion("Sala VIP",2,sala3.getButacasTotales(),sala3,true);
		sesionRepo.add(sala3Sesion2); 

		Sesion sala3Sesion3 = new Sesion("Sala VIP",3,sala3.getButacasTotales(),sala3,true);
		sesionRepo.add(sala3Sesion3); 

	}
	 
}

