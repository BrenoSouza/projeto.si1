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
	private Date dataCriacao = getPegaDataAtual();
	private Date dataEdicao = getPegaDataAtual();
	@ElementCollection
	private ArrayList<FileAndFolder> diretorio = new ArrayList<FileAndFolder>();
	
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
			this.diretorio.add(temp);
		}
		
	}
	
	public void removeFolder(Folder folder){
		this.diretorio.remove(folder);
	}
	
	public Folder getFolder(String name2){
		Folder folderTemp = null;
		for (int i = 0; i < diretorio.size(); i++) {
			if(diretorio.get(i).getName().equals(name2)){
				folderTemp = (Folder) diretorio.get(i);
			}
		}
		return folderTemp;
	}

	public ArrayList<FileAndFolder> getDiretorio(){
		return diretorio;
	}
	
	public void setDiretorio(ArrayList<FileAndFolder> diretorio){
		this.diretorio =diretorio;
	}
	
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public Date getDataEdicao() {
		return dataEdicao;
	}
	
	public void setDataCriacao(Date data) {
		this.dataCriacao = data;
		
	}
	
	public void setDataEdicao(Date data) {
		this.dataEdicao = data;
		
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getPegaDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
	}

	
	
}
