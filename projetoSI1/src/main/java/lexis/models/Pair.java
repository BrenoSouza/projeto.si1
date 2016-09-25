package lexis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Pair <B, C> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	@Column(name = "pair_first")
	B first;
	@Column(name = "pair_second")
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
