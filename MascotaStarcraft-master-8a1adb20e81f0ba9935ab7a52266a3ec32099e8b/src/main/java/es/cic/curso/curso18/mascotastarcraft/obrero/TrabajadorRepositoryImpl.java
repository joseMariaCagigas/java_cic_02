package es.cic.curso.curso18.mascotastarcraft.obrero;

import es.cic.curso.curso18.mascotastarcraft.dominio.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TrabajadorRepositoryImpl extends AbstractRepositoryImpl<Long, Trabajador> implements TrabajadorRepository {

	@Override
	public Class<Trabajador> getClassDeT() {
		return Trabajador.class;
	}

	@Override
	public String getNombreTabla() {
		return "Trabajador";
	}
}
