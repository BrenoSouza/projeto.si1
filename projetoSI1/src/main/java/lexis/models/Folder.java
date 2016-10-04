package lexis.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel pelo objeto do tipo pasta.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
public class Folder implements FileAndFolder {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4989997726749486926L;

	public static final String SEP = "/";
	public static final String UNAMED_FOLDER = "Sem titulo";
		
	private String name;
	
	private LocalDateTime dateCreation;
	
	private LocalDateTime dateEdition;
	
	private Permission permission;
	
	private boolean inTrash;

	private List<Folder> folderDirectory;
	
	private List<File> fileDirectory;
	
	private List<String> path;

	public Folder(String name, Permission permission, List<String> path) {
		this(name, permission, path, LocalDateTime.now());
	}

	/**
	 * Esse construtor exige apenas o nome da pasta, a data de criacao e
	 * estabelecida na criacao do objeto.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public Folder(String name, Permission permission, List<String> path, LocalDateTime dateCreation) {

		if (path == null || dateCreation == null)
			throw new NullPointerException();

		folderDirectory = new ArrayList<Folder>();

		if (!ModelsUtil.isAValidName(name))
			name = getNewFoldersName(UNAMED_FOLDER);

		if (permission == null)
			permission = Permission.PRIVATE;

		this.name = name;
		this.dateCreation = dateCreation;
		this.dateEdition = dateCreation;
		this.permission = permission;
		this.path = path;
		this.fileDirectory = new ArrayList<File>();
		this.inTrash = false;

	}

	public void addFolder(String name, Permission permission, LocalDateTime dateCreation) {

		if (permission == null)
			permission = Permission.PRIVATE;

		if (!ModelsUtil.isAValidName(name))
			name = UNAMED_FOLDER;

		Folder temp;
		List<String> newPath = getPathWithThisFolder();

		if (containsFolder(name))
			temp = new Folder(getNewFoldersName(name), permission, newPath, dateCreation);
		else
			temp = new Folder(name, permission, newPath, dateCreation);

		this.folderDirectory.add(temp);
	}

	/**
	 * Metodo que adiciona uma nova pasta, caso o nome da pasta seja invalido a
	 * pasta tera um nome default. Caso haja uma pasta com o mesmo nome passado
	 * no parametro sera criado um novo nome para ela tambem a partir do nome
	 * passado no parametro.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public void addFolder(String name, Permission permission) {

		if (permission == null)
			permission = Permission.PRIVATE;

		if (!ModelsUtil.isAValidName(name))
			name = UNAMED_FOLDER;

		Folder temp;
		List<String> newPath = getPathWithThisFolder();

		if (containsFolder(name))
			temp = new Folder(getNewFoldersName(name), permission, newPath, dateCreation);
		else
			temp = new Folder(name, permission, newPath, dateCreation);

		this.folderDirectory.add(temp);
	}

	public void addFile(String name, Type type, Permission permission, LocalDateTime dateCreation) {

		if (permission == null)
			permission = Permission.PRIVATE;

		if (dateCreation == null)
			throw new NullPointerException();

		if (!ModelsUtil.isAValidName(name))
			name = File.UNAMED_FILE;

		List<String> newPath = getPathWithThisFolder();
		File fileTemp;
		if (getFile(name, type) != null) {
			fileTemp = new File(getNewFilesName(name, type), type, permission, newPath, dateCreation);
			this.fileDirectory.add(fileTemp);

		} else {

			fileTemp = new File(name, type, permission, newPath, dateCreation);
			this.fileDirectory.add(fileTemp);
		}

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

		LocalDateTime aux = LocalDateTime.now();

		Folder folder = new Folder(folderName, Permission.PRIVATE, getPathWithThisFolder(), aux);
		return folderDirectory.remove(folder);
	}

	public boolean removeFile(String fileName, Type type) {

		LocalDateTime aux = LocalDateTime.now();

		File file = new File(fileName, type, Permission.PRIVATE, getPathWithThisFolder(), aux);
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

		LocalDateTime aux = LocalDateTime.now();
		Folder folderTemp = new Folder(name, permission, getPathWithThisFolder(), aux);
		for (Folder folder : folderDirectory) {
			if (folder.equals(folderTemp))
				return folder;

		}
		return null;
	}

	public List<FileAndFolder> find(String name) {
		List<FileAndFolder> results = new ArrayList<FileAndFolder>();

		if (name != null) {
			auxFind(name, results, this);
		}

		return results;
	}

	/**
	 * Procura por uma pasta com o nome passado no parametro, caso a pasta nao
	 * exista, cria uma nova pasta vazia, com a data de criacao do momento em
	 * que o metodo foi criado e privada com o nome passado.
	 * 
	 * @param name
	 *            Nome da pasta.
	 * @return retorna a pasta encontrada ou uma pasta vazia.
	 */
	public Folder getOrCreateFolder(String name) {

		LocalDateTime aux = LocalDateTime.now();
		Folder folderTemp = new Folder(name, Permission.PRIVATE, getPathWithThisFolder(), aux);
		for (Folder folder : folderDirectory) {
			if (folder.equals(folderTemp))
				return folder;
		}

		folderDirectory.add(folderTemp);

		return folderTemp;
	}

