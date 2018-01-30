package es.cic.curso.curso06.wallachof.dominio.usuario.versionI;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.wallachof.repositorio.versionI.AbstractRepositoryImpl;

@Repository
@Transactional
public class UsuarioRepositoryImpl extends AbstractRepositoryImpl<Long, Usuario> implements UsuarioRepository {

	@Override
	public Class<Usuario> getClassDeT() {
		return Usuario.class;
	}

	@Override
	public String getNombreTabla() {
		return "usuario";
	}

}
