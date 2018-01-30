package es.cic.curso.grupo4.ejercicio024.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo4.ejercicio024.dominio.Sesion;

@Repository
@Transactional
public class SesionRepositoryImpl extends AbstractRepositoryImpl<Long, Sesion> implements SesionRepository {

	@Override
	public Class<Sesion> getClassDeT() {
		return Sesion.class;
	}

	@Override
	public String getNombreTabla() {
		return "sesion";
	}

}
