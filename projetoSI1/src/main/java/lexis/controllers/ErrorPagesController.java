package lexis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesController {

	@RequestMapping("/404")
	public String notFound(){
		return "404";
	}
	
	@RequestMapping("/403")
	public String forbidden(){
		return "403";
	}
}
