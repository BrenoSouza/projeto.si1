package lexis.models;

import java.time.LocalDateTime;


public final class SharedFileReadAndWrite extends SharedFile {
	
	private static final long serialVersionUID = -7382772783384859855L;
	
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

}
