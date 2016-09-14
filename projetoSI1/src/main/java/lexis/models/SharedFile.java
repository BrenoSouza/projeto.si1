package lexis.models;

import java.time.LocalDateTime;

public abstract class SharedFile {
	
	private File file;
	private String owner;
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
