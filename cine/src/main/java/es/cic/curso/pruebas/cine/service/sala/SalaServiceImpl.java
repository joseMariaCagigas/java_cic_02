package es.cic.curso.pruebas.cine.service.sala;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.pruebas.cine.repository.sala.Sala;
import es.cic.curso.pruebas.cine.repository.sala.SalaRepository;

@Service
public class SalaServiceImpl implements SalaService{

	@Autowired
	private SalaRepository salaRepository;
	//varios, CREATE
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sala.SalaService#aniadirSala(int)
	 */
	@Override
	public Long aniadirSala(int aforo){
		Sala sala= new Sala();
		sala.setAforo(aforo);
		Sala aniadida = aniadirSala(sala);
		
		return aniadida.getId();
	}
	//uno, CREATE
	private Sala aniadirSala(Sala nueva) {
		
		return salaRepository.add(nueva);
	}
	//varios, READ
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sala.SalaService#obtenerSalas()
	 */
	@Override
	public List<Sala> obtenerSalas(){
		return salaRepository.list();
	}
	//uno, READ
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sala.SalaService#obtenerSala(java.lang.Long)
	 */
	@Override
	public Sala obtenerSala(Long id){
		
		return salaRepository.read(id);
				
	}
	//UPDATE
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sala.SalaService#actualizarSala(es.cic.curso.pruebas.cine.repository.sala.Sala)
	 */
	@Override
	public Sala actualizarSala(Sala modificada){
		return salaRepository.update(modificada);
	}
	//DELETE
	/* (non-Javadoc)
	 * @see es.cic.curso.pruebas.cine.service.sala.SalaService#borrarSala(java.lang.Long)
	 */
	@Override
	public void borrarSala(Long id){
		Sala aBorrar = obtenerSala(id);
		salaRepository.delete(aBorrar);
	}
	
}
