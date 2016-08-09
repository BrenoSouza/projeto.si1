package lexis.repositories;

import org.springframework.data.repository.CrudRepository;
import lexis.models.User;

/**
 * Interface que irá servir de repositorio para os objetos do tipo User
 * extendendo uma segunda interface da framework que ja vem com os metodos do
 * sitema CRUD implementados e com suporte a busca a parti do nome dos metodos.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	/**
	 * Metodo que busca usuario a partir do email.
	 * 
	 * @param email
	 *            String - o email usado para a busca.
	 * @return Se o email existir retorna o usuario associado a ele, se nao
	 *         retorna null.
	 */
	User findByEmail(String email);

	/**
	 * Metodo que busca o usuario a partir do login.
	 * 
	 * @param login
	 *            String - o login usado para a busca.
	 * @return Se o login existir retorna o usuario associado a ele, se nao
	 *         retorna null.
	 */
	User findByLogin(String login);

}
