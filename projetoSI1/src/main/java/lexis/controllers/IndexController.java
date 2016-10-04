package lexis.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lexis.controllers.util.UserRegisterHelper;
import lexis.models.User;

@Controller
public class IndexController {
		
	/**
	 * Metodo responsavel pelo index.html
	 * caso um usuario ja esteja logado, ele redireciona
	 * autimaticamente para o home.html 
	 * @return
	 */
	@RequestMapping(value = {"/","index","login"}) // acessa o url "/"
	public ModelAndView login(@AuthenticationPrincipal User user) {		
		ModelAndView index = new ModelAndView("index");
		if(user != null){
			index.setViewName("redirect:home");
		}
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
		UserRegisterHelper userRegisterHelper = new UserRegisterHelper();
		
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

}