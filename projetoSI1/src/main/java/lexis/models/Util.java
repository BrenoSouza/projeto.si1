package lexis.models;

public class Util {
	
	
	
	public static boolean isAValidName(String name) {
		boolean result = true;
		if(name == null)
			result = false;
		else if(name.equals(""))
			result = false;
		
		return result;

	}
}
