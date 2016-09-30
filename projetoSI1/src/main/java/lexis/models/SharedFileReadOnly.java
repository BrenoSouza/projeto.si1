package lexis.models;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Classe usada para compartilhar um arquivo 
 * com outro usuario e esse usuario tem apenas 
 * permissao para leitura do arquivo. Essa classe 
 * possui metodos get para todos os atributos que 
 * possam interessar o leitor do arquivo e possui 
 * um atributo owner que e o criador do arquivo. Esse 
 * criador nao pode mudar.
 * @author klynger
 *
 */
//@Entity
public class SharedFileReadOnly extends SharedFile {

	private static final long serialVersionUID = -9170535956018149023L;

	public static final String TYPE_SHARING = "Read Only";
	
	@Version
	private Integer version;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * Construtor da classe que recebe o arquivo 
	 * em questao e o dono do arquivo.
	 * @param file Arquivo a ser passado
	 * @param owner Login do dono do arquivo
	 */
	public SharedFileReadOnly(File file, String owner) {
		super(file, owner);
	}



	@Override
	public String getTypeSharing() {
		return TYPE_SHARING;
	}



	@Override
	public void setData(String data, LocalDateTime dateEdition) {
		// TODO Auto-generated method stub
		
	}



	public Integer getVersion() {
		return version;
	}



	public void setVersion(Integer version) {
		this.version = version;
	}

}
