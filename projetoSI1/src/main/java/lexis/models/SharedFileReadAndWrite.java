package lexis.models;

import java.time.LocalDateTime;

public final class SharedFileReadAndWrite extends SharedFile {
	
	public static final String TYPE_SHARING = "Read and Write";
	
	public SharedFileReadAndWrite (File file, String owner) {
		super(file, owner);
		
	}
	
	public void setData(String data, LocalDateTime dateEdition) {
		getFile().setData(data, dateEdition);
	}
	
	@Override
	public  String getTypeSharing() {
		return TYPE_SHARING;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof SharedFileReadAndWrite))
			return false;
					
		SharedFileReadAndWrite otherFile = (SharedFileReadAndWrite) obj;
		
		return getOwner().equals(otherFile.getOwner()) && 
				getFile().equals(otherFile.getFile());
	}
	
}
