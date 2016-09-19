package lexis.models;

import java.time.LocalDateTime;

public final class TrashFile implements TrashFileAndFolder {
	
	private File file;
	
	public TrashFile(File file) {
		if(file == null)
			throw new NullPointerException();
		
		this.file = file;
		
	}
	
	@Override
	public String getName() {
		return file.getName();
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