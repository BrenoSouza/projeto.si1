package lexis.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lexis.models.Explorer;
import lexis.models.File;
import lexis.models.Folder;
import lexis.models.Type;
import lexis.models.User;

@RestController
@RequestMapping("/editor")
public class EditorController {

	private Explorer explorer;
	private File fileTemp;
	
	@RequestMapping
	public ModelAndView editor() {
		ModelAndView editor;
		if(fileTemp == null){
			editor = new ModelAndView("/home");
		}else{
			editor = new ModelAndView("/editor");
		}
		return editor;
	}
	
	@RequestMapping(value = "viewFile", method = RequestMethod.POST)
	public File viewFile(@RequestBody Object json) {
		Map<String, Object> map = jsonToMap(json);
		
		
		
		String fileName = (String) map.get("fileName");
		Type type = Type.valueOf((String) map.get("type"));
		
		this.fileTemp = explorer.openFile(fileName,Type.TXT);
		return EditorController.fileEditor(explorer,fileTemp);
	}
	
	@RequestMapping(value = "viewFile", method = RequestMethod.GET)
	public File viewFile() {
		return fileTemp;
	}
	

	@RequestMapping("fileEditor")
	public static File fileEditor(Explorer explorer,File file) {
		ModelAndView editor = new ModelAndView("/editor");
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
}
