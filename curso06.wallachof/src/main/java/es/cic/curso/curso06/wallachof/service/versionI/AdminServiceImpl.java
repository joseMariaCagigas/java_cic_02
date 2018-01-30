package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.curso06.wallachof.dominio.admin.versionI.Admin;
import es.cic.curso.curso06.wallachof.dominio.admin.versionI.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Long aniadirAdmin(String user, String password) {
		Admin admin = new Admin();
		
		admin.setUser(user);
		admin.setPassword(password);
		
		Admin aniadido = aniadirAdmin(admin);
		
		return aniadido.getId();
	}


	private Admin aniadirAdmin(Admin nuevo) {
		adminRepository.add(nuevo);
		entityManager.flush();
		
		return nuevo;
	}
	
	@Override
	public Admin obtenerAdmin(Long id){
		return adminRepository.read(id);
	}

	@Override
	public List<Admin> obtenerAdmin(){
		return adminRepository.list();
	}

	@Override
	public Admin actualizarAdmin(Admin modificado){
		return adminRepository.update(modificado);
	}


	@Override
	public void borrarAdmin(Long id) {
		Admin aBorrar = obtenerAdmin(id);
		adminRepository.delete(aBorrar);
		
	}






}
