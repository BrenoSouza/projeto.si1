package lexis.models;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum Permission {
	PRIVATE, PUBLIC;
	
	
	
	
	@JsonCreator
	public static Permission create(String value) {
		
		if(value == null)
			throw new IllegalArgumentException();
		
		for(Permission v : values()) {
			if(v.name().equals(value.toUpperCase())) {
				return v;
			}
		}
		
		throw new IllegalArgumentException();
	}

}
