package es.cic.curso06.stillUseful.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso06.stillUseful.dominio.user.User;
import es.cic.curso06.stillUseful.dominio.user.Usuario;
import es.cic.curso06.stillUseful.repository.user.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UserService#crearUser(es.cic.curso06.stillUseful.dominio.user.Usuario, java.lang.String, java.lang.String)
	 */
	@Override
	public User crearUser(Usuario usuarioId, String nick, String password){
		
		User nuevoUser = new User();
		
		nuevoUser.setUsuario(usuarioId);
		nuevoUser.setNick(nick);
		nuevoUser.setPassword(password);
		
		userRepository.add(nuevoUser);
		
		return nuevoUser;
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UserService#editarUser(long, es.cic.curso06.stillUseful.dominio.user.Usuario, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean editarUser(long userId, Usuario usuario, String nick, String password){
		
		User editaUser;
		
		boolean editado = false;
		
		for (User i : userRepository.list()){
			if(i.getId() == userId){
				editaUser = i;
				
				editaUser.setUsuario(usuario);
				editaUser.setNick(nick);
				editaUser.setPassword(password);
				
				userRepository.update(editaUser);
				editado = true;
				
			}
		}return editado;
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UserService#borrarUser(long)
	 */
	@Override
	public boolean borrarUser(long userId){
		
		boolean eliminado = false;
		
		for(User i : userRepository.list()){
			if(i.getId() == userId){
				userRepository.delete(i);
				eliminado = true;
			}
		}
		return eliminado;
		
		
	}
	
	/* (non-Javadoc)
	 * @see es.cic.curso06.stillUseful.service.user.UserService#listarUser()
	 */
	@Override
	public List<User> listarUser(){
		return userRepository.list();
	}
}
