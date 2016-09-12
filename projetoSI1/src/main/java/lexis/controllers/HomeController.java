package lexis.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lexis.models.DataBase;
import lexis.models.Explorer;
import lexis.models.File;
import lexis.models.Folder;
import lexis.models.Notification;
import lexis.models.Permission;
import lexis.models.Type;
import lexis.models.User;
import lexis.services.UserServiceDAO;
import lexis.util.JsonUtil;

@RestController
@RequestMapping("/home")
public class HomeController {
	// objeto responsavel por manipular User
	private UserServiceDAO userService;

	@Autowired
	public void setUserService(UserServiceDAO userService) {
		this.userService = userService;
	}

	private Explorer explorer;

	/**
	 * Metodo responsavel pelo home.html
	 * 
	 * @return Retorna a pagina home.html
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String home(@RequestBody User user) {
		return userLogged().getUsername();
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
	
	@RequestMapping(value = "newFolder", method = RequestMethod.POST)
	public void newFolder(@RequestBody Object json) {	
		JsonUtil.json(json);
		
		String name = JsonUtil.getName();
		Permission permission = JsonUtil.getPermission();
		LocalDateTime dateCreation = JsonUtil.GetDateCreation();
		
		explorer.currentFolder().addFolder(name, permission, dateCreation);
	}

	@RequestMapping(value = "newFile", method = RequestMethod.POST)
	public void newFile(@RequestBody Object json){
		JsonUtil.json(json);
		
		String name = JsonUtil.getName();
		Permission permission = JsonUtil.getPermission();
		Type type = JsonUtil.getType();
		LocalDateTime dateCreation = JsonUtil.GetDateCreation();
		
		explorer.currentFolder().addFile(name, type, permission, dateCreation);
	}

	@RequestMapping(value = "renameFolder",method = RequestMethod.POST)
	public void renameFolder(@RequestBody Object json) {
		JsonUtil.json(json);
		
		String oldName = JsonUtil.getOldName();
		String newName = JsonUtil.getNewName();
		
		explorer.renameFolder(oldName,newName);
	}
	
	@RequestMapping(value = "renameFile",method = RequestMethod.POST)
	public void renameFile(@RequestBody Object json) {
		JsonUtil.json(json);
		
		String oldName = JsonUtil.getOldName();
		String newName = JsonUtil.getNewName();
		Type oldType = JsonUtil.getOldType();
		Type newType = JsonUtil.getNewType();
		
		explorer.renameFile(oldName, newName, oldType);
		explorer.getFile(newName, oldType).setType(newType);

	}

	@RequestMapping(value = "deleteFolder", method = RequestMethod.POST)
	public void deleteFolder(@RequestBody Object json) {
		JsonUtil.json(json);
		String folderName = JsonUtil.getName();
		explorer.removeFolder(folderName);
	}
	
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public void deleteFile(@RequestBody Object json) {
		JsonUtil.json(json);
		
		String fileName = JsonUtil.getName();
		Type type = JsonUtil.getType();
		explorer.removeFile(fileName, type );	
		
	}
	
	@RequestMapping(value = "shareFile", method = RequestMethod.POST)
	public void shareFile(@RequestBody Object json){
		JsonUtil.json(json);
		
		//propietario e seu explorer
		User userOwner = userLogged();
		Explorer ownersExplorer = explorer;
		
		
		//atributos passados pelo usuario
		String fileNameShared = JsonUtil.getName();
		Type fileTypeShared = JsonUtil.getType();
		String userNameToShared = JsonUtil.getUserName();
		Boolean read = JsonUtil.isRead();
		Boolean write = JsonUtil.isWrite();
		
		Boolean userExists = userService.existsByEmailOrLogin(userNameToShared);
		if(userExists){
			//procura pelo usuario fornecido
			User userToShareWith = userService.getUserByEmailOrLogin(userNameToShared);
			//procura pelo explorer do usuario	
			Explorer explorerToShareWith = DataBase.getInstance().getUser(userToShareWith.getUsername());	
			if(write){
				DataBase.getInstance().shareFileReadAndWrite(userOwner, ownersExplorer, fileNameShared, fileTypeShared, userToShareWith, explorerToShareWith,LocalDateTime.now());
			}else if(read){
				DataBase.getInstance().shareFileReadOnly(userOwner, ownersExplorer, fileNameShared, fileTypeShared, userToShareWith, explorerToShareWith,LocalDateTime.now());
			}else{
				System.out.println("deu bug");
			}
		}else{
			System.out.println("usuario "+ userNameToShared +" nao existe");
		}		
	}
	
	@RequestMapping(value = "notification", method = RequestMethod.GET)
	public Notification[] getNotification(){
		return explorer.getNotifications();
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
