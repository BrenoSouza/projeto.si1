package lexis.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

//@Entity
public abstract class SharedFile implements  Serializable {

	private static final long serialVersionUID = 1057400889808018146L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	
	@Column(name = "shared_file_file")
	private File file;
	
	@Column(name = "shared_file_owner")
	private String owner;
	
	@Column(name = "shared_file_index")
	private int index;
	
	
	public SharedFile(File file, String owner) {
		if(file == null || owner == null)
			throw new NullPointerException();
		
		this.file = file;
		this.owner = owner;
	}
	
	
	/**
	 * Recupera o tipo do arquivo
	 * @return Retorna uma enum que indica 
	 * o tipo do arquivo.
	 */
	public Type getType() {
		return file.getType();
	}
	
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	/**
	 * Recupera o nome do arquivo.
	 * @return
	 */
	public String getName() {
		return file.getName();
	}
	
	/**
	 * Recupera o conteudo do arquivo.
	 * @return 
	 */
	public String getData() {
		return file.getData();
	}
	
	/**
	 * Recupera a permissao do arquivo.
	 * @return Retorna uma enum indicando a permissao 
	 * do arquivo.
	 */
	public Permission getPermission() {
		return file.getPermission();
	}
	
	/**
	 * Recupera a data de criacao do arquivo.
	 * @return
	 */
	public LocalDateTime getDateCreation() {
		return file.getDateCreation();
	}
	
	protected File getFile() {
		return file;
	}
	
	/**
	 * Recupera a data da ultima edicao do arquivo.
	 * @return
	 */
	public LocalDateTime getDateEdition() {
		return file.getDateEdition();
	}
	
	/**
	 * Recupera o login do criador do arquivo.
	 * @return
	 */
	public String getOwner() {
		return owner;
	}
	
	abstract public String getTypeSharing();
	
	abstract public void setData(String data, LocalDateTime dateEdition);

	@Override
	public String toString() {
		
		
		return "Name: " + getName() + "\nCreation: " + getDateCreation().toString() + 
				"\nLast Edition: " + getDateEdition().toString() + "\nType of File: " + 
				getType().name().toLowerCase() + "\nPermission: " + getPermission().name().toLowerCase() + 
				"\nData: " + getData() + "\nType Sharing: " + getTypeSharing() + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof SharedFile))
			return false;
					
		SharedFile otherFile = (SharedFile) obj;
		
		return getOwner().equals(otherFile.getOwner()) && 
				getFile().equals(otherFile.getFile());
	}

}
