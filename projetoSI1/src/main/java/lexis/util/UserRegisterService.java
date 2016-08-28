package lexis.util;

import lexis.exceptions.*;
import lexis.models.DataBase;
import lexis.models.User;
import lexis.services.UserServiceDAO;

public class UserRegisterService {

	private UserServiceDAO userService;
	private User userTemp;

	public UserRegisterService(UserServiceDAO userService) {
		this.userService = userService;
	}

	public void hasErrorIn(User user) {
		check(user);
		return;
	}

	private void check(User user) {

		checkLoginIsEmpty(user);

		checkEmailIsEmpty(user);

		checksLoginIsUsed(user);

		checksEmailIsUsed(user);
	}

	// verifica se o email esta em uso
	private void checksEmailIsUsed(User user) throws EmailIsUsedException {
		if (userService.existsByEmail(user.getEmail())) {
			throw new EmailIsUsedException("email ja em uso");
		}
	}

	// verifica se o login esta em uso
	private void checksLoginIsUsed(User user) throws LoginIsUsedException {
		if (userService.existsByLogin(user.getUsername())) {
			throw new LoginIsUsedException("login ja em uso");
		}
	}

	// verifica se o email é vazio
	public void checkEmailIsEmpty(User user) throws EmailEmptyException {
		if (user.getEmail().isEmpty()) {
			throw new EmailEmptyException("Email não pode ser vazio");
		}
	}

	// verifica se o login é vazio

	public void checkLoginIsEmpty(User user) throws LoginEmptyException {
		if (user.getUsername().isEmpty()) {
			throw new LoginEmptyException("Login não pode ser vazio");
		}
	}

	// verifica se o email esta cadastrado
	public void checkEmailIsRegistered(User user) throws EmailNotRegisteredException {
		this.userTemp = userService.getUserByEmail(user.getUsername());
		if (userTemp == null) {
			throw new EmailNotRegisteredException("email nao cadastrado");
		}
	}

	// verifica se o login esta cadastrado
	public void checkLoginIsRegistered(User user) throws LoginNotRegisteredException {
		userTemp = userService.getUserByLogin(user.getUsername());
		if (userTemp == null) {
			throw new LoginNotRegisteredException("usuario nao cadastrado");
		}
	}
	// registra o usuario
	public void RegisterUser(User userRegister) {
		DataBase.getInstance().addNewUser(userRegister);
		userService.saveUser(userRegister);

	}

}
