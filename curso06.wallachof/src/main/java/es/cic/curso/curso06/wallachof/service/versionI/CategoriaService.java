package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import es.cic.curso.curso06.wallachof.dominio.categoria.versionI.Categoria;

public interface CategoriaService {

	Long aniadirCategoria(String nombre);
	
	Categoria obtenerCategoria(Long id);

	Categoria actualizarCategoria(Categoria modificado);

	void borrarCategoria(Long id);

	List<Categoria> obtenerCategoria();
}