package lexis.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	private Date dateCreation;
	private Date dateEdition;
	@ElementCollection
	private ArrayList<FileAndFolder> directory = new ArrayList<FileAndFolder>();

	/**
	 * Esse construtor exige apenas o nome da 
	 * pasta, a data de criacao e estabelecida 
	 * na criacao do objeto.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public Folder(String name) throws RuntimeException {
		checkName(name);
		
		
		this.name = name;
		dateCreation = getCurrentDate();
		dateEdition = getCurrentDate();
	}
	
	/**
	 * Metodo que adiciona uma nova pasta.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 */
	public void addFolder(String name) throws RuntimeException {
		Folder temp = new Folder(name);
		if (!containsFolder(name)) {
			//throw new DuplicatedNameException();
		}
		this.directory.add(temp);
	}

	/**
	 * Metodo que remove uma pasta.
	 * 
	 * @param folder Nome da pasta a ser removida
	 * @return true caso a pasta seja removida e false 
	 * caso a pasta nao seja encontrada.
	 */
	public boolean removeFolder(Folder folder) {
		return directory.remove(folder);
	}

	/**
	 * Metodo que retorna a pasta solicitada. Caso 
	 * a pasta nao exista o metodo retorna null.
	 * 
	 * @param name
	 *            String - nome da pasta.
	 * @return Folder - a pasta solicitada.
	 */
	public Folder getFolder(String name) {
		
		Folder folderTemp = null;
		for (int i = 0; i < directory.size(); i++) {
			if (directory.get(i).getName().equals(name)) {
				folderTemp = (Folder) directory.get(i);
				break;
			}
		}
		return folderTemp;
	}
	
	public File getFile(String name) {
		
		File fileTemp = null;
		for(int i = 0; i < directory.size(); i++) {
			if(directory.get(i).getName().equals(name)) {
				fileTemp = (File) directory.get(i);
				break;
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
	public ArrayList<FileAndFolder> getDirectory() {
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
	 * Verifica se o arquivo ou pasta esta no diretorio 
	 * atual.
	 * @param name Nome do arquivo.
	 * @return Retorna True caso o arquivo/pasta esteja 
	 * dentro do diretorio.
	 */
	public boolean containsFolder(String name) {
		return getFolder(name) != null;
	}
	
	@Override
	public Date getDateCreation() {
		return dateCreation;
	}
	
	@Override
	public void setDateCreation(Date date) throws Exception {
		checkDate(date);
		dateCreation = date;
	}

	@Override
	public Date getDateEdition() {
		return dateEdition;
	}

	@Override
	public void setDateEdition(Date date) throws Exception {
		checkDate(date);
		this.dateEdition = date;
	}
	
	private void checkDate(Date date) throws Exception {
		if(date == null)
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

	private Date getCurrentDate() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
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
	 * Talvez seja interessante criar uma nova classe para esses metodos 
	 * de verificacao.
	 */
	
	private void checkName(String name) throws RuntimeException {
		if(name == null)
			throw new NullPointerException();
		
		if(name.equals("")) {
			//TODO throw new InvalidNameException();
		}
		
//		if(!parent.contains(name)) {
//			//TODO throw new DuplicatedNameException();
//		}
		
	}
	

}
