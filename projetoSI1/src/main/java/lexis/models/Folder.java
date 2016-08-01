package lexis.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;


public class Folder implements FileAndFolder {

	@Column//(scale = 3)
	private String name;
	private Date dataCreation = getDateCurrent();
	private Date dataEdition = getDateCurrent();
	@ElementCollection
	private ArrayList<FileAndFolder> directory = new ArrayList<FileAndFolder>();
	
	public Folder(String name){
		this.name = name;
	}
	
	
	/*public Folder newFolder(String name2){
		Folder folder = new Folder(name2);
		return folder;
	}*/
	
	public void addFolder(String name){
		Folder temp =  new Folder(name);
		if(getFolder(name) == null){
			this.directory.add(temp);
		}
		
	}
	
	public void removeFolder(Folder folder){
		this.directory.remove(folder);
	}
	
	public Folder getFolder(String name2){
		Folder folderTemp = null;
		for (int i = 0; i < directory.size(); i++) {
			if(directory.get(i).getName().equals(name2)){
				folderTemp = (Folder) directory.get(i);
			}
		}
		return folderTemp;
	}

	public ArrayList<FileAndFolder> getDirectory(){
		return directory;
	}
	
	public void setDiretorio(ArrayList<FileAndFolder> directory){
		this.directory =directory;
	}
	
	
	public Date getDateCreation() {
		return dataCreation;
	}
	
	public Date getDateEdition() {
		return dataEdition;
	}
	
	public void setDateCreation(Date date) {
		this.dataCreation = date;
		
	}
	
	public void setDateEdition(Date date) {
		this.dataEdition = date;
		
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDateCurrent() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
	}

	
	
}
