package es.cic.curso.grupo6.ejercicio024;

import org.springframework.web.context.ContextLoader;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

import es.cic.curso.grupo6.ejercicio024.modelo.Cine;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorTaquilla;


public class GestionCine extends HorizontalLayout {
	
	private HorizontalLayout botonesCine;
	private Button abrir;
	private Button cerrar;
	Cine cine = new Cine(1L, true);
	private ServicioGestorTaquilla servicioGestorTaquilla;
	
	public GestionCine(Administrador padre){
		
		servicioGestorTaquilla = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorTaquilla.class);
		abrir = new Button("Abrir Cine");
		cerrar = new Button("Cerrar Cine");
		botonesCine = new HorizontalLayout();

		boolean estado = cine.isEstado();
		   
		if (estado){ 
			botonesCine.addComponent( cerrar);
			}else{
				botonesCine.addComponent(abrir);	
				}
			
		  abrir.addClickListener(f ->
          {
        	  servicioGestorTaquilla.cambiarEstadoCine(cine, true);
        	  botonesCine.removeAllComponents();
        	  padre.habilitarBotones();
        	  botonesCine.addComponents(cerrar);
          });
          cerrar.addClickListener(f ->
          {
          	  servicioGestorTaquilla.cambiarEstadoCine(cine, false);
          	  botonesCine.removeAllComponents();
          	  padre.deshabilitarBotones();
          	  botonesCine.addComponents(abrir);
          });
	
          addComponent(botonesCine);
	}

}
