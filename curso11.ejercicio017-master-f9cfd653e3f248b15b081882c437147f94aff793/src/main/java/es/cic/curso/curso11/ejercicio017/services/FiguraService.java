package es.cic.curso.curso11.ejercicio017.services;

import java.util.List;

import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;

public interface FiguraService {

	List<Figura> listar();

	void anadir(Figura figura);

	void borrar(Figura figura);
	
}
