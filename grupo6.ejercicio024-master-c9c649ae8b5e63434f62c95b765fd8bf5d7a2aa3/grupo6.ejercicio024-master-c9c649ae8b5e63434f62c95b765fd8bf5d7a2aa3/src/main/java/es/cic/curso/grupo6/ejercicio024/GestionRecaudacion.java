package es.cic.curso.grupo6.ejercicio024;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorCine;
import es.cic.curso.grupo6.ejercicio024.servicio.ServicioGestorTaquilla;

public class GestionRecaudacion extends HorizontalLayout{

	private ServicioGestorTaquilla servTaquilla;
	private ServicioGestorCine servCine;
	private List<RecaudacionDTO> recs;
	private Grid gridRecs;
	private Button actualizar;
	
	public GestionRecaudacion(){
		servTaquilla = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorTaquilla.class);
		servCine = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorCine.class);

		generaChicha();

		gridRecs = new Grid();
		gridRecs.setColumns("id_sala","recaudacion");
		 actualizar = new Button("Actualizar");
		 actualizar.addClickListener(e -> {
			 generaChicha();
			 cargaGridVenta();
		 });
		addComponents(gridRecs, actualizar);
	}
	
	
	public void cargaGridVenta() {
		gridRecs.setContainerDataSource(
        		new BeanItemContainer<>(RecaudacionDTO.class, recs)
        );
	}
	public void generaChicha(){
		List<Sala> listaSalas = servCine.listaSalas();
		recs = new ArrayList<>();
		for(Sala s : listaSalas){
			RecaudacionDTO r = new RecaudacionDTO();
			r.setId_sala(s.getId());
			float f = servTaquilla.calculaRecaudacion(s.getId());
			r.setRecaudacion(f);
			recs.add(r);
		}
	}
}
