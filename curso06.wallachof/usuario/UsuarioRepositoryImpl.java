package es.cic.curso.grupo8.ejercicio019.backend.dominio.usuario;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo8.ejercicio019.backend.AbstractRepositoryImpl;

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
