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

	@RequestMapping(value = "newFolder/{folderName}", method = RequestMethod.GET)
	public void newFolder(@PathVariable String folderName) {
		explorer.currentFolder().addFolder(folderName,Permission.PRIVATE);
	}

	@RequestMapping(value = "newFile/{fileName}/{fileType}", method = RequestMethod.GET)
	public File newFile(@PathVariable String fileName,@PathVariable String fileType) throws Exception {
		File fileTemp;
		if(fileType.equals("txt")){
			fileTemp = explorer.openFile(fileName,Type.TXT);
		}else{
			fileTemp = explorer.openFile(fileName,Type.MD);
		}
		return EditorController.fileEditor(explorer,fileTemp);
	}

	@RequestMapping(value = "renameFolder/{oldName}/{newName}",method = RequestMethod.GET)
	public String renameFolder(@PathVariable String oldName,@PathVariable String newName) {
		explorer.renameAFolder(oldName,newName);
		return "pasta renomeada com sucesso, antigo nome: "+ oldName +" novo nome:" + newName;
	}
	
	@RequestMapping(value = "renameFile/{oldName}/{fileType}/{newName}",method = RequestMethod.GET)
	public String renameFile(@PathVariable String oldName,@PathVariable String fileType,@PathVariable String newName) {
		if(fileType.equals("txt")){
			explorer.renameAFile(oldName,newName,Type.TXT);
		}else{
			explorer.renameAFile(oldName,newName,Type.MD);
		}
		
		return "arquivo renomeado com sucesso, antigo nome: "+ oldName +" novo nome:" + newName;
	}

	@RequestMapping(value = "deleteFolder/{folderName}", method = RequestMethod.GET)
	public String deleteFolder(@PathVariable String folderName) {
		explorer.removeFolder(folderName);
		return "pasta: "+ folderName +" deletada com sucesso";
	}
	
	@RequestMapping(value = "deleteFile/{fileName}/{fileType}", method = RequestMethod.GET)
	public String deleteFile(@PathVariable String fileName,@PathVariable String fileType) {
		if(fileType.equals("txt")){
			explorer.removeFile(fileName, Type.TXT);
		}else{
			explorer.removeFile(fileName, Type.MD);
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
