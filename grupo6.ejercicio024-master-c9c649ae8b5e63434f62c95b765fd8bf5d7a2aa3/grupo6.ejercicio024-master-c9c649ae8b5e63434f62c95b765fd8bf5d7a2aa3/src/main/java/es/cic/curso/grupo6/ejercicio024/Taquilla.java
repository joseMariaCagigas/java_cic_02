package es.cic.curso.grupo6.ejercicio024;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorTaquilla;


public class Taquilla extends FormLayout {

	public Taquilla(){
		
	}
	
	protected TextField entradas;
	private Button accion;
	private Button verPrecio;
		
	private VentasDTO sesion;

	
	private ServicioGestorTaquilla servicioGestorTaquilla;
	
	private Label mensajePrecio;
	private MyUI padre;
	private Label lab;
	
	public Taquilla(MyUI padre) {
		this.padre = padre;
		servicioGestorTaquilla = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorTaquilla.class);
		entradas = new TextField("Nº entradas : ");
		accion = new Button("Comprar");
		
		verPrecio = new Button("Ver Precio");
		
		accion.addClickListener(e -> 
		{
			int asientos = 0;
			try{
			asientos = Integer.parseInt(entradas.getValue());
			}catch(Exception e2){
				Notification n = new Notification("Error al vender las entradas");
				n.show(Page.getCurrent());
			}
//			asientos += sesion.getAsientosOcupados();

			try {
				servicioGestorTaquilla.vendeEntradas(sesion.getId(),asientos);
			} catch (Exception e1) {
				
				Notification n = new Notification("Error al vender entradas");
				n.setDelayMsec(2000);
				
				n.show(Page.getCurrent());
			}
			padre.cargaGridVenta();
			
			

		});
		verPrecio.addClickListener(e -> 
		{
			float valor =  verPrecioEntradas();
			mensajePrecio = new Label("El precio sería : " + valor+ "€");
			addComponent(mensajePrecio);
			
		});
		
		
		addComponents(entradas, accion, verPrecio);
		
		setSesion(null);
	}

	public void setSesion(VentasDTO sesion) {
		this.setVisible(sesion != null);
		this.sesion = sesion;

		if (sesion != null) {
			BeanFieldGroup.bindFieldsUnbuffered(sesion, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Sesion(), this);
		}
	}
	
	public float verPrecioEntradas(){
		int asientos = Integer.parseInt(entradas.getValue());
		float resultado = asientos * 5L;
		if(asientos >= 5){
			resultado = (float) (resultado * 0.9);
			
		}
		return  resultado;
	}
}

