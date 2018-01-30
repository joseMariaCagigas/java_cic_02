package es.cic.curso06.stillUseful.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.user.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService#crearUsuario(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public Usuario crearUsuario(String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int codigoPostal, String email){
		
		Usuario nuevoUsuario = new Usuario();
		
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setApellidos(apellidos);
		nuevoUsuario.setDni(dni);
		nuevoUsuario.setCalle(calle);
		nuevoUsuario.setCiudad(ciudad);
		nuevoUsuario.setProvincia(provincia);
		nuevoUsuario.setComunidad(comunidad);
		nuevoUsuario.setCodigoPostal(codigoPostal);
		nuevoUsuario.setEmail(email);
		
		usuarioRepository.add(nuevoUsuario);
		
		return nuevoUsuario;
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService#editarUsuario(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public boolean editarUsuario(long usuarioId, String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int codigoPostal, String email){
		
		Usuario editaUsuario;
		
		boolean editado = false;
		
		for (Usuario i : usuarioRepository.list()){
			if(i.getId() == usuarioId){
				editaUsuario = i;
				
				editaUsuario.setNombre(nombre);
				editaUsuario.setApellidos(apellidos);
				editaUsuario.setDni(dni);
				editaUsuario.setCalle(calle);
				editaUsuario.setCiudad(ciudad);
				editaUsuario.setProvincia(provincia);
				editaUsuario.setComunidad(comunidad);
				editaUsuario.setCodigoPostal(codigoPostal);
				editaUsuario.setEmail(email);
				
				usuarioRepository.update(editaUsuario);
				editado = true;
				
			}
		}return editado;
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService#borrarUsuario(long)
	 */
	@Override
	public boolean borrarUsuario(long usuarioId){
		
		boolean eliminado = false;
		
		for(Usuario i : usuarioRepository.list()){
			if(i.getId() == usuarioId){
				usuarioRepository.delete(i);
				eliminado = true;
			}
		}
		return eliminado;
		
		
	}

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UsuarioService#listarUsuario()
	 */
	@Override
	public List<Usuario> listarUsuario(){
		return usuarioRepository.list();
	}
	
	
}
