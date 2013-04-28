package structure;

public class Stockage {
	private final int LEN=100;
	int[] tab;
	int c;
	int cGet;
	
	
	public Stockage(){
		tab=new int[3*LEN];
		c=0;
		cGet=0;
	}
	
	public boolean add(Triplet tr){
		return add(tr.t, tr.d, tr.f);
	}
	
	public boolean add(int t, int d, int f){
		if(c>=LEN)
			return false;
		
		tab[3*c]=t;
		tab[3*c+1]=d;
		tab[3*c+2]=f;
		c++;
		
		return true;
	}	
	
	public Triplet get(){
		if(c==0)
			return null;
		if(cGet>=c)
			return null;
		if(cGet>=LEN)
			return null;
		int t=cGet;
		cGet++;
		
		return new Triplet(tab[3*t], tab[3*t+1], tab[3*t+2]);
	}
	
	public boolean isEmpty(){
		return c==0;
	}
	
	public String toString(){
		String s="";
		
		if(cGet<c && cGet<LEN)
			s="("+tab[3*cGet]+", "+tab[3*cGet+1]+", "+tab[3*cGet+2]+")";
		
		for(int i=cGet+1;i<c;i++)
			s=s+", "+"("+tab[3*i]+", "+tab[3*i+1]+", "+tab[3*i+2]+")";
		return s;
	}
}
