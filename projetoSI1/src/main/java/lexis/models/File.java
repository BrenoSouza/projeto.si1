package lexis.models;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;

/**
 * Classe responsavel pelo obejto do tipo arquivo.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
public class File implements FileAndFolder {
	public static final String EMPTY_DATA = "";

	@Column // (scale = 4)
	private String name;
	private LocalDateTime dateCreation;
	private LocalDateTime dateEdition;
	private String data;

	/**
	 * Contrutor que recebe o nome como obrigatorio datas criadas
	 * automaticamente e o conteudo do arquivo vazio.
	 * 
	 * @param name Nome do arquivo.
	 * @param parent Folder no qual o arquivo esta inserido.
	 */
	public File(String name) throws Exception {
		checkName(name);

		this.name = name;
		this.data = EMPTY_DATA;
		dateCreation = LocalDateTime.now();
		dateEdition = LocalDateTime.now();
	}
	
	public File() throws Exception {
		this(null);
		
	}

	/**
	 * Retorna o conteudo do arquivo.
	 * 
	 * @return String data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Atualiza o coteudo do arquivo.
	 * 
	 */
	public void setData(String data) {
		this.data = data;
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
	public void setDateEdition(LocalDateTime date) {
		this.dateEdition = date;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof File) {
			File file = (File) obj;
			if (this.getName().equals(file.getName())) {
				return true; // sao iguails.
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Name: " + getName() + "\nDate of creation: " + getDateCreation().toString() + "\nData: " + getData()
				+ "\n";
	}

	private void checkName(String name) throws Exception {
//		if (name == null)
//			throw new NullPointerException();

		if (name.equals("")) {
			// TODO throw new InvalidNameException();
		}

	}
	
	private void checkDate(LocalDateTime date) throws Exception {
		if(date == null)
			throw new NullPointerException();
		
//		if(date ja passou)
//			throw new InvalidDateException();
	}
	
	
}