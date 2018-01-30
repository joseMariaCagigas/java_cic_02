package es.cic.curso.curso07.ejercicio013.servicio;

import java.util.List;

public interface VentaService {

	int PRECIO = 5;

	double calcularPrecio(int numeroEntradas);

	boolean hayButacasLibres(long salaId, long sesionId);
	boolean hayButacasLibres(int cuantas, long salaId, long sesionId);

	double comprarEntradas(long salaId, long sesionId, short cantidad);

	double calcularRecaudacionSala(long salaId);

	double calcularRecaudacion();

}