package es.cic.curso.grupo3.ejercicio024.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;

@Repository
public class SalaRepositoryImpl extends AbstractRepositoryImpl<Long, Sala> implements SalaRepository {

	@Override
	public Class<Sala> getClassDeT() {
		return Sala.class;
	}

	@Override
	public String getNombreTabla() {
		return "sala";
	}
}