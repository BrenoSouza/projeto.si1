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
	
	
	public boolean addNewUser(User user) {
		
		boolean result = true;
		
		if(user == null) {
			result = false;
		
		} else if(data.containsKey(user.getUsername())) {
			result = false;
		
		} else {
			
			Explorer explorer = new Explorer(user);
			data.put(explorer.getUser().getUsername(), explorer);
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
