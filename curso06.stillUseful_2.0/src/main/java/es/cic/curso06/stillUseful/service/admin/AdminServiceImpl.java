package es.cic.curso06.stillUseful.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.admin.Admin;
import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.repository.admin.AdminRepository;
import es.cic.curso06.stillUseful.repository.admin.AdministradorRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService  {

	@Autowired
	private AdminRepository adminrepository;
	
	@Autowired
	private AdministradorRepository administradorRepository;
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#crearAdmin(es.cic.curso06.stillUseful.dominio.admin.Administrador, java.lang.String, java.lang.String)
	 */
	@Override
	public Admin crearAdmin(Administrador administradorId, String nick, String password){
		
		Admin nuevoAdmin = new Admin();
		
		nuevoAdmin.setAdministrador(administradorId);
		nuevoAdmin.setNick(nick);
		nuevoAdmin.setPassword(password);
		
		adminrepository.add(nuevoAdmin);
		
		return nuevoAdmin;
		
	}
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#editarAdmin(long, es.cic.curso06.stillUseful.dominio.admin.Administrador, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean editarAdmin(long adminId, Administrador administrador, String nick, String password){
		
		Admin editaAdmin;
		
		boolean editado = false;
		
		for (Admin i : adminrepository.list()){
			if(i.getId() == adminId){
				editaAdmin = i;
				
				editaAdmin.setAdministrador(administrador);
				editaAdmin.setNick(nick);
				editaAdmin.setPassword(password);
				
				adminrepository.update(editaAdmin);
				editado = true;
				
			}
		}return editado;
	}
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#borrarAdmin(long)
	 */
	@Override
	public boolean borrarAdmin(long adminId){
		
		boolean eliminado = false;
		
		for(Admin i : adminrepository.list()){
			if(i.getId() == adminId){
				adminrepository.delete(i);
				eliminado = true;
			}
		}
		return eliminado;
		
		
	}
	

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#listarAdmin()
	 */
	@Override
	public List<Admin> listarAdmin(){
		return adminrepository.list();
	}

	//Casos ID_Long sin test_Helper
	//varios, CREATE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#aniadirAdmin2(es.cic.curso06.stillUseful.dominio.admin.Administrador, java.lang.String, java.lang.String)
	 */
	@Override
	public Long aniadirAdmin2(Long administradorId, String nick, String password){
		
		Admin nuevoAdmin = new Admin();
		
		Administrador administrador = administradorRepository.read(administradorId);
		nuevoAdmin.setAdministrador(administrador);
		nuevoAdmin.setNick(nick);
		nuevoAdmin.setPassword(password);
		
		Admin aniadida = aniadirAdmin2(nuevoAdmin);
		
		return aniadida.getId();
		
	}
	//uno, CREATE
	private Admin aniadirAdmin2(Admin nuevo) {
		
		return adminrepository.add(nuevo);
	}
	//varios, READ

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#obtenerAdmin2()
	 */
	@Override
	public List<Admin> obtenerAdmin2(){
		return adminrepository.list();
	}
	//uno, READ

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#obtenerAdmin2(java.lang.Long)
	 */
	@Override
	public Admin obtenerAdmin2(Long id){
		
		return adminrepository.read(id);
				
	}
	//UPDATE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#actualizarAdmin2(es.cic.curso06.stillUseful.dominio.admin.Admin)
	 */
	@Override
	public Admin actualizarAdmin2(Admin modificada){
		return adminrepository.update(modificada);
	}
	//DELETE

	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.admin.AdminService#borrarAdmin2(java.lang.Long)
	 */
	@Override
	public void borrarAdmin2(Long id){
		Admin aBorrar = obtenerAdmin2(id);
		adminrepository.delete(aBorrar);
	}
}
