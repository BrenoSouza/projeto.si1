package lexis.controllers;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lexis.models.DataBase;
import lexis.models.Explorer;
import lexis.models.File;
import lexis.models.Type;
import lexis.models.User;
import lexis.util.JsonUtil;

@RestController
@RequestMapping("/editor")
public class EditorController {

	private Explorer explorer;
	private static File file;
	
	@RequestMapping
	public ModelAndView editor() {
		ModelAndView editor;
		if(file == null){
			editor = new ModelAndView("home");
		}else{
			editor = new ModelAndView("editor");
		}
		return editor;
	}
	
	@RequestMapping(value = "viewFile", method = RequestMethod.POST)
	public void viewFile(@RequestBody Object json) {
		JsonUtil.json(json);
		
		setExplorer();

		String fileName = JsonUtil.getName();
		Type type = JsonUtil.getType();
		this.file = explorer.getFile(fileName,type);
	}
	
	@RequestMapping(value = "viewFile", method = RequestMethod.GET)
	public File viewFile() {
		return file;
	}

	@RequestMapping(value = "saveData", method = RequestMethod.POST)
	public void saveData(@RequestBody Object json){
		JsonUtil.json(json);
		
		String data = JsonUtil.getData();
		LocalDateTime dateEdition = LocalDateTime.now();
		file.setData(data, dateEdition);
	}
	
	private User userLogged(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public void setExplorer(){
		this.explorer = DataBase.getInstance().getUser(userLogged().getUsername());
	}
}
