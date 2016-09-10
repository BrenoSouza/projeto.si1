package lexis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lexis.models.User;
import lexis.services.UserServiceDAO;
import lexis.util.UserRegisterHelper;

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
		UserRegisterHelper userRegisterHelper = new UserRegisterHelper(userService);
		
		try {
			
			// verifica se a erros no usuario
			userRegisterHelper.hasErrorIn(userRegister);

			// caso nao haja erro registra o usuario
			userRegisterHelper.RegisterUser(userRegister);

			attributes.addFlashAttribute("mensagemsuccess", "cadastro efetuado com sucesso");
			
			// retorna para o metodo responsavel pelo login
			return  register;

			// se houver erros:
		} catch (Exception e) {
			// com as mensagens de erro
			attributes.addFlashAttribute("mensagemerror", e.getMessage());

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
	@RequestMapping("/home")
	public ModelAndView userLogin(User userLogin, RedirectAttributes attributes) {
		ModelAndView login = new ModelAndView();
		login.setViewName("/home");
		return login;
	}

}