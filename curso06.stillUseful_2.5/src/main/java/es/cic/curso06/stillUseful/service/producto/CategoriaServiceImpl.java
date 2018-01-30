package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.producto.Categoria;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.repository.producto.CategoriaRepository;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService#crearCategoria(es.cic.curso06.stillUseful.dominio.producto.Producto, java.lang.String, java.lang.String)
	 */
	@Override
	public Categoria crearCategoria(Producto productoId, String nombre, String descripcion){
		
		Categoria nuevoCategoria = new Categoria();
		
		nuevoCategoria.setNombre(nombre);
		nuevoCategoria.setDescripcion(descripcion);
		
		categoriaRepository.add(nuevoCategoria);
		
		return nuevoCategoria;
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService#editarCategoria(long, es.cic.curso06.stillUseful.dominio.producto.Producto, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean editarCategoria(long categoriaId, Producto producto, String nombre, String descripcion){
		
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
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService#borrarCategoria(long)
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
	 * @see es.cic.curso06.stillUseful.service.producto.CategoriaService#listarCategoria()
	 */
	@Override
	public List<Categoria> listarCategoria(){
		return categoriaRepository.list();
	}
}
