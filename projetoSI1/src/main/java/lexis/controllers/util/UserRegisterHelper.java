package lexis.controllers.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lexis.exceptions.*;
import lexis.models.DataBase;
import lexis.models.User;
import lexis.services.UserServiceDAO;

@Component
public class UserRegisterHelper {

	private static UserServiceDAO userService;
	private User userTemp;

	@Autowired
	public void setUserService(UserServiceDAO userService2) {
		userService = userService2;
	}
	
	public UserRegisterHelper(){}

	public void hasErrorIn(User user) {
		check(user);
		return;
	}

	private void check(User user) {

		checkLoginIsEmpty(user);

		checkEmailIsEmpty(user);

		checksLoginIsUsed(user);

		checksEmailIsUsed(user);
		
		checkValidLogin(user);
		
		checkValidEmail(user);
	}

	private void checkValidEmail(User user) throws EmailInvalidException {
		String valid = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if(!user.getEmail().matches(valid)){
			throw new EmailInvalidException("O e-mail informado é inválido");
		}

		
	}

	// verifica se caracteres de login sao validos
	private void checkValidLogin(User user) throws LoginInvalidException{
		String valid = "^[a-zA-Z0-9_.-]*$";
		if(!user.getUsername().matches(valid)){
			throw new LoginInvalidException("O login informado é inválido");
		}

	}
	
	
	// verifica se o email esta em uso
	private void checksEmailIsUsed(User user) throws EmailIsUsedException {
		if (userService.existsByEmail(user.getEmail())) {
			throw new EmailIsUsedException("e-mail já em uso");
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
		if((userRegister == null)||(userService == null)){
			throw new LoginNotRegisteredException("erro ao cadastrar");
		}
		DataBase.getInstance().addNewUser(userRegister);
		userService.saveUser(userRegister);
	

	}

}