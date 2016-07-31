package lexis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lexis.models.User;
import lexis.services.UserService;


@Controller
@RequestMapping("/")
public class IndexController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
	
	@RequestMapping("/")
    public ModelAndView index(){
    	ModelAndView index = new ModelAndView("/index");
    	index.addObject("userCadastro",new User());
    	index.addObject("userLogin",new User());
        return index;
    }

	/*//esse metodo redireciona toda url invalida para a pg error.html
	@RequestMapping(value = "/{url}")
    public String index(@PathVariable("url") String url){
		System.out.println("url: "+url);
		if(url.equals("index")){
			return "index";
		}
		return "home";
    }*/
	
    
	@RequestMapping(value = "userCadastro", method = RequestMethod.POST)
    public ModelAndView cadastro(User userCadastro){
    	//ModelAndView cadastro = new ModelAndView("/home"); //criando o Model and view setado para o home
    	userService.saveUser(userCadastro); // salvando usuario no BD local
    	//cadastro.addObject("user", userService.getUserById(userCadastro.getId())); //adicionando objeto para o Model
    	//cadastro.setViewName("index");
    	return login(userCadastro);//retornando o Model(com o obejto "user") para a view "Home"
    }
    
    @RequestMapping(value = "userlogin", method = RequestMethod.POST)
    public ModelAndView login(User userLogin){
		ModelAndView login = new ModelAndView();
		User userTemp = userService.getUserByLogin(userLogin.getLogin());
		login.addObject("user", userTemp);

		if (userTemp.getSenha().equals(userLogin.getSenha())) {
			login.setViewName("home");
		} else {
			login.setViewName("redirect:/");
		}
		return login;
	}
    
}