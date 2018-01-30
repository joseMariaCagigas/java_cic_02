package es.cic.curso.curso18.mascotastarcraft.Sitio;

import es.cic.curso.curso18.mascotastarcraft.dominio.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MapaRepositoryImpl extends AbstractRepositoryImpl<Long, Mapa> implements MapaRepository {

	@Override
	public Class<Mapa> getClassDeT() {
		return Mapa.class;
	}

	@Override
	public String getNombreTabla() {
		return "Mapa";
	}
}
