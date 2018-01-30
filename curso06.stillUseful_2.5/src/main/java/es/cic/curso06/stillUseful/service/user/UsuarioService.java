package es.cic.curso06.stillUseful.service.user;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.user.Usuario;

public interface UsuarioService {

	Usuario crearUsuario(String nombre, String apellidos, String dni, String calle, String ciudad,
			String provincia, String comunidad, int codigoPostal, String email);

	boolean editarUsuario(long usuarioId, String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int codigoPostal, String email);

	boolean borrarUsuario(long usuarioId);

	List<Usuario> listarUsuario();

}