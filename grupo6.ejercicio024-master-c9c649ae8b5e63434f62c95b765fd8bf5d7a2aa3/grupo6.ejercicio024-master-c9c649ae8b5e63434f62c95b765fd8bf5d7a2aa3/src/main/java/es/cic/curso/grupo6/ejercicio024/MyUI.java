package es.cic.curso.grupo6.ejercicio024;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.modelo.Sesion;
import es.cic.curso.grupo6.ejercicio024.modelo.Venta;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorCine;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorTaquilla;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	private Button vender;
	private Button devoluciones;
	Grid gridVentaSesiones;
	Grid gridVentaDevoluciones;
	private Taquilla detalle;
	private Devoluciones devolucion;
	
	private ServicioGestorTaquilla servicioGestorTaquilla;

	private ServicioGestorCine servicioGestorCine;
	
	private Sala sala1 = new Sala();
	private Sala sala2 = new Sala();
	private Sesion s1 = new Sesion();
	private Sesion s2 = new Sesion();
	private final HorizontalLayout 	layout4 = new HorizontalLayout();
	private List<VentasDTO> listaVentas;
	private List<DevolucionesDTO> listaDevoluciones;
	
	private ConversorDTO conv ;

	
	List<Sesion> listaSalas;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		conv= new ConversorDTO();
		listaVentas = new ArrayList<>();
		listaDevoluciones = new ArrayList<>();
		
		sala1.setAbierto(true);
		sala1.setCapacidad(50);
		
		sala2.setAbierto(true);
		sala2.setCapacidad(150);
		
		
        servicioGestorCine = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorCine.class);
        servicioGestorTaquilla = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorTaquilla.class);

        servicioGestorCine.agregaSala(sala1);
		servicioGestorCine.agregaSala(sala2);
        
		servicioGestorCine.agregaSesion(sala1.getId(),s1 );
		servicioGestorCine.agregaSesion(sala2.getId(),s2 );


		

		final VerticalLayout layout = new VerticalLayout();

		TabSheet sample = new TabSheet();
		sample.setHeight(100.0f, Unit.PERCENTAGE);
		sample.setWidth(100.0f, Unit.PERCENTAGE);
		sample.addStyleName(ValoTheme.TABSHEET_FRAMED);
		sample.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

		final HorizontalLayout layout2 = new Administrador(this);

		layout2.setMargin(true);
		layout2.setHeight("500px");


	
		final VerticalLayout layoutBotonesVenta = new VerticalLayout();
		final VerticalLayout layoutContenidoVenta = new VerticalLayout();
		final VerticalLayout layoutVenta = new VerticalLayout();
		final VerticalLayout layoutDevolver = new VerticalLayout();
		layout.setMargin(true);
		
		
		layout4.setMargin(true);
		layout4.setHeight("500px");
		
		layoutBotonesVenta.setMargin(true);
		layoutBotonesVenta.setHeight("200px");
		
		layoutContenidoVenta.setMargin(true);
		layoutContenidoVenta.setWidth("700px");
		
		
        vender = new Button("Comprar entradas");
        devoluciones = new Button("Devoluciones");
        

        vender.setWidth("180px");
        devoluciones.setWidth("180px");

        gridVentaDevoluciones = new Grid();
        gridVentaDevoluciones.setColumns("id","sesion","nEntradas");
        gridVentaDevoluciones.addSelectionListener(e -> 
    	{
    		DevolucionesDTO d = null;
    		if (!e.getSelected().isEmpty() ) {
        		d = (DevolucionesDTO) e.getSelected().iterator().next();
        		
    		}
    		devolucion.setVenta(d);
    		

    	});

		devolucion= new Devoluciones(this);

        
        gridVentaSesiones = new Grid();
        gridVentaSesiones.setColumns("sesion","sala","asientosOcupados","capacidad");
        gridVentaSesiones.setWidth("600px");
        
        cargaGridVenta();
        gridVentaSesiones.addSelectionListener(e -> 
    	{
    		VentasDTO s = null;
    		if (!e.getSelected().isEmpty() ) {
        		s = (VentasDTO) e.getSelected().iterator().next();
        		
    		}

    		detalle.setSesion(s);

    	});
        
		detalle = new Taquilla(this);

		

       layoutVenta.addComponents(gridVentaSesiones);
 
        vender.addClickListener(e -> {
        	cargaGridVenta();
        	
        	layoutContenidoVenta.addComponents(gridVentaSesiones);
        	layoutVenta.addComponent(detalle);
        	layoutContenidoVenta.removeComponent(gridVentaDevoluciones);
        	layoutDevolver.removeComponent(devolucion);
        	layout4.addComponents(layoutContenidoVenta, layoutVenta);

        });
        devoluciones.addClickListener(e -> {
        	cargaGridDevoluciones();
        	layoutContenidoVenta.addComponents(gridVentaDevoluciones);
        	layoutDevolver.addComponent(devolucion);
        	
        	layoutContenidoVenta.removeComponent(gridVentaSesiones);
        	layoutVenta.removeComponent(detalle);
        	

        	layout4.addComponents(layoutContenidoVenta, layoutDevolver);
        });
		

		layoutBotonesVenta.addComponents(vender,devoluciones);
		layout4.addComponents(layoutBotonesVenta);
		
		sample.addTab(layout4, "Ventas");
		sample.addTab(layout2, "Administración");

	
		layout.addComponents(sample);
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);

	}
	
	public void cargaGridVenta() {
		listaVentas.clear();
		List<Sesion> lista = servicioGestorCine.listaSesiones();
		for(Sesion s : lista){
			VentasDTO v = new VentasDTO();
			v = conv.convierteVentas(s);
			
			listaVentas.add(v);
		}
		gridVentaSesiones.setContainerDataSource(
        		new BeanItemContainer<>(VentasDTO.class, listaVentas)
        );
	}
	
	public void cargaGridDevoluciones() {
		listaDevoluciones.clear();
		List<Venta> lista = servicioGestorTaquilla.listar();
		for(Venta s : lista){
			DevolucionesDTO d = new DevolucionesDTO();
			d = conv.convierteDevoluciones(s);
			
			listaDevoluciones.add(d);
		}
		gridVentaDevoluciones.setContainerDataSource(
        		new BeanItemContainer<>(DevolucionesDTO.class, listaDevoluciones)
        );
	}
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	
	
	
	public static class MyUIServlet extends VaadinServlet {
	}
	
//	public void anadeSesion(Sesion s, int asientos){
//<<<<<<< HEAD
////		servicioGestorTaquilla.vendeEntradas(s.getId(),asientos);
////		servicioGestorCine.modificaSesion(s);
//
//=======
//		s.setAsientosOcupados(asientos);
//		servicioGestorCine.modificaSesion(s.getId(),s);
//		
//		servicioGestorTaquilla.vendeEntradas(s.getId(),asientos);
//		
//>>>>>>> f5f46b56cfb4360d18acd43fecd6fa353e1167cd
//	}
//	
	public void borraVenta(Venta v){
		servicioGestorTaquilla.borrar(v);
		cargaGridDevoluciones();
	}
	public void deshabilitarVentas(){
		
		layout4.setEnabled(false);
	}
	

	
public void habilitarVentas(){
		
		layout4.setEnabled(true);
	}
	
	
}
