package lexis.services;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lexis.models.User;
import lexis.repositories.UserRepository;

/**
 * Implementacao da interface UserService
 * @author raimundoheitor
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * metodo que lista todos os usuarios
	 * @return todos os usuarios cadastrados
	 */
	@Override
	public Iterable<User> listAllUsers() {
		return userRepository.findAll();
	}
	
	/**
	 * busca o usuario pelo ID
	 * @param id
	 * @return o Usuario associado ao Id, ou null
	 */
	@Override
	public User getUserById(Integer id) {
		return userRepository.findOne(id);
	}

	/**
	 * salva o usuario
	 * @param user User - o usuario que será salvo 
	 */
	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	/**
	 * deleta o usuario
	 * @param id Integer - o id que sera passado para a remoção
	 */
	@Override
	public void deleteUser(Integer id) {
		userRepository.delete(id);
	}
	
	/**
	 * bsuca o usuario pelo login
	 * @param login String - o login usado para a busca
	 * @return retorna o usuario ou null
	 */
	@Override
	public User getUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	/**
	 * busca o usuario pelo email
	 * @param email String - o email que sera usado pela busca
	 * @return
	 */
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * verifica se existe o user pelo login
	 * @param login String - O login que se quer verificar 
	 * @return True se estiver ,False se nao
	 */
	@Override
	public Boolean existsByLogin(String login) {
		Iterator<User> interator = userRepository.findAll().iterator();
		while (interator.hasNext()) {
			User user = interator.next();
			if (user.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * verifica se existe o user pelo email
	 * @param email String - O email que se quer verificar 
	 * @return True se estiver ,False se nao
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

}
