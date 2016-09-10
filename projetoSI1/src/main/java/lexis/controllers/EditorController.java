package lexis.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lexis.models.DataBase;
import lexis.models.Explorer;
import lexis.models.File;
import lexis.models.Folder;
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
		System.out.println("arquivo2 :"+file);
		if(file == null){
			editor = new ModelAndView("/home");
		}else{
			editor = new ModelAndView("/editor");
		}
		return editor;
	}
	
	@RequestMapping(value = "viewFile", method = RequestMethod.POST)
	public void viewFile(@RequestBody Object json) {
		JsonUtil.json(json);
		
		setExplorer();
		
		
		String fileName = JsonUtil.getName();
		Type type = JsonUtil.getType();
		System.out.println(fileName+"/"+type);
		this.file = explorer.getFile(fileName,type);
		System.out.println("arquivo1 :"+file);
	}
	
	@RequestMapping(value = "viewFile", method = RequestMethod.GET)
	public File viewFile() {
		return file;
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
	private User userLogged(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public void setExplorer(){
		this.explorer = DataBase.getInstance().getUser(userLogged().getUsername());
	}
}
