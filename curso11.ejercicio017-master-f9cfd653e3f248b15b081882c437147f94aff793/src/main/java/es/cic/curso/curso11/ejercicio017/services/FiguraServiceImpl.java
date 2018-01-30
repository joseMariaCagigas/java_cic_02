package es.cic.curso.curso11.ejercicio017.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso11.ejercicio017.repository.FiguraRepository;
import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;

@Service
public class FiguraServiceImpl implements FiguraService {

	@Autowired
	private FiguraRepository figuraRepository;
	
	public FiguraServiceImpl(){
		super();
	}
	
	/**
	 * Metodo que obtiene una figura del repositorio
	 * @return FiguraRepository figuraRepository
	 */
	public FiguraRepository getFiguraRepository() {
		return figuraRepository;
	}

	/**
	 * Metodo que introduce una figura en el repositorio
	 * @param FiguraRepository figuraRepository
	 */
	public void setFiguraRepository(FiguraRepository figuraRepository) {
		this.figuraRepository = figuraRepository;
	}

	@Override
	public List<Figura> listar() {
		return figuraRepository.list();
	}

	@Override
	public void anadir(Figura figura) {
		figuraRepository.add(figura);
	}

	@Override
	public void borrar(Figura figura) {
		figuraRepository.delete(figura);
	}

}
