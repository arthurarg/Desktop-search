package structure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureIndex {
	
	public static void creation(Stockage s, BTree voc, File indexDir){
		// creation du dossier mots
		File f=new File(indexDir, "mots");
		f.mkdir();
		String path=f.getAbsolutePath()+"\\mots\\";
		
		Triplet tr;
		String mot;
		while(!s.isEmpty()){
			tr=s.get();
			mot="";// name=voc.getWord(tr.t);
			ajouter(mot, tr, path);
		}
	}
	
	static void ajouter(String mot, Triplet tr, String path){
		// on ajoute au fichier "mot" le couple (d, f)
		try{
			FileWriter file = new FileWriter(path+mot+".txt", true);
			file.write(tr.d+" "+tr.f+" ");
			file.close();
		}
		catch(IOException e){
			System.out.println("erreur lors de l'ajout de "+mot+" à l'index");
		}
	}
}
