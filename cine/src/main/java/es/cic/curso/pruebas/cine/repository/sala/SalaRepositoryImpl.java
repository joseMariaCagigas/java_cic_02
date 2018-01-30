package es.cic.curso.pruebas.cine.repository.sala;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.pruebas.cine.AbstractRepositoryImpl;

@Repository
@Transactional
public class SalaRepositoryImpl  extends AbstractRepositoryImpl<Long, Sala> implements SalaRepository{

	@Override
	public Class<Sala> getClassDeT() {
		// TODO Auto-generated method stub
		return Sala.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "sala";
	}

	}
