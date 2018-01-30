package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.dominio.producto.Producto;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;

@Service
@Transactional
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService#crearEstado(es.cic.curso06.stillUseful.dominio.producto.Producto, java.lang.String, java.lang.String)
	 */
	@Override
	public Estado crearEstado(Producto productoId, String nombre, String descripcion){
		
		Estado nuevoEstado = new Estado();
		
		nuevoEstado.setNombre(nombre);
		nuevoEstado.setDescripcion(descripcion);
		
		estadoRepository.add(nuevoEstado);
		
		return nuevoEstado;
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService#editarEstado(long, es.cic.curso06.stillUseful.dominio.producto.Producto, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean editarEstado(long estadoId, Producto producto, String nombre, String descripcion){
		
		Estado editaEstado;
		
		boolean editado = false;
		
		for (Estado i : estadoRepository.list()){
			if(i.getId() == estadoId){
				editaEstado = i;
				
				editaEstado.setNombre(nombre);
				editaEstado.setDescripcion(descripcion);
				
				estadoRepository.update(editaEstado);
				editado = true;
				
			}
		}return editado;
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService#borrarEstado(long)
	 */
	@Override
	public boolean borrarEstado(long estadoId){
		
		boolean eliminado = false;
		
		for(Estado i : estadoRepository.list()){
			if(i.getId() == estadoId){
				estadoRepository.delete(i);
				eliminado = true;
			}
		}
		return eliminado;
		
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService#listarEstado()
	 */
	@Override
	public List<Estado> listarEstado(){
		return estadoRepository.list();
	}
}
