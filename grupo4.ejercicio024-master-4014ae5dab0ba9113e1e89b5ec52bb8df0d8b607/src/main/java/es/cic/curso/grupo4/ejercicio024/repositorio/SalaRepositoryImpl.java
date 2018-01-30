package es.cic.curso.grupo4.ejercicio024.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo4.ejercicio024.dominio.Sala;

@Repository
@Transactional
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
