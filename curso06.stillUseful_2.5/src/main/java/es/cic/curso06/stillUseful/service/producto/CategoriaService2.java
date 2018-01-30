package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;

public interface CategoriaService2 {

	Long aniadirCategoria2(String nombre, String descripcion);

	List<Categoria> obtenerCategorias();
	//uno, READ

	Categoria obtenerCategoria(Long id);
	//UPDATE

	Categoria actualizarCategoria(Categoria modificada);
	//DELETE

	void borrarcategoriaRepository(Long id);

}