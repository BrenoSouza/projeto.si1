package lexis.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface TrashFileAndFolder extends Serializable {
	
	/**
	 * @return O nome do objeto.
	 */
	public String getName();
	
	/**
	 * @return Date - a data de criação do objeto.
	 */
	public LocalDateTime getDateCreation();
	
	/**
	 * @return Date - a data de ediçao do objeto.
	 */
	public LocalDateTime getDateEdition();
	
	public Permission getPermission();
	
	public String getStringPath();
	
	public boolean isInTrash();
	
	
	
	
}
