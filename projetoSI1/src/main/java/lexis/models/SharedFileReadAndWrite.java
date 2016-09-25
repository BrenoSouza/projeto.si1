package lexis.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public final class SharedFileReadAndWrite extends SharedFile {
	
	@Version
	private Integer version;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
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
