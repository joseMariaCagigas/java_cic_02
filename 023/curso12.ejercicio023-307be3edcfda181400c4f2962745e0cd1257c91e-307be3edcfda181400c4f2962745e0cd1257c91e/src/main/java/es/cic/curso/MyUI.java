package es.cic.curso;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;
import es.cic.curso.curso12.ejercicio023.servicio.PeliculaService;

import java.util.ArrayList;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	private Grid maestro;
	private PeliculaForm detalle;
	
	private List<Pelicula> listaPeliculas;
	private Button addBtn;

	private PeliculaService peliService;
	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        peliService = ContextLoader.getCurrentWebApplicationContext().getBean(PeliculaService.class);
        
     
        maestro = new Grid();
        maestro.setColumns("titulo","director","interprete","productora","year","duracion","genero");
        maestro.setSizeFull();
        

        maestro.setFrozenColumnCount(1);
        maestro.setSelectionMode(SelectionMode.SINGLE);
        
        
        maestro.addSelectionListener(e -> 
        	{
        		Pelicula p = null;
        		if (!e.getSelected().isEmpty() ) {
	        		p = (Pelicula) e.getSelected().iterator().next();
	        		addBtn.setVisible(false);
        		} else{
	        		addBtn.setVisible(true);

        		}
        		detalle.setPelicula(p);
        	});
        
        detalle = new PeliculaForm(this);
        
        addBtn = new Button("Añadir película");
        addBtn.addClickListener(e->aniadirGrid());
        addBtn.setIcon(FontAwesome.DELICIOUS);
        
        
        layout.addComponents(maestro, detalle,addBtn);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setWidth("100%");
       
        
        
        setContent(layout);
        
        cargaGrid(null);
    }

	public void cargaGrid(Pelicula peli) {
		if (peli != null){
			peliService.actualizarPelicula(peli);
		}

		
		listaPeliculas = peliService.obtenerPeliculas();

		maestro.setContainerDataSource(
        		new BeanItemContainer<>(Pelicula.class, listaPeliculas)
        );
		detalle.setPelicula(null);
	}
	
	public void aniadirGrid(){
		Pelicula p = new Pelicula("","","","",0,0,"");
		detalle.setPelicula(p);
		maestro.setContainerDataSource(
				new BeanItemContainer<>(Pelicula.class,listaPeliculas));
	}
	
	public void borrarGrid(Pelicula peli){
		listaPeliculas.remove(peli);
		maestro.setContainerDataSource(
				new BeanItemContainer<>(Pelicula.class,listaPeliculas));
		Long peliId = peli.getId();
		peliService.borrarPelicula(peliId);
	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
