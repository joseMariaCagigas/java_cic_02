package es.cic.curso.grupo6.ejercicio024;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorCine;

public class GestionSala extends HorizontalLayout{

	private VerticalLayout listas;
	private VerticalLayout opciones;
	private Grid gridSalas;
	private Button abrir;
	private Button cerrar;
	private Sala p;
	
	private ServicioGestorCine servicioGestorCine;
	
	public GestionSala(){
		servicioGestorCine = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorCine.class);
		abrir = new Button("Abrir");
		cerrar = new Button("Cerrar");
		listas = new VerticalLayout();
		opciones = new VerticalLayout();
		gridSalas = new Grid();
		gridSalas.setColumns("id","capacidad","abierto");
        cargaGridVenta();
        listas.addComponent(gridSalas);
        opciones.setVisible(false);
        opciones.addComponents(abrir,cerrar);
        
        opciones.setHeight("120px");
        abrir.setWidth("80px");
        cerrar.setWidth("80px");
        
        gridSalas.addSelectionListener(e -> {
        		if (!e.getSelected().isEmpty() ) {
        			p=null;
	        		p = (Sala) e.getSelected().iterator().next();
	                opciones.setVisible(true);
	                
	                abrir.addClickListener(f -> {
	                	p = servicioGestorCine.obtenSala(p.getId());
	                	p.setAbierto(true);
	                	servicioGestorCine.modificaSala(p);
	                	cargaGridVenta(); });
	                cerrar.addClickListener(f ->{
	                	p = servicioGestorCine.obtenSala(p.getId());
	                	p.setAbierto(false);
	                	servicioGestorCine.modificaSala(p);
	                	cargaGridVenta(); });
        		} else{
        			opciones.setVisible(false);
        		}});
        
        addComponents(listas,opciones);
	}
	
	public void cargaGridVenta() {
		gridSalas.setContainerDataSource(
        		new BeanItemContainer<>(Sala.class, servicioGestorCine.listaSalas())
        );
	}
	
}
