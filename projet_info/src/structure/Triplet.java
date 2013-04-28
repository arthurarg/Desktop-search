package structure;

public class Triplet {
	
	public int t, d, f; // t term, d document, f frequency

	public Triplet(int t, int d, int f) {
		super();
		this.t = t;
		this.d = d;
		this.f = f;
	}
	
	public String toString(){
		return "("+t+", "+d+", "+f+")";
	}

}