	/**
	 * Metodo responsavel por recuperar um arquivo, caso o arquivo nao exista
	 * ele criara um novo arquivo vazio com permissao privada.
	 * 
	 * @param name
	 *            Nome do arquivo.
	 * @param type
	 *            Tipo do arquivo.
	 * @return Retorna o arquivo procurado caso ele exista ou um arquivo vazio
	 *         caso contrario.
	 * @throws Exception
	 *             NullPointerException caso o nome ou o tipo do arquivo sejam
	 *             null.
	 */
	public File getOrCreateFile(String name, Type type) {

		File fileTemp = new File(name, type, Permission.PRIVATE, getPathWithThisFolder());
		for (File file : fileDirectory) {
			if (file.equals(fileTemp))
				return file;
		}

		fileDirectory.add(fileTemp);
		return fileTemp;
	}

	/**
	 * Procura pelo arquivo dentro da pasta que possua o mesmo nome e o mesmo
	 * tipo.
	 * 
	 * @param name
	 *            Nome do arquivo.
	 * @param type
	 *            Tipo do arquivo.
	 * @return Retorna o arquivo procurado caso ele exista e null caso contrario
	 * @throws Exception
	 */
	public File getFile(String name, Type type) {

		File fileTemp = new File(name, type, Permission.PRIVATE, getPathWithThisFolder(), LocalDateTime.now());
		for (File file : fileDirectory) {
			if (file.equals(fileTemp))
				return file;

		}
		return null;
	}
	
	@Override
	public boolean isInTrash() {
		return inTrash;
	}
	
	@Override
	public void putInTrash() {
		inTrash = true;
	}

	/**
	 * Procura por arquivos apenas pelo nome deles.
	 * 
	 * @param name
	 *            Nome a ser procurado.
	 * @return Retorna uma lista de arquivos contendo todos os arquivos que
	 *         possuem aquele nome. O tamanho maximo da lista e a quantidade de
	 *         tipos de arquivos. Caso o nome do arquivo seja null o metodo
	 *         retorna uma lista vazia.
	 */
	public List<File> getFilesOnlyByName(String name) throws Exception {

		List<File> files = new ArrayList<File>();

		if (name == null)
			return files;

		for (File file : fileDirectory) {
			if (file.getName().equals(name))
				files.add(file);
		}

		return files;

	}

	@Override
	public String getStringPath() {
		String stringPath = "";

		for (String folder : path) {
			stringPath += (folder + SEP);
		}

		return stringPath;
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
	 * 
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
	 * 
	 * @param directory
	 *            Novo diretorio.
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

	private boolean containsFile(String name, Type type) {
		return getFile(name, type) != null;
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
	public List<String> getPath() {
		return path;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {

		if (!ModelsUtil.isAValidName(name))
			name = getNewFoldersName(UNAMED_FOLDER);

		this.name = name;
	}

	@Override
	public void setPath(List<String> path) {
		if (path == null)
			throw new NullPointerException();

		this.path = path;
	}

	@Override
	public void setCellOfPath(int index, String newValue) {
		if (newValue == null)
			throw new NullPointerException();

		for (Folder child : folderDirectory)
			child.setCellOfPath(index, newValue);

		for (File child : fileDirectory)
			child.setCellOfPath(index, newValue);

		if (0 <= index && index < path.size())
			path.set(index, newValue);

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (!(obj instanceof Folder))
			return false;

		Folder otherFolder = (Folder) obj;

		return this.getName().equals(otherFolder.getName()) && this.path.equals(otherFolder.getPath());
	}

	@Override
	public String toString() {
		return  "Name: " + getName() +
				"\nDate of creation: " + getDateCreation() + 
				"\n Date of edition: " + getDateEdition() +
				"\n Permission: " + getPermission()+
				"\nFiles: " + getFileDirectory() + "\n";
	}

	public String getNewFoldersName(String name) {
		Integer countSameNameFolders = 0;
		String auxName = name.substring(0);

		while (containsFolder(auxName)) {
			countSameNameFolders++;
			auxName = name.substring(0) + " (" + countSameNameFolders.toString() + ")";

		}

		return auxName;
	}

	public String getNewFilesName(String name, Type type) {
		Integer countSameNameFolders = 0;
		String auxName = name.substring(0);

		while (containsFile(auxName, type)) {
			countSameNameFolders++;
			auxName = name.substring(0) + " (" + countSameNameFolders.toString() + ")";

		}

		return auxName;
	}

	public String getType() {
		return "folder";
	}

	public List<String> getPathWithThisFolder() {
		List<String> newPath = new ArrayList<String>(path.subList(0, path.size()));
		newPath.add(this.getName());
		return newPath;
	}

	public void setInTrash(boolean inTrash) {
		this.inTrash = inTrash;
	}

	private static void auxFind(String name, List<FileAndFolder> results, Folder folder) {

		for (Folder f : folder.getFolderDirectory()) {
			if (f.getName().toLowerCase().equals(name.toLowerCase()))
				results.add(f);

			if (!f.getFolderDirectory().isEmpty())
				auxFind(name, results, f);
		}

		for (File file : folder.getFileDirectory()) {
			if (file.getName().toLowerCase().equals(name.toLowerCase()))
				results.add(file);
		}
	}

}
