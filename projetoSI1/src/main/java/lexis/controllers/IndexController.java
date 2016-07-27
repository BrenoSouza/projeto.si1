package lexis.controllers;

import org.h2.engine.SysProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lexis.domain.Users;


@Controller
@RequestMapping("/")
public class IndexController {

	
	@RequestMapping(value = "/{url}")
    public String index(@PathVariable("url") String url){
		System.out.println("url: "+url);
		if(url.equals("index")){
			return "index";
		}
		return "error";
    }
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
    
    @RequestMapping(method = RequestMethod.POST)
    public String adicionaUsers(String login,String senha,String email, Users user, Model model) {
          user.setLogin(login);
          user.setSenha(senha);
          user.setEmail(email);
          model.addAttribute("user", user);
          System.out.println("login"+ user.getLogin());
          return "editor";
    }
    
}