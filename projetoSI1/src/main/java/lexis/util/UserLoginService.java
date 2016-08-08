package lexis.util;

import lexis.exceptions.AuthenticationErrorException;
import lexis.models.User;
import lexis.services.UserService;

public class UserLoginService {
	private UserService userService;
	private User userTemp;		
	
	public UserLoginService(UserService userService){
		this.userService = userService;
	}
	
	public void hasErroIn(User user){
		check(user);
		return;
	}
	
	private void check(User user){
		UserRegisterService userRegisterService = new UserRegisterService(userService);
		
		userRegisterService.checkLoginIsEmpty(user);
		
		setLoginIfRegistered(user, userRegisterService);
		
		authenticationPassword(user);
		
		
	}

	private void setLoginIfRegistered(User user, UserRegisterService userRegisterService) {
		//se o login for o email
		if(user.getLogin().contains("@")){	
			userRegisterService.checkEmailIsRegistered(user);
			this.userTemp = userService.getUserByEmail(user.getLogin());			
		//se nao for	
		}else{
			userRegisterService.checkLoginIsRegistered(user);
			this.userTemp = userService.getUserByLogin(user.getLogin());			
		}
	}

	//verifica a validação da senha
	private void authenticationPassword(User user) throws AuthenticationErrorException{
		if(!(userTemp.getPassword().equals(user.getPassword()))){
			throw new  AuthenticationErrorException("senha invalida");
		}
	}
	
	public User getUser(User user){
		check(user);
		return userTemp;
	}
}
