package lexis.models;

import java.util.Date;

import javax.persistence.Embeddable;


@Embeddable
public interface FileAndFolder{
	
	String getName();
	
	void setName(String name);
	
	Date getDateCreation();
	
	Date getDateEdition();
	
	void setDateCreation(Date date);
	
	void setDateEdition(Date date);

}
