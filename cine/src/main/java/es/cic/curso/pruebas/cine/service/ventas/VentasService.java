package es.cic.curso.pruebas.cine.service.ventas;

import java.util.List;

import es.cic.curso.pruebas.cine.repository.venta.Venta;

public interface VentasService {

	Long aniadirVenta(Long idSala, Long idSesion, int numEntradas, double importe);

	Venta obtenerVenta(Long id);

	List<Venta> obtenerVentas();

	Venta actualizarVenta(Venta ventaModificada);

	void borrarVenta(Long id);

}