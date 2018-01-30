package es.cic.curso06.stillUseful.repository.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.abstracto.AbstractRepositoryImpl;

@Repository
@Transactional
public class UsuarioRepositoryImpl extends AbstractRepositoryImpl<Long, Usuario> implements UsuarioRepository{

	@Override
	public Class<Usuario> getClassDeT() {
		// TODO Auto-generated method stub
		return Usuario.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "USUARIO";
	}
	

}
