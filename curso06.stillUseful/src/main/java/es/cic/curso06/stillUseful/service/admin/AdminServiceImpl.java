package es.cic.curso06.stillUseful.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.admin.Admin;
import es.cic.curso06.stillUseful.dominio.admin.Administrador;
import es.cic.curso06.stillUseful.repository.admin.AdminRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminrepository;
	
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
}
