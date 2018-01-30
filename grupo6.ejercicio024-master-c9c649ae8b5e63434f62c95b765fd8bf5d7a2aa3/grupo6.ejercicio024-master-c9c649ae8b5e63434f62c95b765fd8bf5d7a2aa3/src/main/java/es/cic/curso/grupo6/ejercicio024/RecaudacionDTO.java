package es.cic.curso.grupo6.ejercicio024;

import com.vaadin.data.fieldgroup.PropertyId;

public class RecaudacionDTO {

	@PropertyId("id_sala")
	private Long id_sala;
	@PropertyId("recaudacion")
	private float recaudacion;
	
	public RecaudacionDTO(){
		
	}
	public RecaudacionDTO(Long id,float rec){
		id_sala = id;
		recaudacion = rec;
	}

	public Long getId_sala() {
		return id_sala;
	}
	public void setId_sala(Long id_sala) {
		this.id_sala = id_sala;
	}
	public float getRecaudacion() {
		return recaudacion;
	}
	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}
	
}
