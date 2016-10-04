
 package lexis.models;
 
 import java.io.BufferedInputStream;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.Serializable;
 import java.time.LocalDateTime;
 import java.util.TreeMap;
 
 public class DataBase implements Serializable {
 
 
 	private static final long serialVersionUID = 6013810455664863783L;
 	private static DataBase dataBase;
 	private TreeMap<String, Explorer> data;
 
 	private DataBase() {
 		data = new TreeMap<String, Explorer>();
 	}
 
  	public static synchronized DataBase getInstance() {
  		if (dataBase == null)
  			dataBase = new DataBase();
 		
 		java.io.File explorersFolder = new java.io.File(Explorer.FOLDER_EXPLORERS);
 		
 		if(!explorersFolder.exists()) 
 			explorersFolder.mkdir();
 						
  		return dataBase;
  	}
  
 	public Explorer addNewUser(User user) {
 
 		Explorer result = null;
 
 		if (user == null)
 			result = null;
 
 		result = data.get(user.getUsername());
 		if (result == null) {
 
 			result = getExplorerInFile(user.getUsername());
 
 			if (result == null) {
 
 				Explorer explorer = new Explorer(user.getUsername());
 				data.put(user.getUsername(), explorer);
 				result = data.get(user.getUsername());
 
 			}
 		}
 
 		return result;
 	}
 
 	private Explorer getExplorerInFile(String userLogin) {
 		Explorer output = null;
 
 		ObjectInputStream in = null;
 		try {
 			in = new ObjectInputStream(
 					new BufferedInputStream(
 							new FileInputStream(Explorer.FOLDER_EXPLORERS + "/" + userLogin + "." + Explorer.EXTENSION)));
 			output = (Explorer) in.readObject();
 			data.put(userLogin, output);
 		} catch (FileNotFoundException e) {
 			output = null;
 		} catch (Exception e) {
 			// TODO
 		} finally {
 
 			try {
 				if(in != null)
 					in.close();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		}
 		return output;
 	}
 
 	public Explorer getUser(String userLogin) {
 		Explorer output = data.get(userLogin);
 
 		if (output == null) {
 			output = getExplorerInFile(userLogin);
 		}
 		
 		if(output == null) {
 			output = new Explorer(userLogin);
 			data.put(userLogin, output);
 		}
 			
 
 		return output;
 	}
 
 	public Explorer removeUser(String userLogin) {
 		return data.remove(userLogin);
 	}
 
 	/**
 	 * Metodo responsavel pelo compartilhamento de arquivos com a permissao
 	 * somente para leitura do mesmo. Lembrando que o login do usuario passado
 	 * deve ser o mesmo do nome do root dos seus respectivos exploradores de
 	 * arquivo.
 	 * 
 	 * @param owner
 	 *            Usuario dono do arquivo a ser compartilhado.
 	 * @param ownersExplorer
 	 *            Explorer do dono do arquivo.
 	 * @param fileName
 	 *            Nome do arquivo.
 	 * @param fileType
 	 *            Tipo do arquivo.
 	 * @param userToShareWith
 	 *            Usuario que recebera o arquivo compartilhado.
 	 * @param explorerToShareWith
 	 *            Explorer do usuario que ira receber o arquivo.
 	 * @param log
 	 *            Data e hora na qual o compartilhamento foi feito.
 	 * @return Retorna o arquivo compartilhado caso o arquivo esteja em
 	 *         ownersExplorer.currentFolder() e null caso contrario.
 	 */
 	public SharedFileReadOnly shareFileReadOnly(User owner, Explorer ownersExplorer, String fileName, Type fileType,
 			User userToShareWith, Explorer explorerToShareWith, LocalDateTime log) {
 
 		if (!owner.getUsername().equals(ownersExplorer.getOwner())
 				|| !userToShareWith.getUsername().equals(explorerToShareWith.getOwner())) {
 			// TODO
 		}
 
 		File file = ownersExplorer.getFile(fileName, fileType);
 		SharedFileReadOnly sharedFile = null;
 
 		if (file != null) {
 
 			sharedFile = explorerToShareWith.addSharedFileReadOnly(file, owner.getUsername(), log);
 			explorerToShareWith.addUserToShare(owner.getUsername());
 		}
 
 		return sharedFile;
 	}
 
 	/**
 	 * Metodo responsavel pelo compartilhamento de arquivos com a permissao para
 	 * leitura e escrita do mesmo. Lembrando que o login do usuario passado deve
 	 * ser o mesmo do nome do root dos seus respectivos exploradores de arquivo.
 	 * 
 	 * @param owner
 	 *            Usuario dono do arquivo a ser compartilhado.
 	 * @param ownersExplorer
 	 *            Explorer do dono do arquivo.
 	 * @param fileName
 	 *            Nome do arquivo.
 	 * @param fileType
 	 *            Tipo do arquivo.
 	 * @param userToShareWith
 	 *            Usuario que recebera o arquivo compartilhado.
 	 * @param explorerToShareWith
 	 *            Explorer do usuario que ira receber o arquivo.
 	 * @param log
 	 *            Data e hora na qual o compartilhamento foi feito.
 	 * @return Retorna o arquivo compartilhado caso o arquivo esteja em
 	 *         ownersExplorer.currentFolder() e null caso contrario.
 	 */
 	public SharedFileReadAndWrite shareFileReadAndWrite(User owner, Explorer ownersExplorer, String fileName,
 			Type fileType, User userToShareWith, Explorer explorerToShareWith, LocalDateTime log) {
 
 		if (!owner.getUsername().equals(ownersExplorer.getOwner())
 				|| !userToShareWith.getUsername().equals(explorerToShareWith.getOwner())) {
 			// TODO
 		}
 
 		File file = ownersExplorer.getFile(fileName, fileType);
 		SharedFileReadAndWrite sharedFile = null;
 
 		if (file != null) {
 
 			sharedFile = explorerToShareWith.addSharedFileReadAndWrite(file, owner.getUsername(), log);
 			// ownersExplorer.addUserToShare(userToShareWith.getUsername());
 			explorerToShareWith.addUserToShare(owner.getUsername());
 		}
 
 		return sharedFile;
 	}
 
 }