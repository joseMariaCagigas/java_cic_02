package es.cic.curso;

import java.util.Collection;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.service.SalaService;
import es.cic.curso.grupo3.ejercicio024.service.VentaService;

public class ClienteForm extends FormLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2916926270212171644L;
	private Grid maestro;
	private Button comprar;
	private TextField cuantas;
	private Notification alerta;
	private SalaService salaService;
	private VentaService ventaService;
	private Collection<Sala> listaSalas;

	private Sala sala;
	
	private MyUI padre;
	
	public ClienteForm(MyUI padre){
		this.padre = padre;
		
		salaService = ContextLoader.getCurrentWebApplicationContext().getBean(SalaService.class);
		ventaService = ContextLoader.getCurrentWebApplicationContext().getBean(VentaService.class);
		
		final HorizontalLayout arriba = new HorizontalLayout();
		arriba.setSpacing(true);
		maestro = new Grid();
		
		maestro.setColumns("numSala","numSesion" ,"asientosLibres","cerrado");
        maestro.setSizeFull();
        maestro.setFrozenColumnCount(1);
        maestro.setSelectionMode(SelectionMode.SINGLE);
        
        maestro.setSelectionMode(SelectionMode.SINGLE);
        maestro.addSelectionListener(new SelectionListener() {

        @Override
           public void select(SelectionEvent event) {
              Sala salaSeleccionada =  (Sala) maestro.getSelectedRow();
              sala = salaSeleccionada;
              comprar.setVisible(true);
              cuantas.setVisible(true);
           }
        
        });
        
        final HorizontalLayout abajo = new HorizontalLayout();
        abajo.setSpacing(true);
        
        comprar = new Button("Comprar");
        comprar.setIcon(FontAwesome.TICKET);
        
        cuantas = new TextField();
        
        comprar.setVisible(false);
        cuantas.setVisible(false);
        
        comprar.addClickListener(e ->{
        	String cantidad = cuantas.getValue();
        	int cantidadNumerica = Integer.parseInt(cantidad);
        	comprarEntradas(sala, cantidadNumerica);
        	comprar.setVisible(false);
            cuantas.setVisible(false);
            cuantas.setValue("");
        });
        
        alerta = new Notification("Precio de entradas", "5 Euretes");
        alerta.show(Page.getCurrent());
        alerta.setDelayMsec(5000);

        
        arriba.addComponents(maestro);
        abajo.addComponents(comprar, cuantas);
        addComponents(arriba, abajo);
		
        cargaGrid();

	}
	
	public void cargaGrid() {
		listaSalas = salaService.getSalas();
		
		maestro.setContainerDataSource(
        		new BeanItemContainer<>(Sala.class, listaSalas)
        );
	}
	
	public void comprarEntradas(Sala sala, int cantidad){
		
		ventaService.venderEntrada(sala, cantidad);
		cargaGrid();
	}
}