package es.cic.curso06.stillUseful.service.admin;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.admin.Admin;
import es.cic.curso06.stillUseful.dominio.admin.Administrador;

public interface AdminService {

	Admin crearAdmin(Administrador administradorId, String nick, String password);

	boolean editarAdmin(long adminId, Administrador administrador, String nick, String password);

	boolean borrarAdmin(long adminId);

	List<Admin> listarAdmin();

	Long aniadirAdmin2(Long idAdministrador, String nick, String password);

	List<Admin> obtenerAdmin2();
	//uno, READ

	Admin obtenerAdmin2(Long id);
	//UPDATE

	Admin actualizarAdmin2(Admin modificada);
	//DELETE

	void borrarAdmin2(Long id);

}