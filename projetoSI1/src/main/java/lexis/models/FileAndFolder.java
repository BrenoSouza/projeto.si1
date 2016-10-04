package lexis.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface que dita as regras que todo arquivo e pasta tem que ter.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
public interface FileAndFolder extends TrashFileAndFolder {

	/**
	 * Atualiza o nome.
	 * 
	 * @param name
	 *            String - novo nome.
	 */
	public void setName(String name);
	
	/**
	 * Estabelece uma data de criacao. Esse metodo so 
	 * pode ser usado uma vez para cada pasta ou arquivo.
	 * @param date Data da criacao
	 * @throws Exception Lanca excecao caso a data nao tenha 
	 * sido inicializada.
	 */
	public void setDateCreation(LocalDateTime date) throws Exception;
	
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
	
	public List<String> getPath();
	
	public void setPath(List<String> path);
	
	public void setCellOfPath(int index, String newValue);
	
	public String getStringPath();
	
	public void putInTrash();
		
	
	
	
	
}
