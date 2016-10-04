package lexis.services.impl;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lexis.models.User;
import lexis.repositories.UserRepository;
import lexis.services.UserServiceDAO;

/**
 * Implementacao da interface UserService.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
@Service
public class UserServiceDAOImpl implements UserServiceDAO,UserDetailsService{
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Metodo que lista todos os usuarios.
	 * 
	 * @return Todos os usuarios cadastrados.
	 */
	@Override
	public Iterable<User> listAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Busca o usuario pelo ID.
	 * 
	 * @param id
	 * @return o Usuario associado ao Id, ou null.
	 */
	@Override
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}

	/**
	 * Salva o usuario.
	 * 
	 * @param user
	 *            User - o usuario que será salvo.
	 */
	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	/**
	 * Deleta o usuario.
	 * 
	 * @param id
	 *            Integer - o id que sera passado para a remocao.
	 */
	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	/**
	 * Busca o usuario pelo login.
	 * 
	 * @param login
	 *            String - o login usado para a busca.
	 * @return retorna o usuario ou null.
	 */
	@Override
	public User getUserByLogin(String login) {
		return userRepository.findByUsername(login);
	}

	/**
	 * Busca o usuario pelo email.
	 * 
	 * @param email
	 *            String - o email que sera usado pela busca.
	 * @return retorna o email ou null.
	 */
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Verifica se existe o user pelo login.
	 * 
	 * @param login
	 *            String - O login que se quer verificar
	 * @return True se estiver ,False se nao.
	 */
	@Override
	public Boolean existsByLogin(String login) {
		Iterator<User> interator = userRepository.findAll().iterator();
		while (interator.hasNext()) {
			User user = interator.next();
			if (user.getUsername().equals(login)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se existe o user pelo email.
	 * 
	 * @param email
	 *            String - O email que se quer verificar.
	 * @return True se estiver ,False se nao.
	 */
	@Override
	public Boolean existsByEmail(String email) {
		Iterator<User> interator = userRepository.findAll().iterator();
		while (interator.hasNext()) {
			User user = interator.next();
			if (user.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		User userTemp;
		String userName = arg0.toLowerCase();
		userTemp = getUserByEmailOrLogin(userName);
		
		if(userTemp == null){
			throw new UsernameNotFoundException("O usuario "+ userName+ " não existe");
		}
		return userTemp;
			
		
	}

	@Override
	public User getUserByEmailOrLogin(String login) {
		if(login.contains("@")){
			return getUserByEmail(login);
		}else{
			return getUserByLogin(login);
		}
	}

	@Override
	public Boolean existsByEmailOrLogin(String login) {
		if(login.contains("@")){
			return existsByEmail(login);
		}else{
			return existsByLogin(login);
		}
	}

}
