package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Producto;

public interface CategoriaService {

	Categoria crearCategoria(Producto producto, String nombre, String descripcion);

	boolean editarCategoria(long categoriaId, Producto producto, String nombre, String descripcion);

	boolean borrarCategoria(long categoriaId);

	List<Categoria> listarCategoria();

}