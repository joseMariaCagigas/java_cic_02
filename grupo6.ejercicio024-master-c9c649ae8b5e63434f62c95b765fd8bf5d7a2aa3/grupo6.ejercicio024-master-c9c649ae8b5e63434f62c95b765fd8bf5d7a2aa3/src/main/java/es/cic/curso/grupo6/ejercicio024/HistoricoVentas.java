package es.cic.curso.grupo6.ejercicio024;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.grupo6.ejercicio024.modelo.Venta;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorTaquilla;


public class HistoricoVentas extends HorizontalLayout {
	
	private Grid maestro;
	private List<Venta> listaVentas;
	private ServicioGestorTaquilla servicioGestorTaquilla;
	private Button actualizar;
	
	public HistoricoVentas(){
		final VerticalLayout layout = new VerticalLayout();
		
		final VerticalLayout layoutActualizar = new VerticalLayout();
		maestro = new Grid();
	    maestro.setColumns("id", "Id_sesion", "numeroEntradas", "importe", "descuento");
	 
	    servicioGestorTaquilla = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorTaquilla.class);
	    actualizar = new Button("Actualizar");
	    
	    cargaGrid();
	
	    actualizar.addClickListener(e -> cargaGrid());
	    
	    layout.addComponents(maestro); 
	    layoutActualizar.addComponent(actualizar);
	
	    maestro.setWidth("500px");
	    maestro.setHeight("350px");
	   
	    addComponents(layout, layoutActualizar);
	}
	
	public void cargaGrid() {
		listaVentas = new ArrayList<>();
		
		listaVentas = servicioGestorTaquilla.listar();
		maestro.removeAllColumns();
		maestro.setContainerDataSource(
        		new BeanItemContainer<>(Venta.class, listaVentas )
        );
	}
	
	
}
