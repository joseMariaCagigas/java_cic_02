package es.cic.curso.curso10.curso10.ejercicio023;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import es.cic.curso.curso10.curso10.ejercicio023.dominio.Pelicula;

public class PeliculaForm extends FormLayout
{
	private static final long serialVersionUID = 7854103764337669112L;

	@PropertyId("titulo")
	protected TextField titulo;
	
	@PropertyId("director")
	protected TextField director;
	
	@PropertyId("duracion")
	protected TextField duracion;
	
	private Button actualiza;
	private Button borrar;
	
	private Pelicula pelicula;
	
	@SuppressWarnings("unused")
	private MyUI padre;
	
	public PeliculaForm(MyUI padre)
	{
		this.padre = padre;
		
		titulo = new TextField("Titulo: ");
		director = new TextField("Director: ");
		duracion = new TextField("Duracion (min): ");
		
		actualiza = new Button("Actualizar");
		actualiza.addClickListener(e -> 
		{
			padre.cargaGrid();
		});
		
		borrar = new Button("Borrar");
		borrar.addClickListener(e -> {
			padre.borraPelicula(pelicula);
			padre.cargaGrid();
		});
		
		addComponents(titulo, director, duracion, actualiza, borrar);
		
		setPelicula(null);
	}

	public void setPelicula(Pelicula pelicula)
	{
		this.setVisible(pelicula != null);
		this.pelicula = pelicula;

		if (pelicula != null) {
			BeanFieldGroup.bindFieldsUnbuffered(pelicula, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Pelicula(), this);
		}
	}
}
