package lexis.models;

import java.time.LocalDateTime;
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
	public LocalDateTime getDateCreation();
	
	
	/**
	 * Estabelece uma data de criacao. Esse metodo so 
	 * pode ser usado uma vez para cada pasta ou arquivo.
	 * @param date Data da criacao
	 * @throws Exception Lanca excecao caso a data nao tenha 
	 * sido inicializada.
	 */
	public void setDateCreation(LocalDateTime date) throws Exception;
	
	/**
	 * @return Date - a data de ediçao do objeto.
	 */
	public LocalDateTime getDateEdition();
	
	/**
	 * Atualiza a data de edicao.
	 * 
	 * @param date
	 *            Date - nova data.
	 * @throws Exception Lanca NullPointerException caso date 
	 * nao tenha sido inicializada.
	 */
	public void setDateEdition(LocalDateTime date) throws Exception;
	
	
	public Permission getPermission();
	
	public void setPermission(Permission permission);
		
	
	
	
	
	
}
