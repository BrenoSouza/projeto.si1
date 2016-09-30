package lexis.models;

import java.io.Serializable;
import java.util.Comparator;

public class NotificationComparator implements Comparator<Notification>, Serializable {
	
	private static final long serialVersionUID = -704485300510560417L;

	@Override
	public int compare(Notification o1, Notification o2) {
		return -o1.compareTo(o2);
	}

}
