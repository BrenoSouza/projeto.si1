package lexis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lexis.models.User;
import lexis.services.UserService;


@Controller
@RequestMapping("/")
public class IndexController {

	private UserService userService;//objeto responsavel por manipular User

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
	
	@RequestMapping //acessa o url "/"
    public ModelAndView index(){
    	ModelAndView index = new ModelAndView("/index");//cria um novo objeto que aponta para "index.html"
    	index.addObject("userRegister",new User());//adicionar um User com a chave "usercadastro"
    	index.addObject("userLogin",new User());//adiciona um User com a chave "userLogin"
        return index;//retorna o objeto para o index.html
    }
    
	
    /* recebe o paramentro "userCastro" na url
     * com o metodo POST que indica que a pagina irá enviar dados para o controller
     * esses dados sao enviados atravez de um form no index.html
     * nesse caso esses dados é um objeto do tipo User(userCadastro)
     * logo apos receber o controler salva no servico que controla User
     * e em seguida envia a requisição ao metodo Login, para que seja feita a validação
     */
	@RequestMapping(value = "userRegister", method = RequestMethod.POST)
    public ModelAndView userRegister(User userCadastro, RedirectAttributes attributes){//recebendo o objeto user
		User userTemp;
		ModelAndView login = new ModelAndView();
		//verifica se o login ja esta em uso
		if(userService.existsByLogin(userCadastro.getLogin())){
			login.setViewName("redirect:/");
			attributes.addFlashAttribute("mensagem", "login ja em uso");//com a mensagem de erro
			return login;//retorna a pagina incial com a mensagem de erro
		}
		//verificar se o email ja esta em uso
		if(userService.existsByEmail(userCadastro.getEmail())){
			login.setViewName("redirect:/");
			attributes.addFlashAttribute("mensagem", "email ja em uso");//com a mensagem de erro
			return login;//retorna a pagina incial com a mensagem de erro
		}
		
		
    	userService.saveUser(userCadastro); // salvando usuario no BD local(provisorio)
    	return userLogin(userCadastro, attributes);//retornando o Model(com o obejto "user") para a view "Home"
    }
	
    /* recebe o paramentro "userLogin" na url
     * com o metodo POST que indica que a pagina irá enviar dados para o controller
     * esses dados sao enviados atravez de um form no index.html
     * nesse caso esses dados é um objeto do tipo User(userLogin)
     * logo apos receber o User o controler salva o objeto em um ModelAndView
     * em seguida faz uma verificação simples da senha
     * se forem iguais retorna para o home.html com o obejo User
     * se forem diferentes retorna a mesma pagina
     */
    @RequestMapping(value = "userlogin", method = RequestMethod.POST)
    public ModelAndView userLogin(User userLogin , RedirectAttributes attributes ){
		ModelAndView login = new ModelAndView();
		User userTemp;
		//casso login seja vazia
		if(userLogin.getLogin().isEmpty()){
			login.setViewName("redirect:/");
			attributes.addFlashAttribute("mensagem", "nome de usuario nao pode ser vazio");//com a mensagem de erro
			return login;//retorna a pagina incial com a mensagem de erro
		}else{
			//caso o login seja um email
			if(userLogin.getLogin().contains("@")){
				//busca o usuario por email
				userTemp = userService.getUserByEmail(userLogin.getLogin());
			}else{
				//caso naos seja busca pelo login
				userTemp = userService.getUserByLogin(userLogin.getLogin());
			}
		}
		//caso o usuario nao exista
		if(userTemp == null){
			login.setViewName("redirect:/");//retorna a pagina incial
			attributes.addFlashAttribute("mensagem", "nome de usuario nao encontrado");//com a mensagem de erro
			return login;
		}
		
		
		login.addObject("user", userTemp);

		if (userTemp.getPassword().equals(userLogin.getPassword())) {//se a senha for valida retorna a home.html
			login.setViewName("home");
		} else {//se nao fica no index 
			attributes.addFlashAttribute("mensagem", "senha errada");
			login.setViewName("redirect:/");
		}
		return login;
	}
    
   
    
}