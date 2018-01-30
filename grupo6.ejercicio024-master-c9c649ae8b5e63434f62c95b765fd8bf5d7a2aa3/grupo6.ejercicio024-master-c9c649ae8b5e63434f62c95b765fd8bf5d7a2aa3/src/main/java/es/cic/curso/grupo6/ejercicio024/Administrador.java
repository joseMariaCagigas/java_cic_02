package es.cic.curso.grupo6.ejercicio024;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class Administrador extends HorizontalLayout {
	private static final String ANCHO_CONTENIDO_ADMINISTRACION = "1100px";
	private static final String ANCHO_BOTONES_ADMINISTRACION = "200px";
	private Button recaudacion;
	private Button historicoVentas;
	private Button abrirSala;
	private Button abrirCine;
	private MyUI padre;
	
	public Administrador(MyUI padre){
		final HorizontalLayout layout3 = new HorizontalLayout();
		
		this.padre = padre;
		final VerticalLayout layoutBotonesAdministracion = new VerticalLayout();
		
		final HorizontalLayout layoutRecaudacion = new GestionRecaudacion();
		final HorizontalLayout layoutHistoricoVentas = new HistoricoVentas();
		final HorizontalLayout layoutAbrirSala = new GestionSala();
		final HorizontalLayout layoutAbrirCine = new GestionCine(this);
		
		layout3.setMargin(true);
		layout3.setWidth("1400px");
			
		
		layoutHistoricoVentas.setWidth(ANCHO_CONTENIDO_ADMINISTRACION);
		layoutRecaudacion.setWidth(ANCHO_CONTENIDO_ADMINISTRACION);
		layoutAbrirSala.setWidth(ANCHO_CONTENIDO_ADMINISTRACION);
		layoutAbrirCine.setWidth(ANCHO_CONTENIDO_ADMINISTRACION);
		
        recaudacion = new Button("Recaudación");
        historicoVentas = new Button("Histórico de ventas");
        abrirSala = new Button("Abrir / Cerrar Sala");
        abrirCine = new Button("Abrir / Cerrar Cine");

        recaudacion.addClickListener(e -> {
        	layout3.addComponent(layoutRecaudacion);
        	layout3.removeComponent(layoutHistoricoVentas);
            layout3.removeComponent(layoutAbrirCine);
            layout3.removeComponent(layoutAbrirSala);		
        });
       
        
        historicoVentas.addClickListener(e -> {
        	layout3.addComponent(layoutHistoricoVentas);  
	        layout3.removeComponent(layoutRecaudacion);
	        layout3.removeComponent(layoutAbrirCine);
	        layout3.removeComponent(layoutAbrirSala);
	    	
        });
        
        
        abrirSala.addClickListener(e -> {
        	layout3.addComponent(layoutAbrirSala);
        	
	        layout3.removeComponent(layoutRecaudacion);
	        layout3.removeComponent(layoutHistoricoVentas);
	        layout3.removeComponent(layoutAbrirCine);
	        
	       
        });
        
        abrirCine.addClickListener(e -> {
        	layout3.addComponent(layoutAbrirCine);
        	
	        layout3.removeComponent(layoutRecaudacion);
	        layout3.removeComponent(layoutHistoricoVentas);
	        layout3.removeComponent(layoutAbrirSala);
	         
	       
        });
	

		layoutBotonesAdministracion.addComponents(recaudacion, historicoVentas, abrirSala, abrirCine );
		
		layout3.addComponents(layoutBotonesAdministracion );
		addComponent(layout3);
	
		recaudacion.setWidth(ANCHO_BOTONES_ADMINISTRACION);
		historicoVentas.setWidth(ANCHO_BOTONES_ADMINISTRACION);
		abrirCine.setWidth(ANCHO_BOTONES_ADMINISTRACION);
		abrirSala.setWidth(ANCHO_BOTONES_ADMINISTRACION);
	
		layoutBotonesAdministracion.setHeight("250px");
		
	
	}
	
	public void deshabilitarBotones(){
		padre.deshabilitarVentas();
		historicoVentas.setEnabled(false);
		abrirSala.setEnabled(false);
	
	}
	
	public void habilitarBotones(){
		padre.habilitarVentas();
		historicoVentas.setEnabled(true);
		abrirSala.setEnabled(true);
	}
}


