package lexis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lexis.models.User;
import lexis.services.UserService;
import lexis.util.UserLoginService;
import lexis.util.UserRegisterService;

@Controller
@RequestMapping("/")
public class IndexController {

	private UserService userService;// objeto responsavel por manipular User

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping // acessa o url "/"
	public ModelAndView index() {
		
		// cria um novo objeto que aponta para "index.html"
		ModelAndView index = new ModelAndView("/index");
		
		// adicionar um User com a chave "usercadastro"
		index.addObject("userRegister", new User());
		
		// adiciona um User com a chave "userLogin"
		index.addObject("userLogin", new User());
		
		// retorna o objeto para o index.html
		return index;
	}

	/*
	 * recebe o paramentro "userCadasstro" na url com o metodo POST que indica
	 * que a pagina irá enviar dados para o controller esses dados sao enviados
	 * atravez de um form no index.html nesse caso esses dados é um objeto do
	 * tipo User(userCadastro) logo apos receber o controler salva no servico
	 * que controla User e em seguida envia a requisição ao metodo Login, para
	 * que seja feita a validação
	 */
	// recebendo o objeto user
	@RequestMapping(value = "userRegister", method = RequestMethod.POST)
	public ModelAndView userRegister(User userRegister, RedirectAttributes attributes) {
		ModelAndView login = new ModelAndView();
		//cria um objeto responsavel pelo cadastro do usuario
		UserRegisterService userRegisterService = new UserRegisterService(userService);

		try {
			// verifica se a erros no usuario
			userRegisterService.hasErrorIn(userRegister);
			
			//caso nao haja erro registra o usuario
			userRegisterService.RegisterUser(userRegister);
			
			// retornando o Model(com o obejto "user") para view "Home"
			return userLogin(userRegister, attributes);
		
			//se hoiver erros:
		} catch (Exception e) {
			// retorna a pagina incial
			login.setViewName("redirect:/");
			
			// com as mensagens de erros
			attributes.addFlashAttribute("mensagem", e.getMessage());
			
			return login;
		}
		
	}

	/*
	 * recebe o paramentro "userLogin" na url com o metodo POST que indica que a
	 * pagina irá enviar dados para o controller esses dados sao enviados
	 * atravez de um form no index.html nesse caso esses dados é um objeto do
	 * tipo User(userLogin) logo apos receber o User o controler salva o objeto
	 * em um ModelAndView em seguida faz uma verificação simples da senha se
	 * forem iguais retorna para o home.html com o obejo User se forem
	 * diferentes retorna a mesma pagina
	 */
	@RequestMapping(value = "userlogin", method = RequestMethod.POST)
	public ModelAndView userLogin(User userLogin, RedirectAttributes attributes) {
		
		ModelAndView login = new ModelAndView();
		//cria um objeto resposavel pelo login do usuario
		UserLoginService userLoginService = new UserLoginService(userService);

		try {
			//tenta adicionar user interno a chave "user", aprtir do userLogin passado 
			login.addObject("user", userLoginService.getUser(userLogin));
			
			//retorna para o home.html
			login.setViewName("home");
			
			return login;
			
		//caso haja erros de validacao ou sitax no obejeto passado:
		} catch (Exception e) {
			// retorna a pagina incial
			login.setViewName("redirect:/");
			
			// com as mensagens de erros
			attributes.addFlashAttribute("mensagem", e.getMessage());
			
			return login;
		}	

	}

}