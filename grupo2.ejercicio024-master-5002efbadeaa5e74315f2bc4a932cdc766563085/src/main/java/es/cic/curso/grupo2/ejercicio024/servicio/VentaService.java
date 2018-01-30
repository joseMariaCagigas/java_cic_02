package es.cic.curso.grupo2.ejercicio024.servicio;

import es.cic.curso.grupo2.ejercicio024.dominio.*;
import java.util.List;

public interface VentaService
{
	List<Venta> getVentas();
	Venta leeVenta(Long id);
	Venta nuevaVenta(Venta nueva);
	void editaVenta(Long id, Venta entrada);
	void borraVenta(Long id);
}