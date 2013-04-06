package structure;

public class Stockage {
	private final int LEN=500;
	int[] tab;
	int c;
	
	public Stockage(){
		tab=new int[3*LEN];
		c=0;
	}
	
	public void add(Triplet tr){
		if(c>=LEN)
			System.out.print("ERREUR");
		tab[c]=tr.t;
		tab[c+1]=tr.d;
		tab[c+2]=tr.f;
		c++;
	}
	
	public Triplet get(){
		if(c==0)
			return null;
		c--;
		return new Triplet(tab[c], tab[c+1], tab[c+2]);
	}
}
