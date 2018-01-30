package es.cic.curso06.stillUseful.repository.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.user.User;
import es.cic.curso06.stillUseful.repository.abstracto.AbstractRepositoryImpl;

@Repository
@Transactional
public class UserRepositoryImpl extends AbstractRepositoryImpl<Long, User> implements UserRepository{

	@Override
	public Class<User> getClassDeT() {
		// TODO Auto-generated method stub
		return User.class;
	}

	@Override
	public String getNombreTabla() {
		// TODO Auto-generated method stub
		return "USER";
	}
	

}
