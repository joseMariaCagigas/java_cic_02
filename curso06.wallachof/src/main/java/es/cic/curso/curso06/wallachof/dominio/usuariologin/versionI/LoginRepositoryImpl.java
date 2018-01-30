package es.cic.curso.curso06.wallachof.dominio.usuariologin.versionI;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.wallachof.repositorio.versionI.AbstractRepositoryImpl;

@Repository
@Transactional
public class LoginRepositoryImpl extends AbstractRepositoryImpl<Long, Login> implements LoginRepository {

	@Override
	public Class<Login> getClassDeT() {
		return Login.class;
	}

	@Override
	public String getNombreTabla() {
		return "login";
	}

}
