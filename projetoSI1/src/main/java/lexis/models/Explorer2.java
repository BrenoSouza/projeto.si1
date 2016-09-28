package lexis.models;

import java.time.LocalDateTime;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lexis.services.FileServiceDAO;
import lexis.services.FolderServiceDAO;
import lexis.services.UserServiceDAO;

@Component
public class Explorer2 {

	private static UserServiceDAO userService;
	private static FolderServiceDAO folderService;
	private static FileServiceDAO fileService;
	private Stack<Folder> stackFolder;
	private Folder root;
	private Folder trash;
	private Folder shared;
	private Folder currentFolder;
	private User user;

	@Autowired
	public void setUserService(UserServiceDAO userService2) {
		userService = userService2;
	}
	
	@Autowired
	public void setFolderService(FolderServiceDAO folderService2) {
		folderService = folderService2;
	}
	
	@Autowired
	public void setFileService(FileServiceDAO fileService2){
		fileService = fileService2;
	}
	
	public Explorer2(){}

	public Explorer2(String userName){
		//pega o usuario logado
		this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// pega a pasta root do usuario
		this.root = folderService.findByRoot(user.getUsername());
		//pega a lixeira do usuario
		this.trash = folderService.findByTrash(user.getUsername());
		//pega a pasta compartilhada do usuario
		this.shared = folderService.findByShared(user.getUsername());
		this.stackFolder = new Stack<Folder>();
		stackFolder.push(root);
		this.currentFolder = new Folder();
		
		//para mais servicos consultar os metodos de 
		//folderService e fileService
	}
	
	public Folder currentFolder() {
		currentFolder.setFolderDirectory(folderService.findAllByParentFolder(stackFolder.peek().getId()));
		return currentFolder;
	}

	public Folder goDown(String folderName) {
		Folder folderTemp = folderService.findByNameAndParentFolder(folderName, stackFolder.peek().getId());
		stackFolder.push(folderTemp);
		currentFolder.setFolderDirectory(folderService.findAllByParentFolder(folderTemp.getId()));
		return folderTemp;
	}

	public Folder goUp() {
		stackFolder.pop();
		currentFolder.setFolderDirectory(folderService.findAllByParentFolder(stackFolder.peek().getId()));
		return currentFolder;
	}

	public void addFolder(String name, Permission permission, LocalDateTime dateCreation) throws Exception {
		Folder folderTemp = new Folder();
		folderTemp.setName(name);
		folderTemp.setPermission(permission);
		folderTemp.setDateCreation(dateCreation);
		folderTemp.setOwner(user.getId());
		folderTemp.setParentFolder(stackFolder.peek().getId());
		folderService.saveFolder(folderTemp);
		
	}
	
	
	
	
}
