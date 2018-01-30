package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;

@Service
public class CategoriaServiceImpl2 implements CategoriaService2{

	@Autowired
	private CategoriaRepository categoriaRepository;
	//varios, CREATE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService2#aniadirCategoria2(java.lang.String, java.lang.String)
	 */
	@Override
	public Long aniadirCategoria2(String nombre, String descripcion){
		
		Categoria categoriaNueva = new Categoria();
		categoriaNueva.setNombre(nombre);
		categoriaNueva.setDescripcion(descripcion);
		
		Categoria aniadida = aniadirCategoria(categoriaNueva);
		
		return aniadida.getId();
	}
	//uno, CREATE
	private Categoria aniadirCategoria(Categoria nueva) {
		
		return categoriaRepository.add(nueva);
	}
	//varios, READ

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService2#obtenerCategorias()
	 */
	@Override
	public List<Categoria> obtenerCategorias(){
		return categoriaRepository.list();
	}
	//uno, READ

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService2#obtenerCategoria(java.lang.Long)
	 */
	@Override
	public Categoria obtenerCategoria(Long id){
		
		return categoriaRepository.read(id);
				
	}
	//UPDATE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService2#actualizarCategoria(es.cic.curso06.stillUseful.dominio.producto.Categoria)
	 */
	@Override
	public Categoria actualizarCategoria(Categoria modificada){
		return categoriaRepository.update(modificada);
	}
	//DELETE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService2#borrarcategoriaRepository(java.lang.Long)
	 */
	@Override
	public void borrarcategoriaRepository(Long id){
		Categoria aBorrar = obtenerCategoria(id);
		categoriaRepository.delete(aBorrar);
	}
	
}