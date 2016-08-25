package lexis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lexis.models.File;
import lexis.models.FileAndFolder;
import lexis.models.Folder;
import lexis.models.User;
import lexis.services.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

	private UserService userService;
	private Folder currentFolder;
	

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Metodo responsavel pelo home.html
	 * 
	 * @return Retorna a pagina home.html
	 */
	@RequestMapping("/userjson")
	public User home() {
		return userLogged();
	}

	/**
	 * Metodo responsavel pelo editor.html
	 * 
	 * @return Retorna a pagina editor.html
	 */
	@RequestMapping(value = "explorer", method = RequestMethod.GET)
	public List<FileAndFolder> explorer() {
		setCurrentFolder();
		return currentFolder().getDirectory();
	}
	
	@RequestMapping(value = "explorer/{folderName}", method = RequestMethod.GET)
	public Folder getFolder(@PathVariable String folderName) {
		Folder folderTemp = currentFolder().getFolder(folderName);
		return folderTemp;
	}

	@RequestMapping(value = "newFolder", method = RequestMethod.POST)
	@ResponseBody
	public void newFolder(@RequestBody String NameFolder) {
		currentFolder.addFolder(NameFolder);
	}

	@RequestMapping(value = "newFile", method = RequestMethod.POST)
	@ResponseBody
	public void newFile(@RequestBody File file) throws Exception {
		System.out.println("teste file");
		//currentFolder.addFile(file);
	}

	@RequestMapping("view")
	public void view() {

	}

	@RequestMapping("edit")
	public void edit() {

	}

	@RequestMapping("delete")
	public void delete() {

	}
	
	private User userLogged(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public Folder currentFolder(){
		return currentFolder;
	}
	public void setCurrentFolder(){
		this.currentFolder = userLogged().getRoot();
	}
	
}
