package es.cic.curso06.stillUseful.service.admin;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.admin.Administrador;

public interface AdministradorService {

	Administrador crearAdministrador(String nombre, String apellidos, String dni, String calle, String ciudad,
			String provincia, String comunidad, int codigoPostal, String email);

	boolean editarAdministrador(long administradorId, String nombre, String apellidos, String dni, String calle,
			String ciudad, String provincia, String comunidad, int codigoPostal, String email);

	boolean borrarAdministrador(long administradorId);

	List<Administrador> listarAdministrador();

}