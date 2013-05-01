package structure;

public class StructureStockage {
	
	class Bloc{
		Stockage c;
		Bloc p, n;
		
		public Bloc(){
			c=new Stockage();
			p=null;
			n=null;
		}
	}
	
	Bloc start, current;
	
	public StructureStockage(){
		start=new Bloc();
		current=start;
	}
	
	public void add(Triplet tr){
		if(!current.c.add(tr)){
			current.n=new Bloc();
			current.n.p=current;
			current=current.n;
			if(start.n==null)
				start.n=current;
			current.c.add(tr);
		}
	}
	
	public void add(int t, int d, int f){
		if(!current.c.add(t, d, f)){
			current.n=new Bloc();
			current.n.p=current;
			current=current.n;
			if(start.n==null)
				start.n=current;
			current.c.add(t, d, f);
		}
	}
	
	public Triplet get(){
		if(start==null)
			return null;
		Triplet t=start.c.get();
		
		if(t!=null)
			return t;
		
		if(start.n==null)
			return null;
		
		start=start.n;
		
		return get();
	}
	
	public boolean isEmpty(){
		if(start==null)
			return true;
		
		boolean isStartEmpty=start.c.isEmpty();
		
		if(!isStartEmpty)
			return false;
		
		if(start.n==null)
			return true;
		
		return start.n.c.isEmpty();
	}
	
	public String toString(){
		String s="";
		int n=1;
		Bloc c=start;
		
		while(c!=null){
			s=s+"Bloc "+n+" :\n"+c.c.toString()+"\n";
			c=c.n;
			n++;
		}
		return s;
	}
	
	public void aff(){
		System.out.print(this);
	}

}
