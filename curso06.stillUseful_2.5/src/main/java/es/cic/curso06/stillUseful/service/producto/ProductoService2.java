package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.producto.Producto;

public interface ProductoService2 {

	Long aniadirProducto(String nombre, double precioInicial, int cantidad, boolean reservado, boolean vendido,
			Long categoriaId, Long estadoId, Long usuarioId);

	Producto obtenerProducto(Long id);

	List<Producto> obtenerProductos();

	Producto actualizarProducto(Producto productoModificada);

	void borrarProducto(Long id);

}