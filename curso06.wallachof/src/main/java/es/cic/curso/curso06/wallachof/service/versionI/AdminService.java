package es.cic.curso.curso06.wallachof.service.versionI;

import java.util.List;

import es.cic.curso.curso06.wallachof.dominio.admin.versionI.Admin;

public interface AdminService {

	Long aniadirAdmin(String user, String password);
	
	Admin obtenerAdmin(Long id);

	Admin actualizarAdmin(Admin modificado);

	void borrarAdmin(Long id);

	List<Admin> obtenerAdmin();
}