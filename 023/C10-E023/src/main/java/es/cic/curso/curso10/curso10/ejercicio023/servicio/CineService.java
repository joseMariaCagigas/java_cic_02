package es.cic.curso.curso10.curso10.ejercicio023.servicio;

import es.cic.curso.curso10.curso10.ejercicio023.dominio.*;
import java.util.List;

public interface CineService
{
	String dameMensaje();
	List<Pelicula> getPeliculas();
	void nuevaPelicula(Pelicula nueva);
	void editaPelicula(Long id, Pelicula pelicula);
	void borraPelicula(Long id);
}