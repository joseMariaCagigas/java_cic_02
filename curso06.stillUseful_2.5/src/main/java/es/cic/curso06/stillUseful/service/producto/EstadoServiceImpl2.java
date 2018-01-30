package es.cic.curso06.stillUseful.service.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.curso06.stillUseful.dominio.producto.Estado;
import es.cic.curso06.stillUseful.repository.producto.EstadoRepository;

@Service
public class EstadoServiceImpl2 implements EstadoService2 {

	@Autowired
	private EstadoRepository estadoRepository;
	//varios, CREATE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService2#aniadirEstado2(java.lang.String, java.lang.String)
	 */
	@Override
	public Long aniadirEstado2(String nombre, String descripcion){
		
		Estado estadoNueva = new Estado();
		estadoNueva.setNombre(nombre);
		estadoNueva.setDescripcion(descripcion);
		
		Estado aniadida = aniadirEstado(estadoNueva);
		
		return aniadida.getId();
	}
	//uno, CREATE
	private Estado aniadirEstado(Estado nueva) {
		
		return estadoRepository.add(nueva);
	}
	//varios, READ


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService2#obtenerEstados()
	 */
	@Override
	public List<Estado> obtenerEstados(){
		return estadoRepository.list();
	}
	//uno, READ

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService2#obtenerEstado(java.lang.Long)
	 */
	@Override
	public Estado obtenerEstado(Long id){
		
		return estadoRepository.read(id);
				
	}
	//UPDATE


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService2#actualizarEstado(es.cic.curso06.stillUseful.dominio.producto.Estado)
	 */
	@Override
	public Estado actualizarEstado(Estado modificada){
		return estadoRepository.update(modificada);
	}
	//DELETE


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.producto.EstadoService2#borrarestadoRepository(java.lang.Long)
	 */
	@Override
	public void borrarestadoRepository(Long id){
		Estado aBorrar = obtenerEstado(id);
		estadoRepository.delete(aBorrar);
	}
	
}