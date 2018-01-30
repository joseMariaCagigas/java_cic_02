package es.cic.curso.pruebas.cine.repository.sesion;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.pruebas.cine.AbstractRepositoryImpl;

@Repository
@Transactional
public class SesionRepositoryImpl extends AbstractRepositoryImpl<Long, Sesion> implements SesionRepository {

	@Override
	public Class<Sesion> getClassDeT() {
		
		return Sesion.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "sesion";
	}

}
