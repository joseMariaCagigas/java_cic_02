package es.cic.curso.curso10.curso10.ejercicio023;

import org.springframework.web.context.ContextLoader;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import es.cic.curso.curso10.curso10.ejercicio023.servicio.*;
import es.cic.curso.curso10.curso10.ejercicio023.dominio.*;

@Theme("mytheme")
public class MyUI extends UI
{
	private static final long serialVersionUID = -8725441044024774812L;
	
	private Grid maestro;
	private PeliculaForm detalle;
	
	private PeliculaNuevaForm detalleNueva;
	private Button nueva;

	private CineService cineService;
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        //ContextLoader (Service)
        cineService = ContextLoader.getCurrentWebApplicationContext().getBean(CineServiceImpl.class);

//////////
Button botonMensaje = new Button("Bienvenido al mundo real");
botonMensaje.addClickListener(e -> Notification.show(cineService.dameMensaje()));
layout.addComponent(botonMensaje);
//////////

		//Añade Peliculas Iniciales
		cineService.nuevaPelicula(new Pelicula("Hachiko", "Un Perro", 100));
		cineService.nuevaPelicula(new Pelicula("Truman", "Otro Perro", 180));

        maestro = new Grid();
        maestro.setColumns("titulo", "director", "duracion");
 
        // CREAR NUEVA
        nueva = new Button("Nueva Pelicula");
        nueva.addClickListener( e-> {
        	detalleNueva.setPelicula(new Pelicula());
        });        
        detalleNueva = new PeliculaNuevaForm(this);
        layout.addComponent(nueva);
        
        cargaGrid();
        
        // ACTUALIZAR Y BORRAR
        maestro.addSelectionListener(e -> 
        	{
        		Pelicula p = null;
        		if (!e.getSelected().isEmpty() ) {
	        		p = (Pelicula) e.getSelected().iterator().next();
        		} 
        		detalle.setPelicula(p);
        		cineService.editaPelicula(p.getId(),p);
        	});
        detalle = new PeliculaForm(this);
        
        layout.addComponents(maestro, detalle, detalleNueva);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

	public void cargaGrid() {
		maestro.setContainerDataSource(
        		new BeanItemContainer<>(Pelicula.class, cineService.getPeliculas())
        );
	}
	
	//nuevaPelicula
	public void nuevaPelicula(Pelicula pelicula)
	{
		cineService.nuevaPelicula(pelicula);
	}

	//borraPelicula
	public void borraPelicula(Pelicula pelicula)
	{
		cineService.borraPelicula(pelicula.getId());
	}
	
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -6179281841152537850L;}
}
