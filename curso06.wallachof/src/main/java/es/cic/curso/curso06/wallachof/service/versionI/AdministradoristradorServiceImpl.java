package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso06.wallachof.dominio.administrador.versionI.Administrador;
import es.cic.curso.curso06.wallachof.dominio.administrador.versionI.AdministradorRepository;

@Service
public class AdministradoristradorServiceImpl implements AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Long aniadirAdministrador(String nick, String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int cp) {
		Administrador administrador = new Administrador();
		
		administrador.setNick(nick);
		administrador.setNombre(nombre);
		administrador.setApellidos(apellidos);
		administrador.setDni(dni);
		administrador.setCalle(calle);
		administrador.setCiudad(ciudad);
		administrador.setProvincia(provincia);
		administrador.setComunidad(comunidad);
		administrador.setCp(cp);
		
		Administrador aniadido = aniadirAdministrador(administrador);
		
		return aniadido.getId();
	}


	private Administrador aniadirAdministrador(Administrador nuevo) {
		administradorRepository.add(nuevo);
		entityManager.flush();
		
		return nuevo;
	}
	
	@Override
	public Administrador obtenerAdministrador(Long id){
		return administradorRepository.read(id);
	}

	@Override
	public List<Administrador> obtenerAdministradores(){
		return administradorRepository.list();
	}

	@Override
	public Administrador actualizarAdministrador(Administrador modificado){
		return administradorRepository.update(modificado);
	}


	@Override
	public void borrarAdministrador(Long id) {
		Administrador aBorrar = obtenerAdministrador(id);
		administradorRepository.delete(aBorrar);
		
	}








}
