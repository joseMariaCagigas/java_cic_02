package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.dominio.user.Usuario;

public interface ProductoService {

	Producto crearProducto(String nombre, double precioInicial, int cantidad, boolean reservado, boolean vendido,
			Categoria categoriaId, Estado estadoId, Usuario usuarioId);

	boolean editarProducto(long productoid, String nombre, double precioInicial, int cantidad, boolean reservado,
			boolean vendido, Categoria categoriaId, Estado estadoId, Usuario usuarioId);

	boolean borrarProducto(long productoId);

	List<Producto> listarProducto();

}