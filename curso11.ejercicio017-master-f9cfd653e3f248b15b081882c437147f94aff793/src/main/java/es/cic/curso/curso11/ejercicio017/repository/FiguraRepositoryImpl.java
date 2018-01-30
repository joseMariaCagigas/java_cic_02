package es.cic.curso.curso11.ejercicio017.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;

@Repository
public class FiguraRepositoryImpl implements FiguraRepository {

	private List<Figura> listaFiguras;

	public FiguraRepositoryImpl() {
		
		super();
		this.listaFiguras = new ArrayList<>();
	}

	@Override
	public List<Figura> list() {
		
		return new ArrayList<>(listaFiguras);
	}

	@Override
	public void add(Figura figura) {
		
		listaFiguras.add(figura);
	}

	@Override
	public void delete(Figura figura) {
		
		listaFiguras.remove(figura);
	}
}