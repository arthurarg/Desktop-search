
import java.io.File;
import java.io.IOException;

import structure.BTree;
public class TestsPerso {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		/*StructureStockage s=new StructureStockage();
		for(int k=0;k<6;k++)
			s.add(new Triplet(k,k,k));
		s.aff();
		for(int k=0;k<8;k++){
			System.out.println("-----------\n"+s.get()+s.isEmpty());
			s.aff();
		}*/
		/*Boolean b=new Boolean(true);
		f(b);
		System.out.print(b);*/
		//File f=new File("C:/Users/Arthur/Documents/Cours X/inf431/GitRepo/projet_info/mots_sansthreads");
		//System.out.println(f.getAbsolutePath());
		//System.out.print(f.mkdir());
		/*File f=new File("C:/Users/Arthur/Documents/Cours X/inf431/GitRepo/projet_info", "motsr");
		try{
			deleteFolder(f);
		}catch(IOException e){
			
		}
		f.mkdir();*/


		BTree t=new BTree();
		t.insererMot("je");
		t.aff();
		t.insererMot("beau");
		t.aff();
		t.insererMot("suis");
		t.aff();
		t.insererMot("je");
		t.aff();
		t.insererMot("intelligent");
		t.aff();
		t.insererMot("suis");
		t.aff();
		
		
		
	}public static void deleteFolder(File path) throws IOException {
		if (!path.exists()) throw new IOException(
				"File not found '" + path.getAbsolutePath() + "'");
				if (path.isDirectory()) {
					File[] children = path.listFiles();
					for (int i=0; children != null && i<children.length; i++)
						deleteFolder(children[i]);
					if (!path.delete()) throw new IOException(
						"No delete path '" + path.getAbsolutePath() + "'");
				}
				else if (!path.delete()) throw new IOException(
					"No delete file '" + path.getAbsolutePath() + "'");
	    }
	public static void f(Boolean b){
		b=false;
	}

}
class Eee implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			}
	}

	public Eee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}