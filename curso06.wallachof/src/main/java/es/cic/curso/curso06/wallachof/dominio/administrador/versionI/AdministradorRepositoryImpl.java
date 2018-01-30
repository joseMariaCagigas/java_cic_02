package es.cic.curso.curso06.wallachof.dominio.administrador.versionI;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.wallachof.repositorio.versionI.AbstractRepositoryImpl;

@Repository
@Transactional
public class AdministradorRepositoryImpl extends AbstractRepositoryImpl<Long, Administrador> implements AdministradorRepository {

	@Override
	public Class<Administrador> getClassDeT() {
		return Administrador.class;
	}

	@Override
	public String getNombreTabla() {
		return "administrador";
	}

}
