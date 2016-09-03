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

import lexis.models.DataBase;
import lexis.models.Explorer;
import lexis.models.File;
import lexis.models.FileAndFolder;
import lexis.models.Folder;
import lexis.models.Permission;
import lexis.models.Type;
import lexis.models.User;
import lexis.services.UserServiceDAO;

@RestController
@RequestMapping("/home")
public class HomeController {

	private UserServiceDAO userService;
	private Explorer explorer;
	

	@Autowired
	public void setUserService(UserServiceDAO userService) {
		this.userService = userService;
	}

	/**
	 * Metodo responsavel pelo home.html
	 * 
	 * @return Retorna a pagina home.html
	 */
	@RequestMapping(value = "/userTest", method = RequestMethod.POST)
	public User home(@RequestBody User user) {
		System.out.println(user.getLogin());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		return userLogged();
	}

	/**
	 * Metodo responsavel pelo editor.html
	 * 
	 * @return Retorna a pagina editor.html
	 */
	@RequestMapping(value = "explorer", method = RequestMethod.GET)
	public Folder index() {
		setExplorer();
		return explorer.currentFolder();
	}
	
	@RequestMapping(value = "explorer/{folderName}", method = RequestMethod.GET)
	public Folder viewFolder(@PathVariable String folderName) {
		Folder folderTemp = explorer.goDown(folderName);
		return folderTemp;
	}
	
	@RequestMapping(value = "explorer/back", method = RequestMethod.GET)
	public Folder backFolder() {
		Folder folderTemp = explorer.goUp();
		return folderTemp;
	}
	
	@RequestMapping(value = "viewFile/{fileName}/{fileType}", method = RequestMethod.GET)
	public File viewFile(@PathVariable String fileName,@PathVariable String fileType) {
		File fileTemp;
		if(fileType.equals("txt")){
			fileTemp = explorer.openFile(fileName,Type.TXT);
		}else{
			fileTemp = explorer.openFile(fileName,Type.MD);
		}
		return EditorController.fileEditor(explorer,fileTemp);
	}

	@RequestMapping(value = "newFolder", method = RequestMethod.POST)
	public void newFolder(@RequestBody Folder folder) {
		explorer.currentFolder().getFolderDirectory().add(folder);
	}

	@RequestMapping(value = "newFile", method = RequestMethod.POST)
	public File newFile(@RequestBody File file){
		/*File fileTemp;
		if(fileType.equals("txt")){
			fileTemp = explorer.openFile(fileName,Type.TXT);
		}else{
			fileTemp = explorer.openFile(fileName,Type.MD);
		}*/
		return EditorController.fileEditor(explorer,file);
	}

	@RequestMapping(value = "renameFolder",method = RequestMethod.POST)
	public String renameFolder(@RequestBody String[] names) {
		explorer.renameAFolder(names[0],names[1]);
		return "pasta renomeada com sucesso, antigo nome: "+ names[0] +" novo nome:" + names[1];
	}
	
	@RequestMapping(value = "renameFile",method = RequestMethod.POST)
	public String renameFile(@RequestBody String[] names) {
		if(names[3].equals("txt")){
			explorer.renameAFile(names[0],names[1],Type.TXT);
		}else{
			explorer.renameAFile(names[0],names[1],Type.MD);
		}		
		return "arquivo renomeado com sucesso, antigo nome: "+ names[0] +" novo nome:" + names[1];
	}

	@RequestMapping(value = "deleteFolder", method = RequestMethod.POST)
	public String deleteFolder(@RequestBody String folderName) {
		explorer.removeFolder(folderName);
		return "pasta: "+ folderName +" deletada com sucesso";
	}
	
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public String deleteFile(@RequestBody String[] fileName) {
		if(fileName[1].equals("txt")){
			explorer.removeFile(fileName[0], Type.TXT);
		}else{
			explorer.removeFile(fileName[0], Type.MD);
		}
		
		return "arquivo: "+ fileName +" deletado com sucesso";
	}
	
	private User userLogged(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public Explorer getExplorer(){
		return explorer;
	}
	public void setExplorer(){
		
		this.explorer = DataBase.getInstance().getUser(userLogged().getUsername());
	}
	
}
