package es.cic.curso.grupo6.ejercicio024.modelo;

public class Cine {
	
	private Long id;
	
	private boolean estado;
	
	public Cine() {
		super();
	}
	
	public Cine( boolean estado) {
		super();
		this.estado = estado;
	}

	public Cine(Long id, boolean estado) {
		super();
		this.id = id;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
