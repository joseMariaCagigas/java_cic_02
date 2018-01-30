package es.cic.curso.pruebas.cine.service.sala;

import java.util.List;

import es.cic.curso.pruebas.cine.repository.sala.Sala;

public interface SalaService {

	//varios, CREATE
	Long aniadirSala(int aforo);

	//varios, READ
	List<Sala> obtenerSalas();

	//uno, READ
	Sala obtenerSala(Long id);

	//UPDATE
	Sala actualizarSala(Sala modificada);

	//DELETE
	void borrarSala(Long id);

}