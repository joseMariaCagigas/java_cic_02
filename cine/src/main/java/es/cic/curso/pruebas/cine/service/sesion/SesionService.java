package es.cic.curso.pruebas.cine.service.sesion;

import java.util.List;

import es.cic.curso.pruebas.cine.repository.sesion.Sesion;

public interface SesionService {

	Long aniadirSesion(int asientosOcuapdos, boolean esCerrada, long salaid);

	Sesion obtenerSesion(Long id);

	List<Sesion> obtenerSesiones();

	Sesion actualizarSesion(Sesion modificada);

	void borrarSesion(Long id);

}