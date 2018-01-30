package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.producto.Estado;

public interface EstadoService2 {

	Long aniadirEstado2(String nombre, String descripcion);

	List<Estado> obtenerEstados();
	//uno, READ

	Estado obtenerEstado(Long id);
	//UPDATE

	Estado actualizarEstado(Estado modificada);
	//DELETE

	void borrarestadoRepository(Long id);

}