package es.cic.curso.grupo3.ejercicio024.service;

import java.util.Collection;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;

public interface SalaService {

	void cerrarSesion(int numSala, int numSesion);

	void abrirSesion(int numSala, int numSesion);

	void cerrarCine();

	void abrirCine();

	boolean esCerradoSesion(Sala sala);

	boolean hayAsientosLibres(Sala sala, int cantidad);

	Collection<Sala> getSalas();

	Sala nuevaSala(Sala sala);

	void vaciarSalas();
}