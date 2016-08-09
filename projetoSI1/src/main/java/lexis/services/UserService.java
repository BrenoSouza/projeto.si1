package lexis.services;

import lexis.models.User;

/**
 * Interface resposavel pelo contrato de serviços de usuario.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
public interface UserService {

	/**
	 * Metodo que lista todos os usuarios.
	 * 
	 * @return todos os usuarios cadastrados
	 */
	Iterable<User> listAllUsers();

	/**
	 * Busca o usuario pelo ID.
	 * 
	 * @param id
	 * @return o Usuario associado ao Id, ou null.
	 */
	User getUserById(Integer id);

	/**
	 * Salva o usuario.
	 * 
	 * @param user
	 *            User - o usuario que será salvo.
	 */
	void saveUser(User user);

	/**
	 * Busca o usuario pelo login.
	 * 
	 * @param login
	 *            String - o login usado para a busca.
	 * @return retorna o usuario ou null.
	 */
	User getUserByLogin(String login);

	/**
	 * Busca o usuario pelo email.
	 * 
	 * @param email
	 *            String - o email que sera usado pela busca.
	 * @return
	 */
	User getUserByEmail(String email);

	/**
	 * Verifica se existe o user pelo login.
	 * 
	 * @param login
	 *            String - O login que se quer verificar.
	 * @return True se estiver ,False se nao.
	 */
	Boolean existsByLogin(String login);

	/**
	 * Verifica se existe o user pelo email.
	 * 
	 * @param email
	 *            String - O email que se quer verificar.
	 * @return True se estiver ,False se nao.
	 */
	Boolean existsByEmail(String email);

	/**
	 * Deleta o usuario.
	 * 
	 * @param id
	 *            Integer - o id que sera passado para a remocao.
	 */
	void deleteUser(Integer id);

}
