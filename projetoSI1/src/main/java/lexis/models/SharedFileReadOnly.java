package lexis.models;

import java.time.LocalDateTime;


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
public class SharedFileReadOnly {
	
	public static final String TYPE_SHARING = "Read Only";
	
	private File file;
	private String owner;
	
	/**
	 * Construtor da classe que recebe o arquivo 
	 * em questao e o dono do arquivo.
	 * @param file Arquivo a ser passado
	 * @param owner Login do dono do arquivo
	 */
	public SharedFileReadOnly(File file, String owner) {
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
	
	/**
	 * Recupera o conteudo do arquivo.
	 * @return 
	 */
	public String getData() {
		return file.getData();
	}
	
	/**
	 * Recupera o nome do arquivo.
	 * @return
	 */
	public String getName() {
		return file.getName();
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
	
	public String getTypeSharing() {
		return TYPE_SHARING;
	}

	protected File getFile() {
		return file;
	}
	
	@Override
	public String toString() {
		
		
		return "Name: " + getName() + "\nCreation: " + getDateCreation().toString() + 
				"\nLast Edition: " + getDateEdition().toString() + "\nType of File: " + 
				getType().name().toLowerCase() + "\nPermission: " + getPermission().name().toLowerCase() + 
				"\nData: " + getData() + "\nType Sharing: " + getTypeSharing() + "\n";
	}
	
	// TODO
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != this.getClass())
			return false;
					
		SharedFileReadOnly otherFile = (SharedFileReadOnly) obj;
		
		return getOwner().equals(otherFile.getOwner()) && 
				getFile().equals(otherFile.getFile());
	}
	
}
