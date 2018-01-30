package es.cic.curso.grupo6.ejercicio024;

import org.springframework.beans.factory.annotation.Autowired;

import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.modelo.Venta;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorCine;

public class ConversorDTO {

	@Autowired
	private ServicioGestorCine sut;
	
	public VentasDTO convierteVentas(Sesion s){
		VentasDTO v = new VentasDTO();
		v.setAsientosOcupados(s.getAsientosOcupados());
		v.setSala("Sala "+s.getSala().getId());
		v.setSesion("Sesion "+s.getId());
		v.setId(s.getId());
		v.setCapacidad(s.getSala().getCapacidad());
		return v;
	}
	
	public DevolucionesDTO convierteDevoluciones(Venta s){
		DevolucionesDTO d= new DevolucionesDTO();
		d.setnEntradas(s.getnEntradas());
		d.setSesion(s.getSesion().getId());
		d.setId(s.getId());
		return d;
	}
	
	public Venta convierteVenta(DevolucionesDTO s){
//		DevolucionesDTO d= new DevolucionesDTO();
		Venta v = new Venta();
		v.setnEntradas((short)s.getnEntradas());
//		v.setSesion(s.getSesion());
		v.setId(s.getId());
		return v;
	}
}
