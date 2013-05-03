package structure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureIndex {
	
	public static void creation(StructureStockage s, File indexDir){
		// creation du dossier mots
		File f=new File(indexDir, "index/mots/");
		f.mkdir();
		String path=f.getAbsolutePath()+"/";
		
		Triplet tr;int i=0;
		while(!s.isEmpty()){
			tr=s.get();
			ajouter(tr, path);i++;
		}
		System.out.println(i+" fichiers créés");
	}
	
	static void ajouter(Triplet tr, String path){
		// on ajoute au fichier "mot" le couple (d, f)
		if(tr!=null){
			try{
				System.out.println("ajout de "+tr.t+".txt à l'index");
				System.out.println(path+tr.t+".txt");
				FileWriter file = new FileWriter(path+tr.t+".txt", true);
				file.write(tr.d+" "+tr.f+" ");
				file.close();
			}
			catch(IOException e) {
				System.out.println("erreur lors de l'ajout de "+tr.t+".txt à l'index");
			}
		}
	}
	
	public static void conversionId(BTree voc, File indexDir){
		if(voc!=null){
			String path=indexDir.getAbsolutePath()+"/index/mots/";
			System.out.println("path "+path);
			
			File id;
			PairMot p;
			
			int i=0;
			while((p=voc.retirerMot())!=null){
				System.out.println(p.id+" -> "+p.mot);
				id=new File(path+p.id+".txt");
				id.renameTo(new File(path+p.mot+".txt"));i++;
			}
			System.out.println(i+" fichiers renommés");
		}
	}
}
