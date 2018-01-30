package es.cic.curso.grupo2.ejercicio024;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo2.ejercicio024.dominio.Venta;
import es.cic.curso.grupo2.ejercicio024.servicio.CineService;
import es.cic.curso.grupo2.ejercicio024.servicio.CineServiceImpl.NotEnoughException;
import es.cic.curso.grupo2.ejercicio024.servicio.PeliculaService;
import es.cic.curso.grupo2.ejercicio024.servicio.SalaService;
import es.cic.curso.grupo2.ejercicio024.servicio.SesionService;
import es.cic.curso.grupo2.ejercicio024.servicio.VentaService;

@Theme("mytheme")
public class MyUI extends UI
{
private static final long serialVersionUID = -8725441044024774812L;
	
	private Grid maestro;
	/*******************************************************************************************/
	private Grid maestroVentas;
	private Grid maestroRecaudacion;
	private VenderForm venderForm;
	private CambiarForm cambiarForm;
	/*******************************************************************************************/
	
	private CineService cineService;
	private PeliculaService peliculaService;
	private SalaService salaService;
	private SesionService sesionService;
	private VentaService ventaService;
	
	Button devolucionEntradas;
	Button cambioEntradas;
	
	List<Sesion> listaSesiones;
	List<Venta> listaVentas;
	
	Venta nueva;
	Long idVenta;
	
	Sesion sesionCambio;
	List<Long> idsSalas;
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		
		peliculaService = ContextLoader.getCurrentWebApplicationContext().getBean(PeliculaService.class);
		cineService = ContextLoader.getCurrentWebApplicationContext().getBean(CineService.class);
		salaService = ContextLoader.getCurrentWebApplicationContext().getBean(SalaService.class);
		sesionService = ContextLoader.getCurrentWebApplicationContext().getBean(SesionService.class);
		ventaService = ContextLoader.getCurrentWebApplicationContext().getBean(VentaService.class);
		/*******************************************************************************************/		
		try {
			idsSalas = cineService.incicializaCine();
		} catch (NotEnoughException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*******************************************************************************************/		
		listaSesiones = sesionService.leerSesiones();
		listaVentas = ventaService.getVentas(); 
        final VerticalLayout layout = new VerticalLayout();
        
        TabSheet sample = new TabSheet();
        sample.setHeight(100.0f, Unit.PERCENTAGE);
        sample.addStyleName(ValoTheme.TABSHEET_FRAMED);
		sample.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

		layout.setMargin(true);
		
		VerticalLayout layoutVertical = getTabVender();

		sample.addTab(layoutVertical, "Vender entrada");
		/*******************************************************************************************/

		layoutVertical = getTabCambiar();
		
		sample.addTab(layoutVertical, "Ventas");
		layout.setMargin(true);
		/*******************************************************************************************/

		layoutVertical = getTabRecaudacion();

		sample.addTab(layoutVertical, "Recaudacion");
		
		// }

		layout.setMargin(true);
		// }

		layout.addComponents(sample);
		layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
	
	}

	private VerticalLayout getTabRecaudacion() {
		VerticalLayout layoutVertical;
		layoutVertical = new VerticalLayout();
		Double racaduacionSala1 = cineService.recaudarSala(idsSalas.get(0));
		Double racaduacionSala2 = cineService.recaudarSala(idsSalas.get(1));
		Double racaduacionSala3 = cineService.recaudarSala(idsSalas.get(2));

		maestroRecaudacion = new Grid();
		maestroRecaudacion.setColumns("Sala", "RecaudacionTotalSala");
		maestroRecaudacion.addRow(idsSalas.get(0).toString(), racaduacionSala1.toString());
		maestroRecaudacion.addRow(idsSalas.get(1).toString(), racaduacionSala2.toString());
		maestroRecaudacion.addRow(idsSalas.get(2).toString(), racaduacionSala3.toString());

		maestroRecaudacion.setSizeFull();

		double recaudacionTotal = cineService.recaudarCine();
		TextField campoRecaudacionTotal = new TextField();
		campoRecaudacionTotal.setCaption("Recaudación cine");
		campoRecaudacionTotal.setValue(String.valueOf(recaudacionTotal));

		layoutVertical.addComponents(maestroRecaudacion, campoRecaudacionTotal);
		return layoutVertical;
	}

