package es.cic.curso;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;

public class PeliculaDetalleVistaImpl extends FormLayout implements PeliculaDetalleVista {
	private PeliculaDetallePresenter presenter;
	
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
	
	private Label mensajes;
	
	public PeliculaDetalleVistaImpl() {
		
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
		
		accion = new Button("Actualizar/Crear");
		accion.addClickListener(e -> presenter.crearActualizar());
		accion.setIcon(FontAwesome.REFRESH);
		borrar = new Button("Borrar");
		borrar.addClickListener(e->padre.borrarGrid(pelicula));
		borrar.setIcon(FontAwesome.CLOSE);
				
		mensajes = new Label();
		
		horizontal.addComponents(vertical1,vertical2);
		vertical1.addComponents(titulo,director,productora,interprete);
		vertical2.addComponents(year,duracion,genero,horizontal3);
		horizontal3.addComponents(accion,borrar, mensajes);
		vertical2.setComponentAlignment(horizontal3, Alignment.BOTTOM_CENTER);

		addComponents(horizontal);
		
		
		setPelicula(null);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.PeliculaDetalleVista#setError(java.lang.String)
	 */
	@Override
	public void setError(String mensaje) {
		mensajes.setCaption(mensaje);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.PeliculaDetalleVista#getPelicula()
	 */
	@Override
	public Pelicula getPelicula() {
		return this.pelicula;
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

	public PeliculaDetallePresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(PeliculaDetallePresenter presenter) {
		this.presenter = presenter;
	}
	
	
}
