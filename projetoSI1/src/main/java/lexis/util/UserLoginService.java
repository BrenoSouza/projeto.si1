package lexis.util;

import lexis.exceptions.LoginEmptyException;
import lexis.models.User;
import lexis.services.UserService;

public class CheckUserLogin {
	private UserService userService;
	private boolean hasError;
	private String error;
	private User userTemp;		
	
	public CheckUserLogin(UserService userService){
		this.userService = userService;
	}
	
	public boolean hasErroIn(User user){
		check(user);
		return hasError;
	}
	
	public String getError(){
		return error;
	}
	
	private void check(User user){		
		if (user.getLogin().isEmpty()) {
			setError(true, "Login não pode ser vazio");
			return;
		}
		
		if(user.getLogin().contains("@")){			
			this.userTemp = userService.getUserByEmail(user.getLogin());
			if (userTemp == null) {
				setError(true, "email nao cadastrado");
				return;
			}
		}else{			
			userTemp = userService.getUserByLogin(user.getLogin());
			if (userTemp == null) {
				setError(true,"usuario nao cadastrado");
				return;
			}
		}
		if(!(userTemp.getPassword().equals(user.getPassword()))){
			setError(true, "senha invalida");
		}
		
		
	}
	
	private void setError(boolean hasError, String erro){
		this.error = erro;
		this.hasError = hasError;
	}


	public User getUser() {
		return userTemp;
	}
}
