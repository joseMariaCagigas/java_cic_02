package es.cic.curso.pruebas.cine.service.sesion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sala.SalaRepository;
import es.cic.curso.pruebas.cine.repository.sesion.Sesion;
import es.cic.curso.pruebas.cine.repository.sesion.SesionRepository;

@Service
public class SesionServiceImpl implements SesionService{
	
	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sesion.SesionService#aniadirSesion(int, boolean, long)
	 */
	@Override
	public Long aniadirSesion(int asientosOcuapdos, boolean esCerrada, long salaid){
	
		Sesion sesion = new Sesion();
		sesion.setAsientosOcupados(asientosOcuapdos);
		sesion.setEstaCerrada(false);
		
		Sala sala  = salaRepository.read(salaid);
		sesion.setSala(sala);
		Sesion sesionNueva = aniadirSesion(sesion);
		return sesionNueva.getId();
		
	}
	
	private Sesion aniadirSesion(Sesion nueva) {
		return sesionRepository.add(nueva);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sesion.SesionService#obtenerSesion(java.lang.Long)
	 */
	@Override
	public Sesion obtenerSesion(Long id){
		return sesionRepository.read(id);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sesion.SesionService#obtenerSesiones()
	 */
	@Override
	public List<Sesion> obtenerSesiones(){
		return sesionRepository.list();
	}
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sesion.SesionService#actualizarSesion(es.cic.curso.pruebas.cine.repository.sesion.Sesion)
	 */
	@Override
	public Sesion actualizarSesion(Sesion modificada){
		return sesionRepository.update(modificada);
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sesion.SesionService#borrarSesion(java.lang.Long)
	 */
	@Override
	public void borrarSesion(Long id){
		Sesion aBorrar = obtenerSesion(id);
		sesionRepository.delete(aBorrar);
	}
}
