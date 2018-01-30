package es.cic.curso.grupo2.ejercicio024.servicio;

import java.util.List;

import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;

public interface SesionService {

	Sesion leeSesion(Long id);

	void nuevaSesion(Sesion sesion);

	List<Sesion> leerSesiones();

	void editaSesion(Long id, Sesion sesion);

	void borraSesion(Long id);

	void abrirSesion(Sesion sesion);

	void cerrarSesion(Sesion sesion);

}