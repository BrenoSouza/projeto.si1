package lexis.util;



import lexis.models.User;
import lexis.services.UserService;

public class CheckUserRegister {
	
	private UserService userService;
	private boolean hasError;
	private String error;
	
	public CheckUserRegister(UserService userService){
		this.userService = userService;
	}
	
	public boolean hasErrorIn(User user){
		check(user);
		return hasError;
	}
	
	public String getError(){
		return error;
	}

	private void check(User user) {
		
		checksEmailIsUsed(user);
		
		checksLoginIsUsed(user);
		
		checkEmailIsEmpty(user);
		
		checkLoginIsEmpty(user);
	}
	
	
	
	//verifica se o email esta em uso
	private void checksEmailIsUsed(User user) {
		if(userService.existsByEmail(user.getEmail())){
			setError(true, "email ja em uso");
			return;
		}
	}
	//verifica se o login esta em uso
	private void checksLoginIsUsed(User user) {
		if(userService.existsByLogin(user.getLogin())){
			setError(true, "login ja em uso");
			return;
		}
	}
	// verifica se o email é vazio
	private void checkEmailIsEmpty(User user) {
		if (user.getEmail().isEmpty()) {
			setError(true, "Email não pode ser vazio");
			return;
		}
	}
	//verifica se o login é vazio
	public void checkLoginIsEmpty(User user) {
		if(user.getLogin().isEmpty()){
			setError(true, "Login não pode ser vazio");
			return;
		}
	}
	
	private void setError(boolean hasError, String erro){
		this.error = erro;
		this.hasError = hasError;
	}
	
	

}
