package lexis.models;

import java.io.Serializable;

public class Pair <B, C> implements Serializable {
	
	private static final long serialVersionUID = 7045731650503959480L;
	
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
