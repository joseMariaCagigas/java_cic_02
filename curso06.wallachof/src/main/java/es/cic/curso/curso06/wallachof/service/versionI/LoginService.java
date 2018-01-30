package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import es.cic.curso.curso06.wallachof.dominio.usuariologin.versionI.Login;

public interface LoginService {

	Long aniadirLogin(String user, String password);
	
	Login obtenerLogin(Long id);

	Login actualizarLogin(Login modificado);

	void borrarLogin(Long id);

	List<Login> obtenerLogin();
}