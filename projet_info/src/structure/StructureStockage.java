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
