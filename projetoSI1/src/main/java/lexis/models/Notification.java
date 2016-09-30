package lexis.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe possui notificacoes para o usuario sobre arquivos 
 * que foram compartilhados com ele.
 * @author klynger
 *
 */
public class Notification implements Comparable<Notification>, Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8161737401915179695L;
	
	public static final boolean READ = false;
	public static final boolean UNREADED = true;
	
	String owner;
	LocalDateTime log;	
	SharedFile sharedFile;

	boolean unread;
	
	/**
	 * Construtor com o minimo de atributos necessarios 
	 * para criar uma notificacao.
	 * @param owner Login do criador do arquivo.
	 * @param fileShared Arquivo que foi compartilhado, este 
	 * arquivo pode ser do tipo SharedFileReadOnly ou de classes que 
	 * herdam dela, como a SharedFileReadAndWrite.
	 * @param log Momento em que foi feito o compartilhamento.
	 */
	public Notification(String owner, SharedFile sharedFile, LocalDateTime log) {

		if (owner == null || sharedFile == null || log == null)
			throw new NullPointerException();

		this.owner = owner;
		this.log = log;
		this.sharedFile = sharedFile;
		this.unread = true;

	}
	
	
	/**
	 * Recupera o login do criador do arquivo.
	 * @return
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Recupera o nome do arquivo compartilhado.
	 * @return
	 */
	public String getFileName() {
		return sharedFile.getName();
	}
	
	/**
	 * Recupera o momento em que o arquivo foi compartilhado.
	 * @return
	 */
	public LocalDateTime getLog() {
		return log;
	}
	
	/**
	 * Recupera o tipo do arquivo.
	 * @return
	 */
	public Type getFileType() {
		return sharedFile.getType();
	}
	
	/**
	 * Recupera o tipo de compartilhamento do arquivo (Leitura 
	 * ou leitura e escrita).
	 * @return
	 */
	public String getTypeSharing() {
		return sharedFile.getTypeSharing();
	}
	
	/**
	 * Recupera o estado da notificacao que pode ser 
	 * True para nao lida e false para lida.
	 * @return
	 */
	public boolean isUnread() {
		return unread;
	}
	
	public void setUnread(boolean value) {
		unread = value;
	}
	
	public SharedFile getSharedFile() {
		return sharedFile;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Notification))
			return false;
		
		Notification otherNotification = (Notification) obj;
		
		return getOwner().equals(otherNotification.getOwner()) &&
				getFileName().equals(otherNotification.getFileName()) && 
				getFileType().equals(otherNotification.getFileType()) && 
				getLog().equals(otherNotification.getLog()) && 
				getTypeSharing().equals(otherNotification.getTypeSharing());
	}
	
	@Override
	public String toString() {
		return "Owner: " + getOwner() + "Unread: " + isUnread() + "\n" + getSharedFile().toString();
	}



	@Override
	public int compareTo(Notification o) {
		
		if(this.equals(o))
			return 0;
		
		return this.getLog().compareTo(o.getLog());
	}




	
	
	
	
}
