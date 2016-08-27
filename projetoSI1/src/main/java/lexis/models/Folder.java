package lexis.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;


/**
 * Classe responsavel pelo objeto do tipo pasta.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
public class Folder implements FileAndFolder {
	
	public static final String UNAMED_FOLDER = "semTitulo";

	@Column // (scale = 3)
	private String name;
	private LocalDateTime dateCreation;
	private LocalDateTime dateEdition;
	private Permission permission;
	
	@ElementCollection
	private List<Folder> folderDirectory;
	private List<File> fileDirectory;

	/**
	 * Esse construtor exige apenas o nome da pasta, a data de criacao e
	 * estabelecida na criacao do objeto.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public Folder(String name, Permission permission) {
		
		folderDirectory = new ArrayList<Folder>();
	
		if(!Util.isAValidName(name))
			name = getANewName(UNAMED_FOLDER);
		
		if(permission == null)
			permission = Permission.PRIVATE;

		this.name = name;
		dateCreation = LocalDateTime.now();
		dateEdition = LocalDateTime.now();
		this.permission = permission;
		fileDirectory = new ArrayList<File>();

	}
	
	
	/**
	 * Metodo que adiciona uma nova pasta, caso 
	 * o nome da pasta seja invalido a pasta tera 
	 * um nome default. Caso haja uma pasta com o 
	 * mesmo nome passado no parametro sera criado um 
	 * novo nome para ela tambem a partir do nome passado 
	 * no parametro.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public void addFolder(String name, Permission permission) {
		
		if(permission == null)
			permission = Permission.PRIVATE;
		
		if(!Util.isAValidName(name))
			name = UNAMED_FOLDER;
		
		Folder temp;
		if(containsFolder(name)) 
			temp = new Folder(getANewName(name), permission);
		 else
			temp = new Folder(name, permission);

		this.folderDirectory.add(temp);
	}
	

	/**
	 * Metodo que remove uma pasta.
	 * 
	 * @param folder
	 *            Nome da pasta a ser removida
	 * @return true caso a pasta seja removida e false caso a pasta nao seja
	 *         encontrada.
	 */
	public boolean removeFolder(String folderName) {
		Folder folder = new Folder(folderName, Permission.PRIVATE);
		return folderDirectory.remove(folder);
	}
	
	public boolean removeFile(String fileName, Type type) {
		File file = new File(fileName, type, Permission.PRIVATE);
		return fileDirectory.remove(file);
	}

	/**
	 * Metodo que retorna a pasta solicitada. Caso a pasta nao exista o metodo
	 * retorna null.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 * @return Folder - a pasta solicitada.
	 */
	public Folder getFolder(String name) {

		Folder folderTemp = new Folder(name, permission);
		for (Folder folder : folderDirectory) {
			if (folder.equals(folderTemp)) 
				return folder;
			
		}
		return null;
	}
	
	/**
	 * Procura por uma pasta com o nome passado no 
	 * parametro, caso a pasta nao exista, cria uma 
	 * nova pasta vazia e privada com o nome passado. 
	 * @param name Nome da pasta. 
	 * @return retorna a pasta encontrada ou uma pasta vazia.
	 */
	public Folder getOrCreateFolder(String name) {
		
		Folder folderTemp = new Folder(name, Permission.PRIVATE);
		for(Folder folder : folderDirectory) {
			if(folder.equals(folderTemp))
				return folder;
		}
		
		folderDirectory.add(folderTemp);
		
		return folderTemp;
	}
	
	
	/**
	 * Metodo responsavel por recuperar um arquivo, 
	 * caso o arquivo nao exista ele criara um novo 
	 * arquivo vazio com permissao privada.
	 * 
	 * @param name Nome do arquivo.
	 * @param type Tipo do arquivo.
	 * @return Retorna o arquivo procurado caso ele exista 
	 * ou um arquivo vazio caso contrario.
	 * @throws Exception NullPointerException caso o nome 
	 * ou o tipo do arquivo sejam null.
	 */
	public File getOrCreateFile(String name, Type type) {
		
		File fileTemp = new File(name, type, Permission.PRIVATE);
		for (File file : fileDirectory) {
			if (file.equals(fileTemp)) 
				return file;
		}
		
		fileDirectory.add(fileTemp);
		return fileTemp;
	}
	
	/**
	 * Procura pelo arquivo dentro da pasta que possua 
	 * o mesmo nome e o mesmo tipo.
	 * @param name Nome do arquivo.
	 * @param type Tipo do arquivo.
	 * @return Retorna o arquivo procurado caso ele exista 
	 * e null caso contrario
	 * @throws Exception
	 */
	public File getFile(String name, Type type) {
		
		File fileTemp = new File(name, type, Permission.PRIVATE);
		for (File file : fileDirectory) {
			if (file.equals(fileTemp)) 
				return file;
			

		}
		return null;
	}
	
	
	/**
	 * Procura por arquivos apenas pelo nome deles.
	 * 
	 * @param name Nome a ser procurado.
	 * @return Retorna uma lista de arquivos contendo 
	 * todos os arquivos que possuem aquele nome. O 
	 * tamanho maximo da lista e a quantidade de tipos de 
	 * arquivos. Caso o nome do arquivo seja null o metodo 
	 * retorna uma lista vazia.
	 */
	public List<File> getFilesOnlyByName(String name) throws Exception {
		
		List<File> files = new ArrayList<File>();
		
		if(name == null)
			return files;
		
		for (File file : fileDirectory) {
			if(file.getName().equals(name))
				files.add(file);
		}
		
		return files;
			
	}
	

	/**
	 * Recupera o diretorio de pastas
	 * 
	 * @return List<Folder>  
	 */
	public List<Folder> getFolderDirectory() {
		return folderDirectory;
	}
	
	
	/**
	 * Recupera o diretorio de arquivos.
	 * @return List<File> 
	 */
	public List<File> getFileDirectory() {
		return fileDirectory;
	}

	@Override
	public Permission getPermission() {
		return permission;
	}
	
	@Override
	public void setPermission(Permission permission) {
		this.permission = permission;	
	}
	
	/**
	 * Estabelece um novo diretorio de pastas.
	 * 
	 * @param directory
	 *            ArrayList<FileAndFolder> - novo diretorio.
	 */
	public void setFolderDirectory(List<Folder> directory) {
		this.folderDirectory = directory;
	}
	
	/**
	 * Estabelece um novo diretorio de arquivos.
	 * @param directory Novo diretorio.
	 */
	public void setFileDirectory(List<File> directory) {
		this.fileDirectory = directory;
	}

	/**
	 * Verifica se o arquivo ou pasta esta no diretorio atual.
	 * 
	 * @param name
	 *            Nome do arquivo.
	 * @return Retorna True caso o arquivo/pasta esteja dentro do diretorio.
	 */
	public boolean containsFolder(String name) {
		return getFolder(name) != null;
	}

	@Override
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	@Override
	public void setDateCreation(LocalDateTime date) throws Exception {
		checkDate(date);
		dateCreation = date;
	}

	@Override
	public LocalDateTime getDateEdition() {
		return dateEdition;
	}

	@Override
	public void setDateEdition(LocalDateTime date) throws Exception {
		checkDate(date);
		this.dateEdition = date;
	}

	private void checkDate(LocalDateTime date) throws Exception {
		if (date == null)
			throw new NullPointerException();

	}

	@Override
	public String getName() {
		return name;
	}
	

	@Override
	public void setName(String name) {
		
		if(!Util.isAValidName(name))
			name = getANewName(UNAMED_FOLDER);
		
		this.name = name;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Folder) {
			Folder folder = (Folder) objeto;
			if (this.getName().equals(folder.getName())) {
				return true; // sao iguails.
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Name: " + getName() + "\nDate of creation: " + getDateCreation() + "\nFolders: "
				+ getFolderDirectory().toString() + "\nFiles: " + getFileDirectory() + "\n";
	}


	public String getANewName(String name) {
		Integer countSameNameFolders = 0;
		String auxName = name.substring(0);
		
		while(containsFolder(auxName)){
			countSameNameFolders++;
			auxName = name.substring(0) + " (" + countSameNameFolders.toString() + ")";
			
		}
		
		return auxName;
	}
	
}
