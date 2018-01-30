package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso06.wallachof.dominio.usuariologin.versionI.Login;
import es.cic.curso.curso06.wallachof.dominio.usuariologin.versionI.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Long aniadirLogin(String user, String password) {
		Login login = new Login();
		
		login.setUser(user);
		login.setPassword(password);
		
		Login aniadido = aniadirLogin(login);
		
		return aniadido.getId();
	}


	private Login aniadirLogin(Login nuevo) {
		loginRepository.add(nuevo);
		entityManager.flush();
		
		return nuevo;
	}
	
	@Override
	public Login obtenerLogin(Long id){
		return loginRepository.read(id);
	}

	@Override
	public List<Login> obtenerLogin(){
		return loginRepository.list();
	}

	@Override
	public Login actualizarLogin(Login modificado){
		return loginRepository.update(modificado);
	}


	@Override
	public void borrarLogin(Long id) {
		Login aBorrar = obtenerLogin(id);
		loginRepository.delete(aBorrar);
		
	}









}
