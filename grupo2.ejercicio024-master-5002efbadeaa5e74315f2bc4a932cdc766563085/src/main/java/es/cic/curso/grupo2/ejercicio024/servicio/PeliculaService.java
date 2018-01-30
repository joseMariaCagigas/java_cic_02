package es.cic.curso.grupo2.ejercicio024.servicio;

import es.cic.curso.grupo2.ejercicio024.dominio.*;
import java.util.List;

public interface PeliculaService
{
	List<Pelicula> getPeliculas();
	Pelicula leePelicula(Long id);
	void nuevaPelicula(Pelicula nueva);
	void editaPelicula(Long id, Pelicula pelicula);
	void borraPelicula(Long id);
}