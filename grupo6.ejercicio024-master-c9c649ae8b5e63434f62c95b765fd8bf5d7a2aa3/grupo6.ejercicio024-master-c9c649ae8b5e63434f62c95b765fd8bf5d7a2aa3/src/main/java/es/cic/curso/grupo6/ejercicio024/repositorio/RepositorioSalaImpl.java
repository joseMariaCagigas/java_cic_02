package es.cic.curso.grupo6.ejercicio024.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo6.ejercicio024.modelo.Sala;


@Repository
@Transactional
public class RepositorioSalaImpl extends RepositorioAbstractoImpl<Long, Sala> implements RepositorioSala {

	@Override
	public Class<Sala> obtenClaseT() {
		return Sala.class;
	}

	@Override
	public String obtenNombreTabla() {
		return Sala.class.getSimpleName().toUpperCase();
	}

	@Override
	public Class<Sala> getClassDeT() {
		// TODO Auto-generated method stub
		return null;
	}

}