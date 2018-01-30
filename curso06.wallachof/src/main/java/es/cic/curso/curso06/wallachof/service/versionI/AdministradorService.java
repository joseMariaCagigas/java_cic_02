package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import es.cic.curso.curso06.wallachof.dominio.administrador.versionI.Administrador;

public interface AdministradorService {

	Long aniadirAdministrador(String nick, String nombre, String apellidos, String dni, 
			String calle, String ciudad, String provincia, String comunidad, int cp);

	Administrador obtenerAdministrador(Long id);

	Administrador actualizarAdministrador(Administrador modificado);

	void borrarAdministrador(Long id);

	List<Administrador> obtenerAdministradores();
}