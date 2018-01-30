package es.cic.curso.grupo6.ejercicio024;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;

import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.modelo.Venta;
import es.cic.curso.grupo6.ejercicio024.repositorio.RepositorioSesion;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorCine;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorTaquilla;

public class Devoluciones extends FormLayout {

	private Button accion;
	private DevolucionesDTO venta;
	private ServicioGestorCine	servicioGestorCine;
	private ServicioGestorTaquilla servicioGestorTaquilla;
	private RepositorioSesion repositorioSesion;
	private ConversorDTO conversor;
	
	public Devoluciones(MyUI padre) {
		 servicioGestorCine = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorCine.class);
		 servicioGestorTaquilla = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorTaquilla.class);
	        
		
		accion = new Button("Devolucion");
		accion.addClickListener(e -> {
			int asientos = venta.getnEntradas();
			Long numSesion = venta.getSesion();
			System.out.print("/////////////////"+venta.getSesion());
//			
			Sesion sesion = servicioGestorCine.obtenSesion(venta.getSesion());
			int asientosOcup = sesion.getAsientosOcupados() - asientos;
			sesion.setAsientosOcupados(asientosOcup);
			servicioGestorCine.modificaSesion(sesion);
			
			Venta v = servicioGestorTaquilla.buscar(venta.getId());
			padre.borraVenta(v);
		});
		
		addComponents(accion);
		setVenta(null);
	}

	public void setVenta(DevolucionesDTO venta) {
		this.setVisible(venta != null);
		this.venta = venta;

		if (venta != null) {
			BeanFieldGroup.bindFieldsUnbuffered(venta, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Venta(), this);
		}
	}
}
