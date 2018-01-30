package es.cic.curso.grupo6.ejercicio024;

public class DevolucionesDTO {
	
	private Long id;
	private Long sesion;
	private int nEntradas;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public String getSesion() {
//		return sesion;
//	}
//	public void setSesion(String sesion) {
//		this.sesion = sesion;
//	}
	public int getnEntradas() {
		return nEntradas;
	}
	public void setnEntradas(int nEntradas) {
		this.nEntradas = nEntradas;
	}
	public void setSesion(Long sesion) {
		this.sesion = sesion;
	}
	public Long getSesion() {
		return sesion;
	}
	
}
