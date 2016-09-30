package lexis.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

//@Entity
public final class TrashFile implements TrashFileAndFolder, Serializable {
	
	private static final long serialVersionUID = 9062475293631527828L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	@Column(name = "Trash_file_file")
	private File file;
	
	public TrashFile(File file) {
		if(file == null)
			throw new NullPointerException();
		
		this.file = file;
		this.file.putInTrash();
		
	}
	
	@Override
	public String getName() {
		return file.getName();
	}
	
	public Type getType() {
		return file.getType();
	}
	
	@Override
	public LocalDateTime getDateCreation() {
		return file.getDateCreation();
	}

	@Override
	public LocalDateTime getDateEdition() {
		return file.getDateEdition();
	}

	@Override
	public Permission getPermission() {
		return file.getPermission();
	}

	@Override
	public String getStringPath() {
		return file.getStringPath();
	}
	
	@Override
	public boolean isInTrash() {
		return file.isInTrash();
	}

	protected File getFile() {
		return file;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(!(obj instanceof TrashFile)) 
			return false;
		
		TrashFile otherTrashFile = (TrashFile) obj;
		
		return this.getFile().equals(otherTrashFile.getFile());
	}

}
