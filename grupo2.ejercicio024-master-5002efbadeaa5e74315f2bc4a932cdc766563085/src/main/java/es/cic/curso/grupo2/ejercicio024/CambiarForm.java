package es.cic.curso.grupo2.ejercicio024;

import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;

import es.cic.curso.grupo2.ejercicio024.dominio.Pelicula;
import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo2.ejercicio024.dominio.Venta;
import es.cic.curso.grupo2.ejercicio024.servicio.CineService;
import es.cic.curso.grupo2.ejercicio024.servicio.SesionService;
import es.cic.curso.grupo2.ejercicio024.servicio.VentaService;

public class CambiarForm extends FormLayout{

	@PropertyId("sesion")
	protected TextField sesion;
	private SesionService sesionService;
	private NativeSelect select;

	public CambiarForm(MyUI padre) {

		sesion = new TextField("Sesion: ");
		
		sesionService = ContextLoader.getCurrentWebApplicationContext().getBean(SesionService.class);
		
		
		select = new NativeSelect("Selecciona una sesion");

		List<Sesion>sesiones = sesionService.leerSesiones();
		
		for(Sesion se : sesiones){
			select.addItem(se.getId());
		}

		select.setNullSelectionAllowed(true);
		select.setValue(0L);
		select.setImmediate(true);
		addComponents(select);
	}


	public void setVenta(Venta venta) {
		this.setVisible(venta != null);
		if (venta != null) {
			BeanFieldGroup.bindFieldsUnbuffered(venta, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Venta(), this);
		}
	}


	public NativeSelect getSelect() {
		return select;
	}

	public void isActivadoSelect(boolean isActivadoSelect){
		select.setEnabled(isActivadoSelect);
	}

}