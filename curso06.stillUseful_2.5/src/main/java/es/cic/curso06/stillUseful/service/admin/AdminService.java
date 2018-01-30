package es.cic.curso06.stillUseful.service.admin;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.admin.Admin;
import es.cic.curso06.stillUseful.dominio.admin.Administrador;

public interface AdminService {

	Admin crearAdmin(Administrador administrador, String nick, String password);

	boolean editarAdmin(long adminId, Administrador administrador, String nick, String password);

	boolean borrarAdmin(long adminId);

	List<Admin> listarAdmin();

}