package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;

public interface CategoriaService {

	Categoria crearCategoria(String nombre, String descripcion);

	boolean editarCategoria(long categoriaId, String nombre, String descripcion);

	boolean borrarCategoria(long categoriaId);

	List<Categoria> listarCategoria();

	Long aniadirCategoria2(String nombre, String descripcion);

	List<Categoria> obtenerCategorias2();
	//uno, READ

	Categoria obtenerCategorias2(Long id);

	Categoria actualizarCategoria2(Categoria modificada);
	//DELETE

	void borrarCategoria2(Long id);

}