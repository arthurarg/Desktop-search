package structure;

public class Pair {
	String mot;
	int frequence;
	
	public Pair(String s, int i) {
		this.mot = s;
		this.frequence = i;
	}
	
	public String getMot() {
		return this.mot;
	}
	public double getFrequence() {
		return this.frequence;
	}
}
