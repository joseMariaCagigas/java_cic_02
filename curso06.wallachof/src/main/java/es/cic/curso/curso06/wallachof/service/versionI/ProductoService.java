package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import es.cic.curso.curso06.wallachof.dominio.producto.versionI.Producto;

public interface ProductoService {

	Long aniadirProducto(String nombre, double precio, int cantidad, boolean estado);
	
	Long aniadirProductoConCategoria(String nombre, double precio, int cantidad, boolean estado, long categoriaId);
	
	Producto obtenerProducto(Long id);

	Producto actualizarProducto(Producto modificado);

	void borrarProducto(Long id);

	List<Producto> obtenerProductos();
}