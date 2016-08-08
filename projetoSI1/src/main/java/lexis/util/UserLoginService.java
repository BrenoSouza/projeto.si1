package lexis.util;

import lexis.exceptions.AuthenticationErrorException;
import lexis.models.User;
import lexis.services.UserService;

/**
 * classe responsavel pelo serviço de login da aplicação
 * @author raimundoheitor
 *
 */
public class UserLoginService {
	private UserService userService;
	private User userTemp;

	/**
	 * metodo que seta o servico de usuario
	 * @param userService UserService - objeto resposavel pelo servico do usuario
	 */
	public UserLoginService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * metodo que faz um verificação de erros em atributos do User
	 * metodo tambem faz a autenticação do login 
	 * @param user User - usuario que será feito a verificação de erros
	 * @throws lançara um erro caso haja algum erro no usuario
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
		if (user.getLogin().contains("@")) {
			userRegisterService.checkEmailIsRegistered(user);
			this.userTemp = userService.getUserByEmail(user.getLogin());
			// se nao for
		} else {
			userRegisterService.checkLoginIsRegistered(user);
			this.userTemp = userService.getUserByLogin(user.getLogin());
		}
	}

	/**
	 * metodo que faz a autenticação do login
	 * caso a senha seja errada lança uma exceção
	 * @param user
	 * @throws AuthenticationErrorException - senha errada
	 */
	// verifica a validação da senha
	private void authenticationPassword(User user) throws AuthenticationErrorException {
		if (!(userTemp.getPassword().equals(user.getPassword()))) {
			throw new AuthenticationErrorException("senha invalida");
		}
	}

	/**
	 * metodo que retorna um usuario devidamente tratado 
	 * e com login validado
	 * @param user
	 * @return o usuario logado e apto as operacoes
	 */
	public User getUser(User user) {
		check(user);
		return userTemp;
	}
}
