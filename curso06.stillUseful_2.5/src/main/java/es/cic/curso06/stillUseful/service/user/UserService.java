package es.cic.curso06.stillUseful.service.user;

import java.util.List;

import es.cic.curso06.stillUseful.dominio.user.User;
import es.cic.curso06.stillUseful.dominio.user.Usuario;

public interface UserService {

	User crearUser(Usuario usuario, String nick, String password);

	boolean editarUser(long userId, Usuario usuario, String nick, String password);

	boolean borrarUser(long userId);

	List<User> listarUser();

}