package lexis.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {
	TXT, MD;

	@JsonCreator
	public static Type create(String value) {
		
		if(value == null)
			throw new IllegalArgumentException();
		
		for(Type v : values()) {
			if(v.name().equals(value.toUpperCase())) {
				return v;
			}
		}
		
		throw new IllegalArgumentException();
	}

}
