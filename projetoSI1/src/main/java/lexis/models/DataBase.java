package lexis.models;

import java.util.TreeMap;

public class DataBase {
	
	private static DataBase dataBase;
	
	private TreeMap<String, Explorer> data;
	
	
	private DataBase() {
		data = new TreeMap<String, Explorer>();
	}
	
	
	public static synchronized DataBase getInstance() {
		if(dataBase == null)
			dataBase = new DataBase();
		return dataBase;
	}
	
	
	public Explorer addNewUser(User user) {
		
		Explorer result = null;
		
		if(user == null) 
			result = null;
		
		result = data.get(user.getUsername());
		 if(result == null) {
			
			Explorer explorer = new Explorer(user.getUsername());
			data.put(user.getUsername(), explorer);
			result = data.get(user.getUsername());
		}
		
		return result;
	}
	
	public Explorer getUser(String userLogin) {
		Explorer output = data.get(userLogin);
		
		return output;
	}
	
	public Explorer removeUser(String userLogin) {		
		return data.remove(userLogin);
	}
	
	
	/**
	 * Metodo responsavel pelo compartilhamento de arquivos 
	 * com a permissao somente para leitura do mesmo. Lembrando que 
	 * o login do usuario passado deve ser o mesmo do nome do root dos 
	 * seus respectivos exploradores de arquivo.
	 * @param owner Usuario dono do arquivo a ser compartilhado.
	 * @param ownersExplorer Explorer do dono do arquivo.
	 * @param fileName Nome do arquivo.
	 * @param fileType Tipo do arquivo.
	 * @param userToShareWith Usuario que recebera o arquivo compartilhado.
	 * @param explorerToShareWith Explorer do usuario que ira receber 
	 * o arquivo.
	 * @return Retorna o arquivo compartilhado caso o arquivo esteja em 
	 * ownersExplorer.currentFolder() e null caso contrario.
	 */
	public SharedFileReadOnly shareFileReadOnly(User owner, Explorer ownersExplorer,  
			String fileName, Type fileType, User userToShareWith, Explorer explorerToShareWith) {
		
		if(!owner.getUsername().equals(ownersExplorer.getOwner()) || 
				!userToShareWith.getUsername().equals(explorerToShareWith.getOwner())) {
			//TODO
		}
		
		File file = ownersExplorer.getFile(fileName, fileType);
		SharedFileReadOnly sharedFile = null;
		
		if(file != null) {
			
			sharedFile = explorerToShareWith.addSharedFileReadOnly(file, owner.getUsername());
			ownersExplorer.addUserToShare(userToShareWith.getUsername());
		}
		
		return sharedFile;
	}
	
	/**
	 * Metodo responsavel pelo compartilhamento de arquivos 
	 * com a permissao para leitura e escrita do mesmo. Lembrando que 
	 * o login do usuario passado deve ser o mesmo do nome do root dos 
	 * seus respectivos exploradores de arquivo.
	 * @param owner Usuario dono do arquivo a ser compartilhado.
	 * @param ownersExplorer Explorer do dono do arquivo.
	 * @param fileName Nome do arquivo.
	 * @param fileType Tipo do arquivo.
	 * @param userToShareWith Usuario que recebera o arquivo compartilhado.
	 * @param explorerToShareWith Explorer do usuario que ira receber 
	 * o arquivo.
	 * @return Retorna o arquivo compartilhado caso o arquivo esteja em 
	 * ownersExplorer.currentFolder() e null caso contrario.
	 */
	public SharedFileReadAndWrite shareFileReadAndWrite(User owner, Explorer ownersExplorer, 
			String fileName, Type fileType, User userToShareWith, Explorer explorerToShareWith) {
		
		if(!owner.getUsername().equals(ownersExplorer.getOwner()) || 
				!userToShareWith.getUsername().equals(explorerToShareWith.getOwner())) {
			//TODO
		}
		
		File file = ownersExplorer.getFile(fileName, fileType);
		SharedFileReadAndWrite sharedFile = null;
		
		if(file != null) {
			
			sharedFile = explorerToShareWith.addSharedFileReadAndWrite(file, owner.getUsername());
			ownersExplorer.addUserToShare(userToShareWith.getUsername());
		}
		
		return sharedFile;		
	}
	

}
