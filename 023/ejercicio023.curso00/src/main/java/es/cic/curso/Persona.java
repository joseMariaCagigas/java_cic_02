package es.cic.curso;

import com.vaadin.ui.TextField;

public class Persona {
	private String firstName;
	private String lastName;
	
	public Persona() {
		super();
	}

	public Persona(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
