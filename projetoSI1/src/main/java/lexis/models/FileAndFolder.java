package lexis.models;

import java.util.Date;
import javax.persistence.Embeddable;

/**
 * Interface que dita as regras que todo arquivo e pasta tem que ter.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
@Embeddable
public interface FileAndFolder {

	/**
	 * @return O nome do objeto.
	 */
	public String getName();

	/**
	 * Atualiza o nome.
	 * 
	 * @param name
	 *            String - novo nome.
	 */
	public void setName(String name);

	/**
	 * @return Date - a data de criação do objeto.
	 */
	public Date getDateCreation();

	/**
	 * @return Date - a data de ediçao do objeto.
	 */
	public Date getDateEdition();
	
	/**
	 * Atualiza a data de edicao.
	 * 
	 * @param date
	 *            Date - nova data.
	 */
	public void setDateEdition(Date date);
	
	
}
