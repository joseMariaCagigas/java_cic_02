package es.cic.curso;

import java.util.List;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;

public class PeliculaEvent {

	private Pelicula pelicula;
	private TipoEventoPelicula tipoEvento;
	
	
	public PeliculaEvent() {
		super();
	}
	
	public PeliculaEvent(Pelicula pelicula, TipoEventoPelicula tipoEvento) {
		super();
		this.pelicula = pelicula;
		this.tipoEvento = tipoEvento;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public TipoEventoPelicula getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(TipoEventoPelicula tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
}
