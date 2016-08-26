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
import org.springframework.web.servlet.ModelAndView;

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
	public Folder viewFolder(@PathVariable String folderName) {
		Folder folderTemp = currentFolder().getFolder(folderName);
		return folderTemp;
	}
	
	@RequestMapping(value = "viewFile/{fileName}", method = RequestMethod.GET)
	public File viewFile(@PathVariable String fileName) {
		File fileTemp = currentFolder.getFile(fileName);
		return EditorController.fileEditor(currentFolder,fileTemp);
	}

	@RequestMapping(value = "newFolder/{folderName}", method = RequestMethod.GET)
	public void newFolder(@PathVariable String folderName) {
		currentFolder.addFolder(folderName);
	}

	@RequestMapping(value = "newFile/{fileName}", method = RequestMethod.GET)
	public void newFile(@PathVariable String fileName) throws Exception {
		currentFolder.getDirectory().add(new File(fileName))
;	}

	@RequestMapping(value = "renameFolder/{oldName}/{newName}",method = RequestMethod.GET)
	public String renameFolder(@PathVariable String oldName,@PathVariable String newName) {
		currentFolder.getFolder(oldName).setName(newName);
		return "pasta renomeada com sucesso, antigo nome: "+ oldName +" novo nome:" + newName;
	}
	
	@RequestMapping(value = "renameFile/{oldName}/{newName}",method = RequestMethod.GET)
	public String renameFile(@PathVariable String oldName,@PathVariable String newName) {
		currentFolder.getFile(oldName).setName(newName);
		return "arquivo renomeado com sucesso, antigo nome: "+ oldName +" novo nome:" + newName;
	}

	@RequestMapping(value = "deleteFolder/{folderName}", method = RequestMethod.GET)
	public String deleteFolder(@PathVariable String folderName) {
		currentFolder.getDirectory().remove(currentFolder.getFolder(folderName));
		return "pasta: "+ folderName +" deletada com sucesso";
	}
	
	@RequestMapping(value = "deleteFile/{fileName}", method = RequestMethod.GET)
	public String deleteFile(@PathVariable String fileName) {
		currentFolder.getDirectory().remove(currentFolder.getFile(fileName));
		return "arquivo: "+ fileName +" deletado com sucesso";
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
