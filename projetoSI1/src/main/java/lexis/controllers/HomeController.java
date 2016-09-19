package lexis.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lexis.models.DataBase;
import lexis.models.Explorer;
import lexis.models.Folder;
import lexis.models.Notification;
import lexis.models.Pair;
import lexis.models.Permission;
import lexis.models.SharedFile;
import lexis.models.TrashFileAndFolder;
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
	 * @return retorna o usuario logado na sessão
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String home() {
		return userLogged().getUsername();
	}
	
	/**
	 * @return retorna a pasta na qual o usuario se encontra
	 */
	@RequestMapping(value = "explorer", method = RequestMethod.GET)
	public Folder index() {
		setExplorer();
		return explorer.currentFolder();
	}
	
	/**
	 * metodo que faz com que o usuario avançe entre as subpastas
	 * @param folderName - nome ao qual o usuario deseja entrar
	 * @return a pasta que ele entrou
	 */
	@RequestMapping(value = "explorer/{folderName}", method = RequestMethod.GET)
	public Folder viewFolder(@PathVariable String folderName) {
		Folder folderTemp = explorer.goDown(folderName);
		return folderTemp;
	}
	
	/**
	 * metodo que faz com que o usuario regrida a pasta anterior
	 * @return a pasta anterior que o usuario se encontra
	 */
	@RequestMapping(value = "explorer/back", method = RequestMethod.GET)
	public Folder backFolder() {
		Folder folderTemp = explorer.goUp();
		return folderTemp;
	}
	
	/**
	 * metodo reponsavel pela criação de novas pastas
	 * @param json Object Json com os atributos da nova pasta
	 */
	@RequestMapping(value = "newFolder", method = RequestMethod.POST)
	public void newFolder(@RequestBody Object json) {	
		JsonUtil.json(json);
		
		String name = JsonUtil.getName();
		Permission permission = JsonUtil.getPermission();
		LocalDateTime dateCreation = JsonUtil.GetDateCreation();
		
		explorer.currentFolder().addFolder(name, permission, dateCreation);
	}

	/**
	 * metodo responsavel pela criação de um novo arquivo
	 * @param json Object Json com os atributos de um novo arquivo
	 */
	@RequestMapping(value = "newFile", method = RequestMethod.POST)
	public void newFile(@RequestBody Object json){
		JsonUtil.json(json);
		
		String name = JsonUtil.getName();
		Permission permission = JsonUtil.getPermission();
		Type type = JsonUtil.getType();
		LocalDateTime dateCreation = JsonUtil.GetDateCreation();
		
		explorer.currentFolder().addFile(name, type, permission, dateCreation);
	}

	/**
	 * metodo responsavel pela renomeação de pastas
	 * @param json Object Json com os atributos para a renomeação de uma pasta
	 */
	@RequestMapping(value = "renameFolder",method = RequestMethod.POST)
	public void renameFolder(@RequestBody Object json) {
		JsonUtil.json(json);
		
		String oldName = JsonUtil.getOldName();
		String newName = JsonUtil.getNewName();
		
		explorer.renameFolder(oldName,newName);
	}
	
	/**
	 * metodo responsavel pela renomeação de arquivos
	 * @param json Object Json com os atributos para renomeação de arquivos 
	 */
	@RequestMapping(value = "renameFile",method = RequestMethod.POST)
	public void renameFile(@RequestBody Object json) {
		JsonUtil.json(json);
		
		String oldName = JsonUtil.getOldName();
		String newName = JsonUtil.getNewName();
		Type oldType = JsonUtil.getOldType();
		Type newType = JsonUtil.getNewType();
		
		explorer.renameFile(oldName, newName, oldType, newType);
	}

	/**
	 * metodo resposavel pela deleção de pastas
	 * @param json Object Json com os atributos para a deleção de pastas
	 */
	@RequestMapping(value = "deleteFolder", method = RequestMethod.POST)
	public void deleteFolder(@RequestBody Object json) {
		JsonUtil.json(json);
		String folderName = JsonUtil.getName();
		explorer.removeFolder(folderName);
	}
	
	/**
	 * metodo resposavel pela deleção de arquivos
	 * @param json Object Json com os atributos para a deleção de arquivos
	 */
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public void deleteFile(@RequestBody Object json) {
		JsonUtil.json(json);
		
		String fileName = JsonUtil.getName();
		Type type = JsonUtil.getType();
		explorer.removeFile(fileName, type );	
	}
	
	/**
	 * metodo reponsavel pelo compartilhamento de arquivo
	 * @param json Object Json com os atributos necessario para o compartilhamento
	 */
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
	
	/**
	 * retorna as notificações
	 * @return uma lista com notificações
	 */
	@RequestMapping(value = "notification", method = RequestMethod.GET)
	public Notification[] getNotification(){
		return explorer.getNotifications();
	}
	
	/**
	 * metodo para mudar o status de uma notificação
	 * @param json
	 */
	@RequestMapping(value = "setReadNotification", method = RequestMethod.POST)
	public void setReadNotification(@RequestBody Object json){
		JsonUtil.json(json);
		
		int position = JsonUtil.getIndex();
		Notification[] notifications = explorer.getNotifications();
		
		notifications[position].setUnread(Notification.READ);
	}
	
	/**
	 * @return a lista de arquivos comparilhado com permissão de leitura e escrita
	 */
	@RequestMapping(value = "SharedFiles", method = RequestMethod.GET)
	public List<Pair<String, List<SharedFile>>> getSharedFilesReadAndWrite(){
		List<Pair<String, List<SharedFile>>> temp = explorer.getSharedFiles();
		return temp;
		
		
	}
	
	/**
	 * metodo resposavel por retornar um arquivo compartilhado
	 * @param json Objec json com a posição do arquivo na lista
	 * @return o arquivo solicitado
	 */
	@RequestMapping(value = "getSharedFile", method = RequestMethod.POST)
	public SharedFile getSharedFile(@RequestBody Object json){
		int position = JsonUtil.getIndex();
		return explorer.getNotifications()[position].getSharedFile();
	}
	/**
	 * metodo que retorna a lixeira do usuario
	 * @return Object Json contendo a lixeira do usuario
	 */
	@RequestMapping(value = "trash", method = RequestMethod.GET)
	public TrashFileAndFolder[] getTrash(){
		return explorer.getTrash();
	}
	
	/**
	 * @return retorna o usuario logado na sessão
	 */
	private User userLogged(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
		
	/**
	 * metodo que modifica o explorer
	 */
	private void setExplorer(){
		this.explorer = DataBase.getInstance().getUser(userLogged().getUsername());
	}

}
