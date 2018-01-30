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

	//Casos ID_Long sin test_Helper
	//varios, CREATE
	Long aniadirAdministrador2(String nombre, String apellidos, String dni, String calle, String ciudad,
			String provincia, String comunidad, int codigoPostal, String email);

	//varios, READ
	List<Administrador> obtenerAdministradores2();

	//uno, READ
	Administrador obtenerAdministrador2(Long id);

	//UPDATE
	Administrador actualizarAdministrador2(Administrador modificada);

	//DELETE
	void borrarAdministrador2(Long id);

}