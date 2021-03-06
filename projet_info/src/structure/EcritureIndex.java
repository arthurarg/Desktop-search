package structure;
import gestionIO.convertirIndex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureIndex {
	
	public static void creation(StructureStockage s, File indexDir){
		// creation du dossier mots
		


		File f=new File(new File(indexDir,"index"), "mots");
		try{
			convertirIndex.deleteFolder(f);
		}catch(IOException e){
			System.out.println("impossible de vider le dossier");
		}
		f.mkdir();
		String path=f.getAbsolutePath()+"/";
		
		Triplet tr;
		while(!s.isEmpty()){
			tr=s.get();
			ajouter(tr, path);
		}
	}
	
	static void ajouter(Triplet tr, String path){
		// on ajoute au fichier "mot" le couple (d, f)
		if(tr!=null){
			try{

				//System.out.println("ajout de "+tr.t+".txt à l'index");
				//System.out.println(path+tr.t+".txt");

				FileWriter file = new FileWriter(path+tr.t+".txt", true);
				file.write(tr.d+" "+tr.f+" ");
				file.close();
			}
			catch(IOException e) {
				System.out.println("erreur lors de l'ajout de "+tr.d+" à "+tr.t+".txt");
			}
		}
	}
	
	public static void conversionId(BTree voc, File indexDir){
		if(voc!=null){

			String path=indexDir.getAbsolutePath()+"/index/mots/";

			
			File id;
			PairMot p;
			
			while((p=voc.retirerMot())!=null){
				//System.out.println(p.id+" -> "+p.mot);
				id=new File(path+p.id+".txt");
				id.renameTo(new File(path+p.mot+".txt"));
			}
		}
	}
}
