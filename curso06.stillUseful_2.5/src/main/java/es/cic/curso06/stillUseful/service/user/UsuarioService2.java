package es.cic.curso06.stillUseful.service.user;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.user.Usuario;

public interface UsuarioService2 {

	Long aniadirUsuario(String nombre, String apellidos, String dni, String calle, String ciudad, String provincia,
			String comunidad, int codigoPostal, String email);

	Usuario obtenerUsuario(Long id);

	List<Usuario> obtenerUsuarios();

	Usuario actualizarUsuario(Usuario Modificada);

	void borrarUsuario(Long id);

}