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

/**
 * Classe Util com as conversões do Json para diversos objetos
 * @author raimundoheitor
 *
 */
public class JsonUtil {
	private static Map<String, Object> map;

	/**
	 * metodo que salva o obejto Json passado
	 * @param json o Obejct que será manipulado para extração outros objetos
	 */
	public static void json(Object json){
		map = jsonToMap(json);
	}
	
	/**
	 * metodo que obtem o nome de dentro do json
	 * @return o nome encontrado
	 */
	public static String getName(){
		return (String) map.get("name");
	}
	
	/**
	 * metodo que obtem a Permissão de dentro do Json
	 * @return a Permission econtrada
	 */
	public static Permission getPermission(){
		return Permission.create((String) map.get("Permission"));
	}
	
	/**
	 * metodo que obtem a data de criação de dentro do Json
	 * @return a data de criação econtrada
	 */
	public static LocalDateTime GetDateCreation(){
		String date = (String) map.get("dateCreation");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return  LocalDateTime.parse(date, formatter);
	}
	
	/**
	 * metodo que obtem o Tipo de dentro do json
	 * @return o Type encontrado
	 */
	public static Type getType(){
		return Type.valueOf((String) map.get("type"));
	}
	
	/**
	 * metodo que obtem o nome atual de dentro do json
	 * @return o nome atual encontrado
	 */
	public static String getOldName(){
		return (String) map.get("oldName");
	}
	
	/**
	 * metodo que obtem o novo nome de dentro do json
	 * @return o novo nome encontrado
	 */
	public static String getNewName(){
		return (String) map.get("newName");
	}
	
	/**
	 * metodo que obtem o codigo fonte  de dentro do json
	 * @return o codigo fonte encontrado
	 */
	public static String getData(){
		return (String) map.get("data");
	}
	
	/**
	 * metodo que obtem o Tipo velho de dentro do json
	 * @return o Type velho encontrado
	 */
	public static Type getOldType(){
		return Type.valueOf((String) map.get("oldType"));
	}
	
	/**
	 * metodo que obtem o novo Tipo de dentro do json
	 * @return o novo Type encontrado
	 */
	public static Type getNewType(){
		return Type.valueOf((String) map.get("newType"));
	}
	
	/**
	 * metodo que obtem o nome do usuario de dentro do Json
	 * @return o nome encontrado
	 */
	public static String getUserName(){
		return (String) map.get("user");
	}
	
	/**
	 * metodo que obtem o boolen read de dentro do json
	 * @return o boolean encontrado
	 */
	public static Boolean isRead(){
		return Boolean.valueOf((String) map.get("read"));
	}
	
	/**
	 * metodo que obtem o boolean write de dentro do json
	 * @return o boolean encontrado
	 */
	public static Boolean isWrite(){
		return Boolean.valueOf((String) map.get("write"));
	}
	
	/**
	 * metodo que converte Json Object pata map
	 * @param json o Json Object
	 * @return o map conrrespondente
	 */
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

	/**
	 * metodo que obtem a posição de dentro do json
	 * @return a posição int  encontrada
	 */
	public static int getPosition() {
		return Integer.parseInt((String) map.get("position"));
	}

}
