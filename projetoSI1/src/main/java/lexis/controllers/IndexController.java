package lexis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lexis.models.User;
import lexis.services.UserServiceDAO;
import lexis.util.UserLoginService;
import lexis.util.UserRegisterService;

@Controller
public class IndexController {

	// objeto responsavel por manipular User
	private UserServiceDAO userService;

	@Autowired
	public void setUserService(UserServiceDAO userService) {
		this.userService = userService;
	}

	/**
	 * Metodo responsavel pelo index.html
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/","login","index"}) // acessa o url "/"
	public ModelAndView index(@AuthenticationPrincipal User user) {
		ModelAndView index = new ModelAndView("/index");
		if(user != null){
			index.setViewName("/home");
		}

		// cria um novo objeto que aponta para "index.html"
		

		// adicionar um User com a chave "usercadastro"
		index.addObject("userRegister", new User());

		// retorna o objeto para o index.html
		return index;
	}

	/**
	 * Metodo resposavel pelo cadastro de um novo usuario, recebe do form do
	 * index.html um USER e caso nao haja erro no usuario passado retorna ao
	 * metodo de login para a validação.
	 * 
	 * @param userRegister
	 *            - Recebe o paramentro "userRegister" na url com o metodo POST.
	 * @param attributes
	 *            - Obejto da framework que permite o redirecionamento da pagina
	 *            com mensagem de erro.
	 * @return Caso o cadastro seja feito com sucesso, retorna ao metoodo
	 *         userLogin com o obejto user caso nao retorna a pagina index.html
	 *         com a mensagem de erro.
	 */
	@RequestMapping(value = "userRegister", method = RequestMethod.POST) // produces = "application/json"
	public ModelAndView userRegister(User userRegister, RedirectAttributes attributes) {
		
		ModelAndView register = new ModelAndView("redirect:login");
		
		// cria um objeto responsavel pelo cadastro do usuario
		UserRegisterService userRegisterService = new UserRegisterService(userService);
		
		try {
			// verifica se a erros no usuario
			userRegisterService.hasErrorIn(userRegister);

			// caso nao haja erro registra o usuario
			userRegisterService.RegisterUser(userRegister);

			attributes.addFlashAttribute("mensagem", "cadastro efetuado com sucesso");
			
			// retorna para o metodo responsavel pelo login
			return  register;

			// se houver erros:
		} catch (Exception e) {
			// com as mensagens de erro
			attributes.addFlashAttribute("mensagem", e.getMessage());

			return register;
		}

	}

	/**
	 * Metodo resposavel pela validação dos campos e login do usuario.
	 * 
	 * @param userLogin
	 *            - Recebe o paramentro "userLogin" na url com o metodo POST.
	 * @param attributes
	 *            - Obejto da framework que permite o redirecionamento da pagina
	 *            com mensagem de erro.
	 * @return Retona a home.html se nao houver erros,caso contrario retorna a
	 *         index.html com a mensagem de erro.
	 */
	@RequestMapping("/home")//(value = "userlogin", method = RequestMethod.POST)
	public ModelAndView userLogin(User userLogin, RedirectAttributes attributes) {
		ModelAndView login = new ModelAndView();
		
		//login.addObject("user", userTemp);
		login.setViewName("/home");
		return login;
		// cria um objeto resposavel pelo login do usuario
		/*UserLoginService userLoginService = new UserLoginService(userService);

		try {
			// tenta adicionar user interno a chave "user", aprtir do userLogin
			// passado
			login.addObject("user", userLoginService.getUser(userLogin));

			// caso nao de nenhum erro retorna para o home.html
			login.setViewName("home");

			return login;

			// caso haja erros de validacao ou sintax no obejeto passado:
		} catch (Exception e) {
			// retorna a pagina incial
			login.setViewName("redirect:/");

			// com as mensagens de erros
			attributes.addFlashAttribute("mensagem", e.getMessage());

			return login;
		}*/

	}

}