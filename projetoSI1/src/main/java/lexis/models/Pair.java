package lexis.models;

public class Pair <B, C> {
	
	B first;
	C second;
	
	
	public Pair(B first, C second) {
		this.first = first;
		this.second = second;
	}
	
	
	public B getFirst() {
		return first;
	}
	
	public C getSecond() {
		return second;
	}
	
	
	public void setFirst(B first) {
		if(first != null)
			this.first = first;
	}
	
	public void setSecond(C second) {
		if(second != null)
			this.second = second;
	}

}