	private VerticalLayout getTabCambiar() {

		
		VerticalLayout layoutVertical;
		layoutVertical = new VerticalLayout();
		cambiarForm = new CambiarForm(this);
		cambiarForm.setVisible(false);
		maestroVentas = new Grid();

		HorizontalLayout capaBotones = new HorizontalLayout();
		capaBotones.setVisible(false);

		devolucionEntradas = new Button();
		cambioEntradas = new Button();
		maestroVentas.setColumns("idSesion", "titulo", "numeroEntradas", "importe");

		cargaGridVenta();

		maestroVentas.setSizeFull();
		maestroVentas.setFrozenColumnCount(1);
		maestroVentas.setSelectionMode(SelectionMode.SINGLE);

		maestroVentas.addSelectionListener(e -> {
			cambiarForm.setVisible(true);
			capaBotones.setVisible(true);
			devolucionEntradas.setEnabled(true);

			Venta s = null;
			if (!e.getSelected().isEmpty()) {
				s = (Venta) e.getSelected().iterator().next();
				cambioEntradas.setEnabled(false);
				idVenta = s.getId();
			}
		});

		devolucionEntradas = new Button("Devolución");
		devolucionEntradas.addClickListener(e -> {
			ventaService.borraVenta(idVenta);
			listaVentas = ventaService.getVentas();
			cargaGridVenta();
			cambiarForm.setVisible(false);
			capaBotones.setVisible(false);
		});

		devolucionEntradas.setIcon(FontAwesome.DELICIOUS);
		cambiarForm.getSelect().addValueChangeListener(e -> {
			Long idSesionCambio = (Long) e.getProperty().getValue();
			;
			sesionCambio = sesionService.leeSesion(idSesionCambio);
			devolucionEntradas.setEnabled(false);
			cambioEntradas.setEnabled(true);
		});

		cambioEntradas = new Button("Cambio");
		cambioEntradas.addClickListener(e -> {
			try {
				cineService.cambio(idVenta, sesionCambio.getId());
				cargaGridVenta();
				capaBotones.setVisible(false);
			} catch (NotEnoughException e1) {
				e1.printStackTrace();
			}
		});

		cambioEntradas.setIcon(FontAwesome.DELICIOUS);


		capaBotones.setMargin(true);
		capaBotones.setSpacing(true);
		capaBotones.addComponents(devolucionEntradas, cambioEntradas, cambiarForm);

		layoutVertical.addComponents(maestroVentas, capaBotones);

		return layoutVertical;
	}

	private VerticalLayout getTabVender() {
		VerticalLayout layoutVertical;

		layoutVertical = new VerticalLayout();
		maestro = new Grid();
		maestro.setColumns("id", "titulo", "hora", "asientosDisponibles", "abierta");

		cargaGridSesion();

		maestro.setFrozenColumnCount(1);
		maestro.setSelectionMode(SelectionMode.SINGLE);

		maestro.addSelectionListener(e -> {
			Sesion s = null;
			if (!e.getSelected().isEmpty()) {
				s = (Sesion) e.getSelected().iterator().next();
				venderForm.setSesion(s);
				maestro.setSizeFull();
			}
		});
		venderForm = new VenderForm(this);

		maestro.setSizeFull();

		nueva = new Venta(); // Aqui me deberia de llegar una venta

		layoutVertical.addComponents(maestro, venderForm);


		return layoutVertical;
	}
	
	public Sesion venderEntradas(Long sesionId, int numEntradas) throws NotEnoughException{
		cineService.venderEntradas(sesionId, numEntradas);
		return sesionService.leeSesion(sesionId);
	}
	
	public double calcularImporte(int numEntradas){
		return cineService.calcularImporte(numEntradas);
	}
	
	public Grid getMaestroVentas() {
		return maestroVentas;
	}


	public void cargaGridSesion() {
		maestro.setContainerDataSource(
        		new BeanItemContainer<>(Sesion.class, sesionService.leerSesiones())
        );
	}
	/*******************************************************************************************/
	public void cargaGridVenta() {
		maestroVentas.setContainerDataSource(
        		new BeanItemContainer<>(Venta.class, ventaService.getVentas())
        );
	}

	/*******************************************************************************************/
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -6179281841152537850L;}
}