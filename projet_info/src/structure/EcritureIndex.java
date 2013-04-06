package structure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureIndex {
	
	public static void creation(Stockage s, BTree voc){
		// creation du dossier mots
		File f=new File("mots");
		f.mkdir();
		
		Triplet tr;
		String mot;
		while(!s.isEmpty()){
			tr=s.get();
			mot="";// name=voc.getWord(tr.t);
			ajouter(mot, tr);
		}
	}
	
	static void ajouter(String mot, Triplet tr){
		// on ajoute au fichier "mot" le couple (d, f)
		try{
			FileWriter file = new FileWriter("mots/"+mot+".txt", true);
			file.write(tr.d+" "+tr.f+" ");
			file.close();
		}
		catch(IOException e){
			System.out.println("erreur lors de l'ajout de "+mot+" à l'index");
		}
	}
	
	public static void main(String[] args){
		File f=new File("mots");
		if(f.mkdir())
			System.out.println("OK");
		else System.out.println("NO");
		ajouter("bite", new Triplet(1, 8,9));
	}

}
