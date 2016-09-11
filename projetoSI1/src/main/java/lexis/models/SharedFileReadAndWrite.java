package lexis.models;

import java.time.LocalDateTime;

public final class SharedFileReadAndWrite extends SharedFileReadOnly {
	
	public SharedFileReadAndWrite (File file, String owner) {
		super(file, owner);
		
	}
	
	public void setData(String data, LocalDateTime dateEdition) {
		getFile().setData(data, dateEdition);
	}
	
}
