package es.cic.curso.curso10.curso10.ejercicio023.servicio;

import java.util.List;
import es.cic.curso.curso10.curso10.ejercicio023.dominio.Pelicula;

public interface CineController
{
	public String dameMensaje();
	public List<Pelicula> getPeliculas();
	void nuevaPelicula(Pelicula nueva);
	void editaPelicula(Long id, Pelicula pelicula);
	void borraPelicula(Long id);
}