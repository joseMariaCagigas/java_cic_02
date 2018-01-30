package es.cic.curso.curso12.ejercicio023.servicio;

import java.util.List;

import es.cic.curso.curso12.ejercicio023.dominio.Pelicula;

public interface PeliculaService {

	Long aniadirPelicula(String titulo, String director, String productora, String interprete, int year, int duracion,
			String genero);

	Pelicula actualizarPelicula(Pelicula peli);

	Pelicula obtenerPelicula(Long id);

	List<Pelicula> obtenerPeliculas();

	void borrarPelicula(Long id);

	Pelicula aniadirPelicula(Pelicula nueva);

}