package lexis.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

/**
 * classe responsavel pelo objeto do tipo pasta
 * @author raimundoheitor
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
	 * construdor que exige um nome para a pasta
	 * e crias as datas automaticamente
	 * @param name String - nome da pasta
	 */
	public Folder(String name) {
		this.name = name;
		dateCreation = getDateCurrent();
		dateEdition = getDateCurrent();
	}

	/**
	 * metodo que adiciona uma nova pasta 
	 * @param name String - nome da pasta
	 */
	public void addFolder(String name) {
		Folder temp = new Folder(name);
		if (getFolder(name) == null) {
			this.directory.add(temp);
		}
	}

	/**
	 * metodo que remove uma pasta
	 * @param folder Folder - pasta a ser removida
	 */
	public void removeFolder(Folder folder) {
		this.directory.remove(folder);
	}

	/**
	 * metodo que retorna a pasta solicitada
	 * @param name String - nome da pasta
	 * @return Folder - a pasta solicitada
	 */
	public Folder getFolder(String name) {
		Folder folderTemp = null;
		for (int i = 0; i < directory.size(); i++) {
			if (directory.get(i).getName().equals(name)) {
				folderTemp = (Folder) directory.get(i);
			}
		}
		return folderTemp;
	}

	/**
	 * metodo que retorna todo o diretorio local
	 * @return ArrayList<FileAndFolder> - uma lista com todos os objetos dentro da pasta
	 */
	public ArrayList<FileAndFolder> getDirectory() {
		return directory;
	}

	/**
	 * metodo que modifica o diretorio da pasta
	 * @param directory ArrayList<FileAndFolder> - novo diretorio
	 */
	public void setDiretorio(ArrayList<FileAndFolder> directory) {
		this.directory = directory;
	}

	/**
	 * @return Date - a data de criação do objeto
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @return Date - a data de ediçao do objeto
	 */
	public Date getDateEdition() {
		return dateEdition;
	}

	/**
	 * atualizar a data de criação
	 * @param date Date - nova data
	 */
	public void setDateCreation(Date date) {
		this.dateCreation = date;

	}

	/**
	 * atualizar a data de edição
	 * @param date Date - nova data
	 */
	public void setDateEdition(Date date) {
		this.dateEdition = date;

	}

	/**
	 * @return o nome do objeto
	 */
	public String getName() {
		return name;
	}

	/**
	 * atualiza o nome
	 * @param name String - novo nome
	 */
	public void setName(String name) {
		this.name = name;
	}

	private Date getDateCurrent() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
	}

	public String toString(){
		return "name: " + getName() + " date of creation: " + getDateCreation() + " directory: " + getDirectory().toString();
	}

}
