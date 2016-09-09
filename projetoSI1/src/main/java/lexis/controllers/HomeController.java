package lexis.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@RequestMapping(value = "viewFile", method = RequestMethod.POST)
	public File viewFile(@RequestBody Object json) {
		Map<String, Object> map = jsonToMap(json);
		
		File fileTemp;
		
		String fileName = (String) map.get("fileName");
		Type type = Type.valueOf((String) map.get("type"));
		
		fileTemp = explorer.openFile(fileName,Type.TXT);
		return EditorController.fileEditor(explorer,fileTemp);
	}

	@RequestMapping(value = "newFolder", method = RequestMethod.POST)
	public void newFolder(@RequestBody Object json) {	
		Map<String, Object> map = jsonToMap(json);
		
		String name = (String) map.get("name");
		Permission permission = Permission.create((String) map.get("permission"));	
		String date = (String) map.get("dateCreation");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateCreation = LocalDateTime.parse(date, formatter);
		
		explorer.currentFolder().addFolder(name, permission, dateCreation);
	}

	

	@RequestMapping(value = "newFile", method = RequestMethod.POST)
	public void newFile(@RequestBody Object json){
		Map<String, Object> map = jsonToMap(json);
		
		String name = (String) map.get("fileName");
		Permission permission = Permission.create((String) map.get("permission"));
		Type type = Type.valueOf((String) map.get("type"));
		String date = (String) map.get("dateCreation");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateCreation = LocalDateTime.parse(date, formatter);
		
		explorer.currentFolder().addFile(name, type, permission, dateCreation);
	}

	@RequestMapping(value = "renameFolder",method = RequestMethod.POST)
	public String renameFolder(@RequestBody Object json) {
		Map<String, Object> map = jsonToMap(json);
		String oldName = (String) map.get("oldName");
		String newName = (String) map.get("newName");
		explorer.renameAFolder(oldName,newName);
		return "pasta renomeada com sucesso, antigo nome: "+ oldName +" novo nome:" + newName;
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
	public String deleteFolder(@RequestBody Object json) {
		Map<String, Object> map = jsonToMap(json);
		String folderName = (String) map.get("folderName");
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
	
	private Map<String, Object> jsonToMap(Object json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonToString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		
			Map<String, Object> map = new HashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(jsonToString, new TypeReference<Map<String, String>>(){});
			return map;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
