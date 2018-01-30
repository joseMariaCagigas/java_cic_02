package es.cic.curso.curso07.ejercicio013.dominio;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SalaRepositoryImpl extends RepositorioAbstractoImpl<Long, Sala> implements SalaRepository {

	@Override
	public Class<Sala> getClassDeT() {
		return Sala.class;
	}


	@Override
	public String getNombreTabla() {
		return "sala";
	}	
}
