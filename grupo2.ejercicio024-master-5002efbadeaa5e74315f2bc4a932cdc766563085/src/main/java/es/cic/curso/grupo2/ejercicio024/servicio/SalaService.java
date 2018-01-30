package es.cic.curso.grupo2.ejercicio024.servicio;

import java.util.List;

import es.cic.curso.grupo2.ejercicio024.dominio.Sala;

public interface SalaService {

	Sala leeSala(Long salaId);

	Long nuevaSala(Sala sala);

	List<Sala> leerSala();

	void editaSala(Long id, Sala sala);

	void borraSala(Long id);

}