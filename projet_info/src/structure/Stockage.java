package structure;

public class Stockage {
	private final int LEN=500;
	int[] tab;
	int c;
	
	public Stockage(){
		tab=new int[3*LEN];
		c=0;
	}
	
	public boolean add(Triplet tr){
		if(c>=LEN)
			return false;
		
		tab[3*c]=tr.t;
		tab[3*c+1]=tr.d;
		tab[3*c+2]=tr.f;
		c++;
		
		return true;
	}
	
	public Triplet get(){
		if(c==0)
			return null;
		c--;
		return new Triplet(tab[3*c], tab[3*c+1], tab[3*c+2]);
	}
	
	public boolean isEmpty(){
		return c==0;
	}
}
