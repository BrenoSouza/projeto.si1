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

	@Column // (scale = 3)
	private String name;
	private LocalDateTime dateCreation;
	private LocalDateTime dateEdition;
	@ElementCollection
	private List<FileAndFolder> directory = new ArrayList<FileAndFolder>();

	/**
	 * Esse construtor exige apenas o nome da pasta, a data de criacao e
	 * estabelecida na criacao do objeto.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public Folder(String name) {
		checkName(name);

		this.name = name;
		dateCreation = LocalDateTime.now();
		dateEdition = LocalDateTime.now();
	}

	/**
	 * Metodo que adiciona uma nova pasta.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public void addFolder(String name) throws Exception {
		Folder temp = new Folder(name);
		if (!containsFolder(name)) {
			// throw new DuplicatedNameException();
		}
		this.directory.add(temp);
	}

	/**
	 * Metodo que remove uma pasta.
	 * 
	 * @param folder
	 *            Nome da pasta a ser removida
	 * @return true caso a pasta seja removida e false caso a pasta nao seja
	 *         encontrada.
	 */
	public boolean removeFolder(Folder folder) {
		return directory.remove(folder);
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

		Folder folderTemp = null;
		for (int i = 0; i < directory.size(); i++) {
			if (equals(directory.get(i), name, Folder.class)) {
				folderTemp = (Folder) directory.get(i);
				break;
			}
		}
		return folderTemp;
	}

	public File getFile(String name) {

		File fileTemp = null;
		for (int i = 0; i < directory.size(); i++) {
			if (equals(directory.get(i), name, File.class)) {
				fileTemp = (File) directory.get(i);
				break;
				// TODO
			}

		}
		return fileTemp;
	}

	/**
	 * Metodo que retorna todo o diretorio local.
	 * 
	 * @return ArrayList<FileAndFolder> - uma lista com todos os objetos dentro
	 *         da pasta.
	 */
	public List<FileAndFolder> getDirectory() {
		return directory;
	}

	/**
	 * Metodo que modifica o diretorio da pasta.
	 * 
	 * @param directory
	 *            ArrayList<FileAndFolder> - novo diretorio.
	 */
	public void setDiretorio(ArrayList<FileAndFolder> directory) {
		this.directory = directory;
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
		return "Name: " + getName() + "\nDate of creation: " + getDateCreation() + "\nDirectory: "
				+ getDirectory().toString() + "\n";
	}

	/*
	 * Talvez seja interessante criar uma nova classe para esses metodos de
	 * verificacao.
	 */

	private void checkName(String name) {
		if (name == null)
			throw new NullPointerException();

		if (name.equals("")) {
			// TODO throw new InvalidNameException();
		}

//		 if(containsFolder(name)) {
//		 throw new DuplicatedNameException();
//		 }

	}

	private boolean equals(FileAndFolder fileOrFolder, String fileName, Class<?> classToCompare) {
		return fileOrFolder.getName().equals(fileName) && fileOrFolder.getClass().equals(classToCompare);

	}

}
