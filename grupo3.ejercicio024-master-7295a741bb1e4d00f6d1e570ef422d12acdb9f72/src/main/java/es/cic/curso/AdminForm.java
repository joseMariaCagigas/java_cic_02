

package es.cic.curso;

import java.util.Collection;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;
import es.cic.curso.grupo3.ejercicio024.service.SalaService;
import es.cic.curso.grupo3.ejercicio024.service.VentaService;

public class AdminForm extends FormLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3749707334689233859L;
	private Grid maestro;
	private MyUI padre;
	private ComboBox lista;
	private Button recaudacion;
	private Button iniciar;
	private ComboBox cerrar;
	private ComboBox abrir;
	private Collection<Venta> listaVentas;
	private Collection<Sala> listaSalas;
	private VentaService ventaService;
	private SalaService salaService;
	private Button borrar;
	private Venta venta;
	private static final String SELECCIONA = "Selecciona";

	public AdminForm(MyUI padre){
		this.padre = padre;

		ventaService = ContextLoader.getCurrentWebApplicationContext().getBean(VentaService.class);
		salaService = ContextLoader.getCurrentWebApplicationContext().getBean(SalaService.class);

		listaSalas = salaService.getSalas();

		for (Sala s: listaSalas){
			ventaService.venderEntrada(s, 5);
		}


		final HorizontalLayout base = new HorizontalLayout();
		final VerticalLayout izquierda = new VerticalLayout();
		final VerticalLayout derecha = new VerticalLayout();
		final HorizontalLayout arriba = new HorizontalLayout();
		arriba.setSpacing(true);
		maestro = new Grid();

		maestro.setColumns("id", "precio", "cantidad", "beneficio", "numSala", "numSesion");
		maestro.setSizeFull();
		maestro.setFrozenColumnCount(1);
		maestro.setSelectionMode(SelectionMode.SINGLE);

		maestro.addSelectionListener(new SelectionListener() {

			@Override
			public void select(SelectionEvent event) {
				Venta ventaABorrar =  (Venta) maestro.getSelectedRow();
				venta = ventaABorrar;
				borrar.setVisible(true);
			}

		});

		borrar = new Button("Devolver");
		borrar.setVisible(false);
		borrar.addClickListener(e ->{
			devolverVenta(venta.getId());
			borrar.setVisible(false);
		});

		final HorizontalLayout abajo = new HorizontalLayout();
		abajo.setSpacing(true);

		lista = new ComboBox("Recaudación Sala");
		lista.setInputPrompt(SELECCIONA);
		for (int i = 1; i <= 3; i++) {
			lista.addItem(i);
			lista.setItemCaption(i, "Sala " + i);
		}

		lista.setNullSelectionAllowed(false);
		lista.setImmediate(true);




		lista.addValueChangeListener(e -> 
			{
			Notification.show("La recaudación de la sala es: ",
				String.valueOf(ventaService.obtenerRecaudacionSala((int) e.getProperty().getValue())),
				Type.TRAY_NOTIFICATION);
			lista.setInputPrompt(SELECCIONA);
			});

		recaudacion = new Button("Recaudacion Total");
		recaudacion.setIcon(FontAwesome.DOLLAR);


		recaudacion.addClickListener(e -> Notification.show("La recaudación total de las salas es: ",
				String.valueOf(ventaService.obtenerRecaudacionCine()),
				Type.TRAY_NOTIFICATION)
				);

		cerrar = new ComboBox("Cerrar Sesion");
		cerrar.setInputPrompt(SELECCIONA);
		int auxCerrar = 0;
		for (int i = 1; i <=3; i++) {
			for (int j = 1; j <=3; j++) {
				cerrar.addItem(auxCerrar);
				cerrar.setItemCaption(auxCerrar, "Sala: " + i + " - Sesión: " + j);
				auxCerrar++;
			}
		}

		cerrar.setNullSelectionAllowed(false);
		cerrar.setImmediate(true);

		cerrar.addItem(auxCerrar);
		cerrar.setItemCaption(auxCerrar, "Cine");

		cerrar.addValueChangeListener(e ->
		{
			if ((int)e.getProperty().getValue() == 9){
				salaService.cerrarCine();
				Notification.show("Se ha cerrado el cine",Type.TRAY_NOTIFICATION);
			} else {
				int auxCerrar2 = (int)e.getProperty().getValue();
				switch (auxCerrar2){
				case 0:{
					cerrarYNotificar(1, 1);
					break;
				}
				case 1:{
					cerrarYNotificar(1, 2);
					break;
				}
				case 2:{
					cerrarYNotificar(1, 3);
					break;
				}
				case 3:{
					cerrarYNotificar(2, 1);
					break;
				}
				case 4:{
					cerrarYNotificar(2, 2);
					break;
				}
				case 5:{
					cerrarYNotificar(2, 3);
					break;
				}
				case 6:{
					cerrarYNotificar(3, 1);
					break;
				}
				case 7:{
					cerrarYNotificar(3, 2);
					break;
				}
				case 8:{
					cerrarYNotificar(3, 3);
					break;
				}
				default: {
					break;
				}
				}
			}
			cerrar.setInputPrompt(SELECCIONA);
		}); 		

		abrir = new ComboBox("Abrir Sesion");
		abrir.setInputPrompt(SELECCIONA);
		int auxAbrir = 0;
		for (int i = 1; i <=3; i++) {
			for (int j = 1; j <=3; j++) {
				abrir.addItem(auxAbrir);
				abrir.setItemCaption(auxAbrir, "Sala: " + i + " - Sesión: " + j);
				auxAbrir++;
			}
		}

			abrir.setNullSelectionAllowed(false);
			abrir.setImmediate(true);

			abrir.addItem(auxAbrir);
			abrir.setItemCaption(auxAbrir, "Cine");

			abrir.addValueChangeListener(e ->
			{
				if ((int)e.getProperty().getValue() == 9){
					salaService.abrirCine();
					Notification.show("Se ha abierto el cine",Type.TRAY_NOTIFICATION);
				} else {
					int auxAbrir2 = (int)e.getProperty().getValue();
					switch (auxAbrir2){
					case 0:{
						abrirYNotificar(1, 1);
						break;
					}
					case 1:{
						abrirYNotificar(1, 2);
						break;
					}
					case 2:{
						abrirYNotificar(1, 3);
						break;
					}
					case 3:{
						abrirYNotificar(2, 1);
						break;
					}
					case 4:{
						abrirYNotificar(2, 2);
						break;
					}
					case 5:{
						abrirYNotificar(2, 3);
						break;
					}
					case 6:{
						abrirYNotificar(3, 1);
						break;
					}
					case 7:{
						abrirYNotificar(3, 2);
						break;
					}
					case 8:{
						abrirYNotificar(3, 3);
						break;
					}
					default: {
						break;
					}
					}
				}
				abrir.setInputPrompt(SELECCIONA);
			});


			iniciar = new Button("Iniciar el día");
			iniciar.setIcon(FontAwesome.ERASER);
			iniciar.setVisible(true);

			iniciar.addClickListener(e ->
				reiniciarElDia());

			derecha.setSpacing(true);
			derecha.setMargin(true);
			izquierda.setSpacing(true);
			izquierda.setMargin(true);

			arriba.addComponent(maestro);
			abajo.addComponents(iniciar, recaudacion, lista);
			izquierda.addComponents(arriba, abajo);
			izquierda.addComponents(arriba, abajo);
			derecha.addComponents(cerrar, abrir, borrar);
			base.addComponents(izquierda, derecha);
			addComponents(base);

			cargaGrid();
	}

	public void cargaGrid() {
		listaVentas = ventaService.getVentas();

		maestro.setContainerDataSource(
				new BeanItemContainer<>(Venta.class, listaVentas)
				);
	}

	public void devolverVenta(Long id){
		ventaService.borrarVenta(id);
		cargaGrid();
	}

	public void reiniciarElDia(){
		ventaService.vaciarVentas();
		salaService.vaciarSalas();

		for (int i=1; i <=3 ; i++){
			for (int j=1; j <=3 ; j++){
				Sala sala = new Sala(i, j, false);
				salaService.nuevaSala(sala);
			}
		}
		cargaGrid();
	}
	
	public void abrirYNotificar(int a, int b){
		salaService.abrirSesion(a, b);
		Notification.show("Se ha abierto la sala: "+a+", sesión: "+b
				,Type.TRAY_NOTIFICATION);
	}
	
	public void cerrarYNotificar(int a, int b){
		salaService.cerrarSesion(a, b);
		Notification.show("Se ha cerrado la sala: "+a+", sesión: "+b
				,Type.TRAY_NOTIFICATION);
	}

	public static void main(String[] args) {
		
	}
}

