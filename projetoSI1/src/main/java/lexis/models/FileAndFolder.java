package lexis.models;

import java.util.Date;
import javax.persistence.Embeddable;

/**
 * Interface que dita as regras que todo arquivo e pasta tem que ter
 * @author raimundoheitor
 *
 */
@Embeddable
public interface FileAndFolder {
	
	/**
	 * @return o nome do objeto
	 */
	String getName();

	/**
	 * atualiza o nome
	 * @param name String - novo nome
	 */
	void setName(String name);

	/**
	 * @return Date - a data de criação do objeto
	 */
	Date getDateCreation();

	/**
	 * @return Date - a data de ediçao do objeto
	 */
	Date getDateEdition();

	/**
	 * atualizar a data de criação
	 * @param date Date - nova data
	 */
	void setDateCreation(Date date);

	/**
	 * atualizar a data de edição
	 * @param date Date - nova data
	 */
	void setDateEdition(Date date);

}
