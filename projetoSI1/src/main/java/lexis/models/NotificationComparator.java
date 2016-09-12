package lexis.models;

import java.util.Comparator;

public class NotificationComparator implements Comparator<Notification> {

	@Override
	public int compare(Notification o1, Notification o2) {
		return o1.compareTo(o2);
	}

}
