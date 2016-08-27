package lexis.models;

import java.time.LocalDateTime;


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
	public static final String UNAMED_FILE = "semTitulo";

	@Column // (scale = 4)
	private String name;
	private LocalDateTime dateCreation;
	private LocalDateTime dateEdition;
	private String data;
	private Type type;
	private Permission permission;

	/**
	 * Contrutor que recebe o nome como obrigatorio datas criadas
	 * automaticamente e o conteudo do arquivo vazio.
	 * 
	 * @param name Nome do arquivo.
	 * @param parent Folder no qual o arquivo esta inserido.
	 */
	public File(String name, Type type, Permission permission) {

		if(!Util.isAValidName(name)) 
			name = UNAMED_FILE;
		
		if(type == null)
			type = Type.TXT;

		this.name = name;
		this.data = EMPTY_DATA;
		this.type = type;
		dateCreation = LocalDateTime.now();
		dateEdition = LocalDateTime.now();
		this.permission = permission;
	}
	
	public File()  {
		this(UNAMED_FILE, Type.TXT, Permission.PRIVATE);
		
	}
	
	
	
	public Type getType() {
		return type;
	}
	
	
	public void setType(Type type) {
		if(type != null)
			this.type = type;
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
		if(data != null)
			this.data = data;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Permission getPermission() {
		return permission;
	}
	
	@Override
	public void setPermission(Permission permission) {
		if(permission != null)
			this.permission = permission;
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
	public void setDateCreation(LocalDateTime date) {
		if(date != null)
			dateCreation = date;
	}

	@Override
	public LocalDateTime getDateEdition() {
		return dateEdition;
	}

	@Override
	public void setDateEdition(LocalDateTime date) {
		if(date != null)
			this.dateEdition = date;

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof File))
			return false;
		
		File otherFile = (File) obj;

		
		return this.getName().equals(otherFile.getName()) && 
				this.getType().equals(otherFile.getType());
	}

	@Override
	public String toString() {
		return "Name: " + getName() + "\nCreation: " + getDateCreation().toString() +
				"\nLast Edition: " + getDateEdition().toString() + "\nType of File: " + 
				type.name().toLowerCase() + "\nPermission: " + permission.name().toLowerCase() + 
				"\nData: " + data + "\n";

	}
	

}