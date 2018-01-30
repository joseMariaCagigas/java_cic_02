package es.cic.curso;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class PersonaForm extends FormLayout {
	@PropertyId("firstName")
	protected TextField firstName;
	
	@PropertyId("lastName")
	protected TextField lastName;
	
	private Button accion;
	
	private Persona persona;
	
	private MyUI padre;
	
	public PersonaForm(MyUI padre) {
		this.padre = padre;
		
		firstName = new TextField("Nombre: ");
		lastName= new TextField("Apellido: ");
		
		accion = new Button("Actualizar");
		accion.addClickListener(e -> padre.cargaGrid());
		
		addComponents(firstName, lastName, accion);
		

		setPersona(null);
	}

	public void setPersona(Persona persona) {
		this.setVisible(persona != null);
		this.persona = persona;

		if (persona != null) {
			BeanFieldGroup.bindFieldsUnbuffered(persona, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Persona(), this);
		}
	}
}
