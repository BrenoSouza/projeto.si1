package lexis.models;

import java.util.TreeMap;

public class DataBase {
	
	private static DataBase dataBase;
	
	private TreeMap<String, Explorer> data;
	
	
	private DataBase() {
		data = new TreeMap<String, Explorer>();
	}
	
	
	public static synchronized DataBase getInstance() {
		if(dataBase == null)
			dataBase = new DataBase();
		return dataBase;
	}
	
	
	public Explorer addNewUser(User user) {
		
		Explorer result = null;
		
		if(user == null) 
			result = null;
		
		result = data.get(user.getUsername());
		
		 if(result == null) {
			
			Explorer explorer = new Explorer(user.getUsername());
			data.put(user.getUsername(), explorer);
		}
		
		return result;
	}
	
	public Explorer getUser(String userLogin) {
		Explorer output = data.get(userLogin);
		
		return output;
	}
	
	public Explorer removeUser(String userLogin) {		
		return data.remove(userLogin);
	}
	
	

}
