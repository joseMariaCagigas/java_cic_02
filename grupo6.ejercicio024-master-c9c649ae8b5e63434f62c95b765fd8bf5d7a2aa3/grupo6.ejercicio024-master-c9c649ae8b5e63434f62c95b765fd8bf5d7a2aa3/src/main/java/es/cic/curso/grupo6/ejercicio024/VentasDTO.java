package es.cic.curso.grupo6.ejercicio024;

public class VentasDTO {

	private Long id;
	private String sesion;
	private String sala;
	private int asientosOcupados;
	private int capacidad;
	
	public String getSesion() {
		return sesion;
	}
	public void setSesion(String sesion) {
		this.sesion = sesion;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public int getAsientosOcupados() {
		return asientosOcupados;
	}
	public void setAsientosOcupados(int asientosOcupados) {
		this.asientosOcupados = asientosOcupados;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
}
