package lexis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lexis.models.Folder;
import lexis.services.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping("home")
	public String home(){
		return "home";
	}
	
	@RequestMapping("editor")
	public String explorer(){
		return "editor";
	}
	 
	@RequestMapping("newFolder")
	public void newFolder(){
		
	}
	
	@RequestMapping("newFile")
	public void newFile(){
		
	}
	@RequestMapping("view")
	public void view(){
		
	}
	@RequestMapping("edit")
	public void edit(){
		
	}
	@RequestMapping("delete")
	public void delete(){
		
	}
	
}
