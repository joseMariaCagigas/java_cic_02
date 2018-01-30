package es.cic.curso06.stillUseful.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.repository.admin.AdministradorRepository;

@Service
@Transactional
public class AdministradorServiceImpl implements AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#crearAdministrador(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public Administrador crearAdministrador(String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int codigoPostal, String email){
		
		Administrador nuevoAdministrador = new Administrador();
		
		nuevoAdministrador.setNombre(nombre);
		nuevoAdministrador.setApellidos(apellidos);
		nuevoAdministrador.setDni(dni);
		nuevoAdministrador.setCalle(calle);
		nuevoAdministrador.setCiudad(ciudad);
		nuevoAdministrador.setProvincia(provincia);
		nuevoAdministrador.setComunidad(comunidad);
		nuevoAdministrador.setCodigoPostal(codigoPostal);
		nuevoAdministrador.setEmail(email);
		
		administradorRepository.add(nuevoAdministrador);
		
		return nuevoAdministrador;
		
	}


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#editarAdministrador(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public boolean editarAdministrador(long administradorId, String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int codigoPostal, String email){
		
		Administrador editaAdministrador;
		
		boolean editado = false;
		
		for (Administrador i : administradorRepository.list()){
			if(i.getId() == administradorId){
				editaAdministrador = i;
				
				editaAdministrador.setNombre(nombre);
				editaAdministrador.setApellidos(apellidos);
				editaAdministrador.setDni(dni);
				editaAdministrador.setCalle(calle);
				editaAdministrador.setCiudad(ciudad);
				editaAdministrador.setProvincia(provincia);
				editaAdministrador.setComunidad(comunidad);
				editaAdministrador.setCodigoPostal(codigoPostal);
				editaAdministrador.setEmail(email);
				
				administradorRepository.update(editaAdministrador);
				editado = true;
				
			}
		}return editado;
		
	}
	


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#borrarAdministrador(long)
	 */
	@Override
	public boolean borrarAdministrador(long administradorId){
		
		boolean eliminado = false;
		
		for(Administrador i : administradorRepository.list()){
			if(i.getId() == administradorId){
				administradorRepository.delete(i);
				eliminado = true;
			}
		}
		return eliminado;
		
		
	}


	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#listarAdministrador()
	 */
	@Override
	public List<Administrador> listarAdministrador(){
		return administradorRepository.list();
	}
	
	//Casos ID_Long sin test_Helper
	//varios, CREATE
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#aniadirAdministrador(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public Long aniadirAdministrador2(String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int codigoPostal, String email){
		
		Administrador nuevoAdministrador = new Administrador();
		
		nuevoAdministrador.setNombre(nombre);
		nuevoAdministrador.setApellidos(apellidos);
		nuevoAdministrador.setDni(dni);
		nuevoAdministrador.setCalle(calle);
		nuevoAdministrador.setCiudad(ciudad);
		nuevoAdministrador.setProvincia(provincia);
		nuevoAdministrador.setComunidad(comunidad);
		nuevoAdministrador.setCodigoPostal(codigoPostal);
		nuevoAdministrador.setEmail(email);
		
		administradorRepository.add(nuevoAdministrador);
		
		return nuevoAdministrador.getId();
	}
	//uno, CREATE
	private Administrador aniadirAdministrador2(Administrador nuevo) {
		
		return administradorRepository.add(nuevo);
	}
	//varios, READ
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#obtenerAdministradores()
	 */
	@Override
	public List<Administrador> obtenerAdministradores2(){
		return administradorRepository.list();
	}
	//uno, READ
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#obtenerAdministrador(java.lang.Long)
	 */
	@Override
	public Administrador obtenerAdministrador2(Long id){
		
		return administradorRepository.read(id);
				
	}
	//UPDATE
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#actualizarAdministrador(es.cic.curso06.stillUseful.dominio.admin.Administrador)
	 */
	@Override
	public Administrador actualizarAdministrador2(Administrador modificada){
		return administradorRepository.update(modificada);
	}
	//DELETE
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdministradorService#borrarSala(java.lang.Long)
	 */
	@Override
	public void borrarAdministrador2(Long id){
		Administrador aBorrar = obtenerAdministrador2(id);
		administradorRepository.delete(aBorrar);
	}
}
