package es.cic.curso.grupo6.ejercicio024.servicio;


import java.util.List;

import es.cic.curso.grupo6.ejercicio024.ErrorVenta;
import es.cic.curso.grupo6.ejercicio024.modelo.Cine;
import es.cic.curso.grupo6.ejercicio024.modelo.Venta;

public interface ServicioGestorTaquilla {

	float calculaRecaudacion();

	float calculaRecaudacion(Long idSala);

	void cambiaEstadoSesion(Long idSesion, boolean abierta);

	void cambiaEstadoTaquilla(boolean abierta);

	boolean estaAbierta(Long idSesion);

	int localidadesDisponibles(Long idSesion);

	double vendeEntrada(Long idSesion, Long idLocalidad);

	double vendeEntradas(Long idSesion, int nEntradas) throws ErrorVenta;

	void cambiarEstadoCine(Cine cine, boolean estado);
	
	void borrar(Venta v);

	List<Venta> listar();
	
	Venta buscar(Long id);


}
