package lexis.models;

import java.time.LocalDateTime;

public interface TrashFileAndFolder {
	
	public String getName();
	
	public LocalDateTime getDateCreation();
	
	public LocalDateTime getDateEdition();
	
	public Permission getPermission();
	
	public String getStringPath();
	
	
}
