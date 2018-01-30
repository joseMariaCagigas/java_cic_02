package es.cic.curso;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;

public interface PeliculaDetalleVista {

	void setError(String mensaje);

	Pelicula getPelicula();

}