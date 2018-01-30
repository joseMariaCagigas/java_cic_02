package es.cic.curso.grupo2.ejercicio024.servicio;

import java.util.List;

import es.cic.curso.grupo2.ejercicio024.servicio.CineServiceImpl.NotEnoughException;

public interface CineService
{
	Long venderEntradas(Long sesionId, int numEntradas) throws NotEnoughException;

	List listarVentas();

	double recaudarSala(Long salaId);

	double recaudarCine();

	double devolver(Long ventaId);

	Long cambio(Long ventaId, Long idSesionNueva) throws NotEnoughException;

	List incicializaCine() throws NotEnoughException;

	double calcularImporte(int numEntradas);
}