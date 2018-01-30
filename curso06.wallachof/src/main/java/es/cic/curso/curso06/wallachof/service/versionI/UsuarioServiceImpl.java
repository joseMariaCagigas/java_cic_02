package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso06.wallachof.dominio.usuario.versionI.Usuario;
import es.cic.curso.curso06.wallachof.dominio.usuario.versionI.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	private EntityManager entityMnager;
	

	public Long aniadirUsuario(String nombre, String apellidos, String dni, String coordenadas){
		Usuario usuario = new Usuario();
		
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setDni(dni);
		usuario.setCoordenadas(coordenadas);

		Usuario aniadido = aniadirUsuario(usuario);
		
		return aniadido.getId();
	}
	
	@Override
	public Long aniadirUsuarioConProducto(String nombre, String apellidos, String dni, String coordenadas, long productoId) {
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setDni(dni);
		usuario.setCoordenadas(coordenadas);
		usuario.setProductoId(productoId);
		
		Usuario aniadido = aniadirUsuario(usuario);
		
		return aniadido.getId();
	}

	private Usuario aniadirUsuario(Usuario nuevo) {
		usuarioRepository.add(nuevo);
		entityMnager.flush();
		
		return nuevo;
	}
	
	@Override
	public Usuario obtenerUsuario(Long id){
		return usuarioRepository.read(id);
	}

	@Override
	public List<Usuario> obtenerUsuarios(){
		return usuarioRepository.list();
	}

	@Override
	public Usuario actualizarUsuario(Usuario modificado){
		return usuarioRepository.update(modificado);
	}

	@Override
	public void borrarUsuario(Long id){
		Usuario aBorrar = obtenerUsuario(id);
		usuarioRepository.delete(aBorrar);
	}


}
