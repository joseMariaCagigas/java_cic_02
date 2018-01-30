package es.cic.curso06.stillUseful.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.user.UsuarioRepository;
@Service
public class UsuarioServiceImpl2 implements UsuarioService2 {


	@Autowired
	private UsuarioRepository usuarioRepository;
	
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService2#aniadirUsuario(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public Long aniadirUsuario(String nombre, String apellidos, String dni, String calle, String ciudad, String provincia,
			String comunidad, int codigoPostal, String email) {
		
		Usuario nuevaUsuario = new Usuario();
		
		nuevaUsuario.setNombre(nombre);
		nuevaUsuario.setApellidos(apellidos);
		nuevaUsuario.setDni(dni);
		nuevaUsuario.setCalle(calle);
		nuevaUsuario.setCiudad(ciudad);
		nuevaUsuario.setProvincia(provincia);
		nuevaUsuario.setComunidad(comunidad);
		nuevaUsuario.setCodigoPostal(codigoPostal);
		nuevaUsuario.setEmail(email);

		Usuario aniadida = aniadirUsuario(nuevaUsuario);
		
		return aniadida.getId();
	}

	private Usuario aniadirUsuario(Usuario nuevaUsuario) {
		return usuarioRepository.add(nuevaUsuario);
	}


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService2#obtenerUsuario(java.lang.Long)
	 */
	@Override
	public Usuario obtenerUsuario(Long id) {
		
		return usuarioRepository.read(id);
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService2#obtenerUsuarios()
	 */
	@Override
	public List<Usuario> obtenerUsuarios(){
		return usuarioRepository.list();
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService2#actualizarUsuario(es.cic.curso06.stillUseful.dominio.user.Usuario)
	 */
	@Override
	public Usuario actualizarUsuario(Usuario Modificada) {
		
		return usuarioRepository.update(Modificada);
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService2#borrarUsuario(java.lang.Long)
	 */
	@Override
	public void borrarUsuario(Long id) {
		
		Usuario usuarioBorrable = obtenerUsuario(id);
		usuarioRepository.delete(usuarioBorrable);
	}
	
	
	
	
}
