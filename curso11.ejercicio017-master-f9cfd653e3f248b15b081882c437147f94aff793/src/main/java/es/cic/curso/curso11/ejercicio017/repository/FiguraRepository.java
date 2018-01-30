package es.cic.curso.curso11.ejercicio017.repository;

import java.util.List;

import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;

public interface FiguraRepository {

	List<Figura> list();
	
	void add(Figura figura);

	void delete(Figura figura);
	
}
