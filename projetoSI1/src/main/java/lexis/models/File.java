package lexis.models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;

/**
 * Classe responsavel pelo obejto do tipo arquivo.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
public class File implements FileAndFolder {
	@Column // (scale = 4)
	private String name;
	private Date dateCreation;
	private Date dateEdition;
	private String data;
	private static String DATAEMPTY = "";

	/**
	 * Contrutor que recebe o nome como obrigatorio datas criadas
	 * automaticamente e o codigo do arquivo vazio.
	 * 
	 * @param name
	 */
	public File(String name) {
		this.name = name;
		this.data = DATAEMPTY;
		dateCreation = getDateCurrent();
		dateEdition = getDateCurrent();
	}

	/**
	 * Retorna o codigo fonte do arquivo.
	 * 
	 * @return String data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Atualiza a data de criacao.
	 * 
	 * @param date
	 *            Date - nova data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return O nome do objeto.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Atualiza o nome.
	 * 
	 * @param name
	 *            String - novo nome
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * @return Date - a data de criação do objeto.
	 */
	@Override
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @return Date - a data de ediçao do objeto.
	 */
	@Override
	public Date getDateEdition() {
		return dateEdition;
	}

	/**
	 * Atualiza a data de criacao.
	 * 
	 * @param date
	 *            Date - nova data
	 */
	@Override
	public void setDateCreation(Date date) {
		this.dateCreation = date;

	}

	/**
	 * Atualiza a data de edicao.
	 * 
	 * @param date
	 *            Date - nova data
	 */
	@Override
	public void setDateEdition(Date date) {
		this.dateEdition = date;

	}

	private Date getDateCurrent() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
	}

	@Override
	public String toString() {
		return "name: " + getName() + " date of creation: " + getDateCreation() + " data: " + getData();
	}

}
