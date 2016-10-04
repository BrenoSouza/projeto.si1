package lexis.models;

import java.time.LocalDateTime;

public final class TrashFolder implements TrashFileAndFolder {
	
	private static final long serialVersionUID = -8344927605101822430L;

	Folder folder;
	
	public TrashFolder(Folder folder) {
		if(folder == null)
			throw new NullPointerException();
		
		this.folder = folder;
		this.folder.putInTrash();
	}
	
	@Override
	public String getName() {
		return folder.getName();
	}

	@Override
	public LocalDateTime getDateCreation() {
		return folder.getDateCreation();
	}

	@Override
	public LocalDateTime getDateEdition() {
		return folder.getDateEdition();
	}

	@Override
	public Permission getPermission() {
		return folder.getPermission();
	}

	@Override
	public String getStringPath() {
		return folder.getStringPath();
	}
	
	protected Folder getFolder() {
		return folder;
	}
	
	@Override
	public boolean isInTrash() {
		return folder.isInTrash();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
					
		if(!(obj instanceof TrashFolder))
			return false;
		
		TrashFolder otherTrashFolder = (TrashFolder) obj;
		
		return this.getFolder().equals(otherTrashFolder.getFolder());
	}
}
