package structure;
import java.io.File;

public class EcritureIndex {
	
	public static void creation(Stockage s, BTree voc){
		// creation du dossier mots
		
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
	}

}
