package lexis.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public final class TrashFolder implements TrashFileAndFolder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	@Column(name = "trash_folder_folder")
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
