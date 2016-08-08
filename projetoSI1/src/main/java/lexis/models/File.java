package lexis.models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;

public class File implements FileAndFolder {
	@Column // (scale = 4)
	private String name;
	private Date dateCreation;
	private Date dateEdition;
	private String data;
	private static String DATAEMPTY = "";
	
	public File(String name){
		this.name = name;
		this.data = DATAEMPTY;
		dateCreation = getDateCurrent();
		dateEdition = getDateCurrent();
	}

	public String getData() {
		return data;
	}

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
	public Date getDateCreation() {
		return dateCreation;
	}

	@Override
	public Date getDateEdition() {
		return dateEdition;
	}

	@Override
	public void setDateCreation(Date date) {
		this.dateCreation = date;
		
	}

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
	public String toString(){
		return "name: " + getName() + " date of creation: " + getDateCreation() + " data: " + getData();
	}

}
