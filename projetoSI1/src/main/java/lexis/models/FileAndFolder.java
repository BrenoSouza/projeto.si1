package lexis.models;

import java.util.Date;
import javax.persistence.Embeddable;

/**
 * Interface que dita as regras que todo arquivo e pasta tem que ter.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
@Embeddable
public interface FileAndFolder {

	/**
	 * @return O nome do objeto.
	 */
	String getName();

	/**
	 * Atualiza o nome.
	 * 
	 * @param name
	 *            String - novo nome.
	 */
	void setName(String name);

	/**
	 * @return Date - a data de criação do objeto.
	 */
	Date getDateCreation();

	/**
	 * @return Date - a data de ediçao do objeto.
	 */
	Date getDateEdition();

	/**
	 * Atualiza a data de criacao.
	 * 
	 * @param date
	 *            Date - nova data.
	 */
	void setDateCreation(Date date);

	/**
	 * Atualiza a data de edicao.
	 * 
	 * @param date
	 *            Date - nova data.
	 */
	void setDateEdition(Date date);

}
