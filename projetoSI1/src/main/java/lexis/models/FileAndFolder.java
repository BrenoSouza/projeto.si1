package lexis.models;

import java.util.Date;

import javax.persistence.Embeddable;


@Embeddable
public interface FileAndFolder{
	
	String getName();
	
	void setName(String name);
	
	Date getDataCriacao();
	
	Date getDataEdicao();
	
	void setDataCriacao(Date data);
	
	void setDataEdicao(Date data);

}
