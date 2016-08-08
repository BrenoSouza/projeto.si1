package lexis.services;

import lexis.models.User;

/**
 * Interface resposavel pelo contrato de serviços de usuario
 * @author raimundoheitor
 *
 */
public interface UserService {
	
	/**
	 * metodo que lista todos os usuarios
	 * @return todos os usuarios cadastrados
	 */
	Iterable<User> listAllUsers();
	
	/**
	 * busca o usuario pelo ID
	 * @param id
	 * @return o Usuario associado ao Id, ou null
	 */
	User getUserById(Integer id);
	
	/**
	 * salva o usuario
	 * @param user User - o usuario que será salvo 
	 */
	void saveUser(User user);
	
	/**
	 * bsuca o usuario pelo login
	 * @param login String - o login usado para a busca
	 * @return retorna o usuario ou null
	 */
	User getUserByLogin(String login);
	
	/**
	 * busca o usuario pelo email
	 * @param email String - o email que sera usado pela busca
	 * @return
	 */
	User getUserByEmail(String email);
	
	/**
	 * verifica se existe o user pelo login
	 * @param login String - O login que se quer verificar 
	 * @return True se estiver ,False se nao
	 */
	Boolean existsByLogin(String login);
	
	/**
	 * verifica se existe o user pelo email
	 * @param email String - O email que se quer verificar 
	 * @return True se estiver ,False se nao
	 */
	Boolean existsByEmail(String email);

	/**
	 * deleta o usuario
	 * @param id Integer - o id que sera passado para a remoção
	 */
	void deleteUser(Integer id);

}
