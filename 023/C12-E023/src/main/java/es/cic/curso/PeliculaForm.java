package es.cic.curso;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;

public class PeliculaForm extends FormLayout {
	@PropertyId("titulo")
	protected TextField titulo;
	@PropertyId("director")
	protected TextField director;
	@PropertyId("productora")
	protected TextField productora;
	@PropertyId("interprete")
	protected TextField interprete;
	@PropertyId("year")
	protected TextField year;
	@PropertyId("duracion")
	protected TextField duracion;
	@PropertyId("genero")
	protected TextField genero;
	
	private Button accion;
	private Button borrar;
	private Pelicula pelicula;
	
	private MyUI padre;
	
	public PeliculaForm(MyUI padre) {
		this.padre = padre;
		
		final HorizontalLayout horizontal= new HorizontalLayout();
		horizontal.setSpacing(true);
		
		final VerticalLayout vertical1 = new VerticalLayout();
		final VerticalLayout vertical2 = new VerticalLayout();
		final HorizontalLayout horizontal3 = new HorizontalLayout();

		vertical1.setSpacing(true);
		vertical2.setSpacing(true);
		horizontal3.setSpacing(true);
		

		
		titulo = new TextField("Título: ");
		director = new TextField("Director: ");
		productora = new TextField("Productora: ");
		interprete = new TextField("Interprete: ");
		year = new TextField("Año: ");
		duracion = new TextField("Duración: ");
		genero = new TextField("Género: ");
		
		accion = new Button("Actualizar");
		accion.addClickListener(e -> padre.cargaGrid(pelicula));
		accion.setIcon(FontAwesome.REFRESH);
		borrar = new Button("Borrar");
		borrar.addClickListener(e->padre.borrarGrid(pelicula));
		borrar.setIcon(FontAwesome.CLOSE);
				
		horizontal.addComponents(vertical1,vertical2);
		vertical1.addComponents(titulo,director,productora,interprete);
		vertical2.addComponents(year,duracion,genero,horizontal3);
		horizontal3.addComponents(accion,borrar);
		vertical2.setComponentAlignment(horizontal3, Alignment.BOTTOM_CENTER);

		addComponents(horizontal);
		
		
		setPelicula(null);
	}
	
	public void setPelicula(Pelicula pelicula) {
		this.setVisible(pelicula != null);
		this.pelicula = pelicula;

		if (pelicula != null) {
			BeanFieldGroup.bindFieldsUnbuffered(pelicula, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Pelicula(), this);
		}
	}
	
	
}
