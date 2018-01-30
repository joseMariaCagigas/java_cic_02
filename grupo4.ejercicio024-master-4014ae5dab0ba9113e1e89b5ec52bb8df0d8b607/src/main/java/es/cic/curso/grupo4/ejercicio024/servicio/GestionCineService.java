package es.cic.curso.grupo4.ejercicio024.servicio;

import java.util.List;

import es.cic.curso.grupo4.ejercicio024.dominio.Sala;
import es.cic.curso.grupo4.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo4.ejercicio024.dominio.Venta;

public interface GestionCineService {
	
	final double PRECIOBBDD = 5.0;
	final double PRECIOBBDDREBAJADO = 4.5;

 
	double calculaPrecioTotal(Sala sala1, Sala sala2, Sala sala3); // relacion entidades
	
	 
	boolean hayAsientos(int cuantas, Sesion sesion); //relacion entidades

 	
	Venta venderEntradas(int numEntradas, Sesion sesion, Sala sala); //mediantes entidades
 
	
	boolean comprobarEstadoSesion(Sesion sesion);

	
	Sesion cambiarEstadoSesion(Sesion sesion);

	
	boolean hayDescuento(int numeroEntradas);


	double calcularPrecio(int numeroEntradas);


	boolean puedoDevolverEntradas(Venta ventaAnterior);


	Venta devolverEntradas(Venta ventaACambiar);


	void cambioEntradas(Venta ventaACambiar, Sala sala, Sesion sesion);


	Sesion actualizarSesion(Sesion sesion);


	Venta actualizarVenta(Venta venta);


	Sala actualizarSala(Sala sala);


	List<Sesion> obtenerSesiones();


	List<Sala> obtenerSalas();


	List<Venta> obtenerVentas();


	Sala aniadirSala(Sala nueva);


	Sesion aniadirSesion(Sesion nueva);


	Venta aniadirVenta(Venta nueva);


	void inicializar();


	double beneficiosTotales();
 

}