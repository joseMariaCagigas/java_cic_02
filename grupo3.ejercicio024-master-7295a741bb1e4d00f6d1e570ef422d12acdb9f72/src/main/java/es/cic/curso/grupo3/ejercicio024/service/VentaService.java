package es.cic.curso.grupo3.ejercicio024.service;

import java.util.Collection;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;

public interface VentaService {

	double obtenerRecaudacionCine();

	double obtenerRecaudacionSala(int numSala);

	void venderEntrada(Sala sala, int cantidad);

	Collection<Venta> getVentas();

	void borrarVenta(Venta venta);

	void borrarVenta(Long id);

	void vaciarVentas();
}