package lexis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesController {

	/**
	 * mapeamento para a pagina de erro padrão
	 * @return
	 */
	@RequestMapping("404")
	public String notFound(){
		return "404";
	}
	
	/**
	 * mapeamento para a pagina de acesso restrito
	 * @return
	 */
	@RequestMapping("403")
	public String forbidden(){
		return "403";
	}
}
