package lexis.models;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class NotificationComparator implements Comparator<Notification> {
	
	@Version
	private Integer version;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Override
	public int compare(Notification o1, Notification o2) {
		return -o1.compareTo(o2);
	}

}
