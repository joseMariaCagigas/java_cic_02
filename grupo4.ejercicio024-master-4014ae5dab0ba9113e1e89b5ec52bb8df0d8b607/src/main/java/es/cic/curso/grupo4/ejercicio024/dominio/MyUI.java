package es.cic.curso.grupo4.ejercicio024.dominio;


import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import es.cic.curso.grupo4.ejercicio024.servicio.GestionCineService;


@Theme("mytheme")
public class MyUI extends UI {


	private static final long serialVersionUID = 9030415108741524405L;
	
	private GestionCineService cineservice;
	private Sesion sesion;
	private Sesion sesionReelegida;
	private Sala salaReelegida;
	private Sala sala;
	private Venta ventaADevolver;
	List<Sesion> listaSesiones;
	
	private Grid gridSesiones;
	private Grid gridVentas;
	private Grid gridSesionesACambiar;
	private TextField textFieldCantidad;
 
	private Label etiquetaRecuadacion;
	private Label etiquetaRecaudacionTotal;
	private Label etiquetaPrecio;
	private Label advertencia;

	private Button recaudacionSala;
	private Button recaudacionTotal;
	private Button vender;
	private Button devolver;
	private Button confirmarCambio;
	private Button cancelarCambio;
	
	private int cont=0;
	@SuppressWarnings("serial")
	@Override
	protected void init(VaadinRequest vaadinRequest) {

		cineservice = ContextLoader.getCurrentWebApplicationContext().getBean(GestionCineService.class);	

		final VerticalLayout layout = new VerticalLayout();
		
		HorizontalLayout espacio = new HorizontalLayout();
		HorizontalLayout espacio2 = new HorizontalLayout();
		HorizontalLayout espacio3 = new HorizontalLayout();
		HorizontalLayout espacio4 = new HorizontalLayout();
		HorizontalLayout espacio5 = new HorizontalLayout();
		HorizontalLayout espacio6 = new HorizontalLayout();
		HorizontalLayout espacio7 = new HorizontalLayout();
		HorizontalLayout espacio8 = new HorizontalLayout();
		HorizontalLayout espacio9 = new HorizontalLayout();
		
		HorizontalLayout devolucionesCambiosLay = new HorizontalLayout();
		devolucionesCambiosLay.setSpacing(true);
		setContent(layout);

		Label label = new Label("GESTIÓN CINE - TAQUILLA");
		Label labelSesiones = new Label("Listado sesiones");
		Label labelVentas = new Label("Listado ventas");

		Panel panel = new Panel();
		HorizontalSplitPanel panelHorizontal = new HorizontalSplitPanel();
		panel.setContent(panelHorizontal);

		HorizontalLayout barraSuperior = new HorizontalLayout();
		barraSuperior.addComponent(label);
		barraSuperior.setStyleName("barrasuperior");
		barraSuperior.setSizeFull();
		barraSuperior.setMargin(true);
		barraSuperior.setHeight(80, Unit.PIXELS);

		HorizontalLayout barraSesiones = new HorizontalLayout();
		barraSesiones.addComponent(labelSesiones);
		barraSesiones.setStyleName("barrasuperior");
		barraSesiones.setSizeFull();
		barraSesiones.setMargin(true);
		barraSesiones.setHeight(20, Unit.PIXELS);

		HorizontalLayout barraVentas = new HorizontalLayout();
		barraVentas.addComponent(labelVentas);
		barraVentas.setStyleName("barrasuperior");
		barraVentas.setSizeFull();
		barraVentas.setMargin(true);
		barraVentas.setHeight(40, Unit.PIXELS);

		VerticalLayout columnaIzquierda = new VerticalLayout();
		columnaIzquierda.setWidth(60, Unit.PERCENTAGE);
		VerticalLayout columnaDerecha = new VerticalLayout();
		columnaDerecha.setWidth(40, Unit.PERCENTAGE);

		VerticalLayout vlCoIz = new VerticalLayout();
		vlCoIz.setMargin(true);
		VerticalLayout vlCoDe = new VerticalLayout();
		vlCoDe.setMargin(true);

		VerticalLayout casillaSesiones = new VerticalLayout();
		casillaSesiones.setSpacing(true);
		VerticalLayout casillaVentas = new VerticalLayout();
		casillaVentas.setSpacing(true);

		HorizontalLayout lhEntradas = new HorizontalLayout();
		HorizontalLayout lhRecaudacion = new HorizontalLayout();
		HorizontalLayout lhRecaudaTotal = new HorizontalLayout();

		//inicializar bbdd
		
		List<Sesion> lista =cineservice.obtenerSesiones();
		
		if(lista.isEmpty()){
			
		cineservice.inicializar();	
		
		}
		
		

		gridSesiones = new Grid();
		gridSesiones.setSizeFull();
		gridSesiones.setColumns("id","nombreSala", "numeSesion", "butacasDisp");
		
		gridSesionesACambiar = new Grid();
		gridSesionesACambiar.setSizeFull();
		gridSesionesACambiar.setColumns("nombreSala", "numeSesion", "butacasDisp");
		
		
		
		
		gridSesiones.setSizeFull();
		gridSesionesACambiar.setSizeFull();

		cargaGridDeSesion();
		gridSesiones.setFrozenColumnCount(1);
		gridSesiones.setSelectionMode(SelectionMode.SINGLE);


		textFieldCantidad = new TextField();
		textFieldCantidad.setInputPrompt("cantiad de entradas");
		textFieldCantidad.setVisible(false);

		//Botones
		vender = new Button("Vender Entradas");
		vender.setIcon(FontAwesome.TICKET);

		devolver = new Button("Devolver Entradas");
		devolver.setIcon(FontAwesome.REPLY);
			
		recaudacionSala = new Button ("Recaudación Sala");
		recaudacionSala.setIcon(FontAwesome.MONEY);

		recaudacionTotal = new Button("Recaudación Total");
		recaudacionTotal.setIcon(FontAwesome.DOLLAR);
		
		confirmarCambio = new Button("Confirmar cambio");
		confirmarCambio.setIcon(FontAwesome.RANDOM);
		
		cancelarCambio = new Button("Cancelar");
		cancelarCambio.setIcon(FontAwesome.TIMES_CIRCLE);
		
		///
		
		advertencia = new Label("ATENCIÓN, seleccionar sesión antes de pulsar vender y recaudacion en Sala.");
		advertencia.setIcon(FontAwesome.WARNING);
		 
		///
		
		gridVentas = new Grid();
		gridVentas.setSizeFull();
		gridVentas.setColumns("id", "cuantasEntradas", "precio", "descuento","esVenta","esDevolucion","devuelta");
		gridVentas.setSizeFull();

		cargaGridDeVentas();
		gridVentas.setFrozenColumnCount(1);
		gridVentas.setSelectionMode(SelectionMode.SINGLE);


		recaudacionSala.addClickListener(e ->{

			double recaudacion = sala.getRecaudacion();
			etiquetaRecuadacion = new Label("Recaudacion en la sala " + sesion.getNombreSala()+ " = " +recaudacion);
			etiquetaRecuadacion.setVisible(true);
			lhRecaudacion.addComponent(etiquetaRecuadacion);


			etiquetaPrecio.setVisible(false);
			etiquetaRecaudacionTotal.setVisible(false);
			textFieldCantidad.setVisible(false);

		});

		recaudacionTotal.addClickListener(e ->{

			double recaudacionTotal = cineservice.beneficiosTotales();
			etiquetaRecaudacionTotal = new Label("Recaudacion total = " + recaudacionTotal);
			etiquetaRecaudacionTotal.setVisible(true);
			lhRecaudaTotal.addComponent(etiquetaRecaudacionTotal);

			etiquetaPrecio.setVisible(false);
			etiquetaRecuadacion.setVisible(false);
			textFieldCantidad.setVisible(false);

		});

		vender.addClickListener(e ->{

			String entradas = textFieldCantidad.getValue();
			int entradasAvender = Integer.parseInt(entradas);

			if(entradasAvender <= sesion.getButacasDisp())
			{
				double precio = cineservice.calcularPrecio(entradasAvender);

				etiquetaPrecio = new Label("Precio = " +precio);
				lhEntradas.addComponent(etiquetaPrecio);

			}

			else{

				etiquetaPrecio = new Label("No hay entradas sufiecientes,NO VENDER !!");
				lhEntradas.addComponent(etiquetaPrecio);

			}

			if (entradasAvender>0){

				cineservice.venderEntradas(entradasAvender, sesion, sala);

				cargaGridDeVentas();

			}
			else{
				if(cont==0){
				Notification sample = new Notification("Introduce valores positivos para vender.");
				sample.setDelayMsec(3000);
				sample.show(Page.getCurrent());
				etiquetaPrecio.setVisible(false);
				cont ++;
				}else{
					
					Notification sample = new Notification("Te he dicho que metas valores positivos..."+(cont+1)+" aviso..espabila");
					sample.setDelayMsec(3000);
					sample.show(Page.getCurrent());
					etiquetaPrecio.setVisible(false);
					cont ++;
					
					
				}
			}
			vender.setVisible(false);
			textFieldCantidad.setVisible(false);
			cargaGridDeSesion();	

		});

		cargaGridDeSesion();	
		
		gridSesiones.addSelectionListener(new SelectionListener() {

			@Override
			public void select(SelectionEvent event) {
				
				vender.setVisible(true);
				if(textFieldCantidad.isEmpty()){
					
					textFieldCantidad.setVisible(true);
					
				}else{
					textFieldCantidad.setVisible(false);
					
				}	
				textFieldCantidad.clear();
				textFieldCantidad.setInputPrompt("cantiad de entradas");
				Sesion sesionSele =  (Sesion) gridSesiones.getSelectedRow();
				sesion = sesionSele;
				sala = sesionSele.getSala();
				
				etiquetaPrecio.setVisible(false);
				etiquetaRecuadacion.setVisible(false);
				etiquetaRecaudacionTotal.setVisible(false);
						
			}

		});
		
		gridSesionesACambiar.addSelectionListener(new SelectionListener() {

			@Override
			public void select(SelectionEvent event) {
				
				Sesion sesionSele =  (Sesion) gridSesionesACambiar.getSelectedRow();
				sesionReelegida = sesionSele;
				salaReelegida = sesionSele.getSala();
				
				if(sesionSele.getId()==ventaADevolver.getSesiones().getId()){
					
					Notification sample = new Notification("Esa es la misma sesion de origen,selecciones otra sesion");
					sample.setDelayMsec(3000);
					sample.show(Page.getCurrent());
					
				}
				else{
				if(sesionReelegida.getButacasDisp()<ventaADevolver.getCuantasEntradas()){
					
					Notification sample = new Notification("Para esa sesion no hay entradas suficientes");
					sample.setDelayMsec(3000);
					sample.show(Page.getCurrent());
							
				}
				}
		 						
			}

		});
		
		
		gridVentas.addSelectionListener(new SelectionListener() {

			@Override
			public void select(SelectionEvent event) {


				Venta ventaSeleccionada =  (Venta) gridVentas.getSelectedRow();
				ventaADevolver = ventaSeleccionada;
				boolean puedo = cineservice.puedoDevolverEntradas(ventaADevolver);
				if(!puedo){

					if(ventaADevolver.isDevuelta()){

						gridSesionesACambiar.setVisible(false);
						confirmarCambio.setVisible(false);
						cancelarCambio.setVisible(false);
						Notification sample2 = new Notification("Ya se han devuelto,no puedes devolverlas ni caambiarlas.");
						sample2.setDelayMsec(3000);
						sample2.show(Page.getCurrent());

					} else {
						gridSesionesACambiar.setVisible(false);
						confirmarCambio.setVisible(false);
						cancelarCambio.setVisible(false);
						Notification sample2 = new Notification("Elija una venta, no un registro de devolucion.");
						sample2.setDelayMsec(3000);
						sample2.show(Page.getCurrent());

					}

				}


				else{
					
					gridSesionesACambiar.setVisible(true);
					confirmarCambio.setVisible(true);
					cancelarCambio.setVisible(true);
					cargarGridDeSesionesACambiar();
					gridSesionesACambiar.setFrozenColumnCount(1);
					gridSesionesACambiar.setSelectionMode(SelectionMode.SINGLE); 
				
					vlCoDe.addComponents(espacio2,gridSesionesACambiar,espacio7,confirmarCambio,espacio8,cancelarCambio);
					Notification sample2 = new Notification("Se pueden devolver o cambiar ");
					sample2.setDelayMsec(3000);
					sample2.show(Page.getCurrent());

				}
				devolver.setVisible(true);
				etiquetaPrecio.setVisible(false);
				etiquetaRecuadacion.setVisible(false);
				etiquetaRecaudacionTotal.setVisible(false);
				textFieldCantidad.setVisible(false);
				
			}
		});

		devolver.addClickListener(e ->{

			cineservice.devolverEntradas(ventaADevolver);
	 
			gridSesionesACambiar.setVisible(false);
			confirmarCambio.setVisible(false);
			cancelarCambio.setVisible(false);
			
			cargaGridDeSesion();
			cargaGridDeVentas();


		});
		
 	
		confirmarCambio.addClickListener(e->{
			
			cineservice.cambioEntradas(ventaADevolver,salaReelegida,sesionReelegida);
 			
			gridSesionesACambiar.setVisible(false);
			confirmarCambio.setVisible(false);
			
			Notification sample2 = new Notification("Entradas cambiadas");
			sample2.setDelayMsec(3000);
			sample2.show(Page.getCurrent());
			
			gridSesionesACambiar.setVisible(false);
			confirmarCambio.setVisible(false);
			cancelarCambio.setVisible(false);
			
			cargaGridDeSesion();
			cargaGridDeVentas();
			
			
		});
		
	 
		cancelarCambio.addClickListener(e->{
			
			gridSesionesACambiar.setVisible(false);
			confirmarCambio.setVisible(false);
			cancelarCambio.setVisible(false);
			
			cargaGridDeSesion();
			cargaGridDeVentas();
			
		});
		
		devolucionesCambiosLay.addComponents(devolver);
		casillaSesiones.addComponents(barraSesiones, gridSesiones);
		casillaVentas.addComponents(barraVentas, gridVentas);
		vlCoIz.addComponents(casillaSesiones,espacio, casillaVentas,espacio9, devolucionesCambiosLay);
		panelHorizontal.setFirstComponent(vlCoIz);

		lhEntradas.addComponent(vender);
		lhEntradas.setSpacing(true);
		lhRecaudacion.addComponent(recaudacionSala);
		lhRecaudacion.setSpacing(true);
		lhRecaudaTotal.addComponent(recaudacionTotal);
		lhRecaudaTotal.setSpacing(true);

		vlCoDe.addComponents(advertencia,espacio3,textFieldCantidad,espacio4, lhEntradas,espacio5,lhRecaudacion,espacio6, lhRecaudaTotal);
		panelHorizontal.setSecondComponent(vlCoDe);

		layout.addComponents(barraSuperior, panel);



	}
	
	private void cargarGridDeSesionesACambiar(){
		
		gridSesionesACambiar.setContainerDataSource(
				new BeanItemContainer<>(Sesion.class, cineservice.obtenerSesiones())
				);
		
	}
	

	private void cargaGridDeSesion() {
		
		gridSesiones.setContainerDataSource(
				new BeanItemContainer<>(Sesion.class, cineservice.obtenerSesiones())
				);

	}

	private void cargaGridDeVentas() {
		 
		gridVentas.setContainerDataSource(
				new BeanItemContainer<>(Venta.class, cineservice.obtenerVentas())
				);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6790551360405458138L;
	}

}