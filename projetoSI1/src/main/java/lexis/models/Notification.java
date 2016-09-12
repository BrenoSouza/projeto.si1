package lexis.models;

import java.time.LocalDateTime;

public class Notification {

	String owner;
	LocalDateTime log;

	String fileName;
	Type fileType;
	String typeSharing;
	
	boolean unread;

	public Notification(String owner, SharedFileReadOnly fileShared, LocalDateTime log) {

		if (owner == null || fileShared == null || log == null)
			throw new NullPointerException();

		this.owner = owner;
		this.log = log;
		this.fileName = fileShared.getName();
		this.fileType = fileShared.getType();
		this.typeSharing = fileShared.getTypeSharing();
		this.unread = true;

	}
	
	
	
	public String getOwner() {
		return owner;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public LocalDateTime getLog() {
		return log;
	}
	
	public Type getFileType() {
		return fileType;
	}
	
	public String getTypeSharing() {
		return typeSharing;
	}
	
	public boolean isUnread() {
		return unread;
	}
	
	public void setUnread(boolean value) {
		unread = value;
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
		return "Owner: " + getOwner() + "Unread: " + isUnread() + "\nName of File: " + getFileName() + 
				"\nType of File: " + getFileType().name().toLowerCase() + "\nLog: " + 
				getLog().toString() + "\nType Sharing: " + getTypeSharing() + "\n";
	}
	
	
	
	
}
