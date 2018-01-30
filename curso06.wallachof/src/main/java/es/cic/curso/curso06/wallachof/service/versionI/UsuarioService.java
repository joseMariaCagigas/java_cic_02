package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import es.cic.curso.curso06.wallachof.dominio.usuario.versionI.Usuario;

public interface UsuarioService {

	Long aniadirUsuario(String nombre, String apellidos, String dni, String coordenadas);
	
	Long aniadirUsuarioConProducto(String nombre, String apellidos, String dni, String coordenadas, long productoId);

	Usuario obtenerUsuario(Long id);

	Usuario actualizarUsuario(Usuario modificado);

	void borrarUsuario(Long id);

	List<Usuario> obtenerUsuarios();
}