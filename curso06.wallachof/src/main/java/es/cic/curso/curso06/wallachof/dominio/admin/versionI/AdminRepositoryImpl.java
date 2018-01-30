package es.cic.curso.curso06.wallachof.dominio.admin.versionI;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.wallachof.repositorio.versionI.AbstractRepositoryImpl;

@Repository
@Transactional
public class AdminRepositoryImpl extends AbstractRepositoryImpl<Long, Admin> implements AdminRepository {

	@Override
	public Class<Admin> getClassDeT() {
		return Admin.class;
	}

	@Override
	public String getNombreTabla() {
		return "admin";
	}

}
