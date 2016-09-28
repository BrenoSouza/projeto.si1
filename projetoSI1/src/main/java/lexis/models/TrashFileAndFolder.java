package lexis.models;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

public interface TrashFileAndFolder {
	
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
