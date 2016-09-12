package lexis.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lexis.models.Permission;
import lexis.models.Type;

public class JsonUtil {
	private static Map<String, Object> map;

	public static void json(Object json){
		map = jsonToMap(json);
	}
	
	public static String getName(){
		return (String) map.get("name");
	}
	
	public static Permission getPermission(){
		return Permission.create((String) map.get("Permission"));
	}
	
	public static LocalDateTime GetDateCreation(){
		String date = (String) map.get("dateCreation");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return  LocalDateTime.parse(date, formatter);
	}
	
	public static Type getType(){
		return Type.valueOf((String) map.get("type"));
	}
	
	public static String getOldName(){
		return (String) map.get("oldName");
	}
	
	public static String getNewName(){
		return (String) map.get("newName");
	}
	
	public static String getData(){
		return (String) map.get("data");
	}
	
	public static Type getOldType(){
		return Type.valueOf((String) map.get("oldType"));
	}
	
	public static Type getNewType(){
		return Type.valueOf((String) map.get("newType"));
	}
	
	public static String getUserName(){
		return (String) map.get("user");
	}
	
	public static Boolean isRead(){
		return Boolean.valueOf((String) map.get("read"));
	}
	
	public static Boolean isWrite(){
		return Boolean.valueOf((String) map.get("write"));
	}
	
	private static Map<String, Object> jsonToMap(Object json) {
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
