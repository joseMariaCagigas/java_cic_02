package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#crearCategoria(es.cic.curso06.stillUseful.dominio.producto.Producto, java.lang.String, java.lang.String)
	 */
	@Override
	public Categoria crearCategoria(String nombre, String descripcion){
		
		Categoria nuevoCategoria = new Categoria();
		
		nuevoCategoria.setNombre(nombre);
		nuevoCategoria.setDescripcion(descripcion);
		
		categoriaRepository.add(nuevoCategoria);
		
		return nuevoCategoria;
		
	}
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#editarCategoria(long, es.cic.curso06.stillUseful.dominio.producto.Producto, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean editarCategoria(long categoriaId, String nombre, String descripcion){
		
		Categoria editaCategoria;
		
		boolean editado = false;
		
		for (Categoria i : categoriaRepository.list()){
			if(i.getId() == categoriaId){
				editaCategoria = i;
				
				editaCategoria.setNombre(nombre);
				editaCategoria.setDescripcion(descripcion);
				
				categoriaRepository.update(editaCategoria);
				editado = true;
				
			}
		}return editado;
	}
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#borrarCategoria(long)
	 */
	@Override
	public boolean borrarCategoria(long categoriaId){
		
		boolean eliminado = false;
		
		for(Categoria i : categoriaRepository.list()){
			if(i.getId() == categoriaId){
				categoriaRepository.delete(i);
				eliminado = true;
			}
		}
		return eliminado;
		
		
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#listarCategoria()
	 */
	@Override
	public List<Categoria> listarCategoria(){
		return categoriaRepository.list();
	}
	

	//Casos ID_Long sin test_Helper
	//varios, CREATE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#aniadirCategoria2(java.lang.String, java.lang.String)
	 */
	@Override
	public Long aniadirCategoria2(String nombre, String descripcion){
		
		Categoria nuevaCategoria = new Categoria();
		
		nuevaCategoria.setNombre(nombre);
		nuevaCategoria.setDescripcion(descripcion);

		
		categoriaRepository.add(nuevaCategoria);
		
		return nuevaCategoria.getId();
	}
	//uno, CREATE
	private Categoria aniadirCategoria2(Categoria nuevo) {
		
		return categoriaRepository.add(nuevo);
	}
	//varios, READ


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#obtenerCategorias2()
	 */
	@Override
	public List<Categoria> obtenerCategorias2(){
		return categoriaRepository.list();
	}
	//uno, READ

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#obtenerCategorias2(java.lang.Long)
	 */
	@Override
	public Categoria obtenerCategorias2(Long id){
		
		return categoriaRepository.read(id);
				
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#actualizarCategoria2(es.cic.curso06.stillUseful.dominio.producto.Categoria)
	 */
	@Override
	public Categoria actualizarCategoria2(Categoria modificada){
		return categoriaRepository.update(modificada);
	}
	//DELETE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaInterface#borrarCategoria2(java.lang.Long)
	 */
	@Override
	public void borrarCategoria2(Long id){
		Categoria aBorrar = obtenerCategorias2(id);
		categoriaRepository.delete(aBorrar);
	}
}
