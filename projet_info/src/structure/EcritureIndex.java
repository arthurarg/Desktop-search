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
		while(!s.isEmpty()){
			tr=s.get();
			ajouter(tr, path);
		}
	}
	
	static void ajouter(Triplet tr, String path){
		// on ajoute au fichier "mot" le couple (d, f)
		try{
			FileWriter file = new FileWriter(path+tr.t+".txt", true);
			file.write(tr.d+" "+tr.f+" ");
			file.close();
		}
		catch(IOException e) {
			System.out.println("erreur lors de l'ajout de "+tr.t+".txt à l'index");
		}
	}
	
	static void conversionId(BTree voc, String path){
		if(voc!=null){
			File id;
			PairMot p=voc.retirerMot();
			while(p!=null){
				id=new File(path+p.id+".txt");
				id.renameTo(new File(path+p.mot+".txt"));
				p=voc.retirerMot();
			}
		}
	}
}
