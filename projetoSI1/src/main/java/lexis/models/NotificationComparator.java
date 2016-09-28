package lexis.models;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

public class NotificationComparator implements Comparator<Notification> {
	
	@Override
	public int compare(Notification o1, Notification o2) {
		return -o1.compareTo(o2);
	}

}
