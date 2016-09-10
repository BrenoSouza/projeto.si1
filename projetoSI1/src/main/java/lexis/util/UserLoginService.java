package lexis.util;

import lexis.exceptions.AuthenticationErrorException;
import lexis.models.User;
import lexis.services.UserServiceDAO;

/**
 * Classe responsavel pelo serviço de login da aplicacao.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
public class UserLoginService {
	private UserServiceDAO userService;
	private User userTemp;

	/**
	 * Metodo que seta o servico de usuario.
	 * 
	 * @param userService
	 *            UserService - objeto resposavel pelo servico do usuario.
	 */
	public UserLoginService(UserServiceDAO userService) {
		this.userService = userService;
	}

	/**
	 * Metodo que faz um verificação de erros em atributos do User metodo tambem
	 * faz a autenticação do login.
	 * 
	 * @param user
	 *            User - usuario que será feito a verificação de erros.
	 * @throws lançara
	 *             Um erro caso haja algum erro no usuario.
	 */
	public void hasErroIn(User user) {
		check(user);
	}

	private void check(User user) {
		UserRegisterService userRegisterService = new UserRegisterService(userService);

		userRegisterService.checkLoginIsEmpty(user);

		setLoginIfRegistered(user, userRegisterService);

		authenticationPassword(user);

	}

	private void setLoginIfRegistered(User user, UserRegisterService userRegisterService) {
		// se o login for o email
		if (user.getUsername().contains("@")) {
			userRegisterService.checkEmailIsRegistered(user);
			this.userTemp = userService.getUserByEmail(user.getUsername());
			// se nao for
		} else {
			userRegisterService.checkLoginIsRegistered(user);
			this.userTemp = userService.getUserByLogin(user.getUsername());
		}
	}

	/**
	 * Metodo que faz a autenticação do login caso a senha seja errada lança uma
	 * exceção.
	 * 
	 * @param user
	 * @throws AuthenticationErrorException
	 *             - Senha errada
	 */
	private void authenticationPassword(User user) throws AuthenticationErrorException {
		if (!(userTemp.getPassword().equals(user.getPassword()))) {
			throw new AuthenticationErrorException("senha invalida");
		}
	}

	/**
	 * Metodo que retorna um usuario devidamente tratado e com login validado.
	 * 
	 * @param user
	 * @return O usuario logado e apto as operacoes.
	 */
	public User getUser(User user) {
		check(user);
		return userTemp;
	}
}
