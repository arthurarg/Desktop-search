package structure;

<<<<<<< HEAD
import gestionIO.Lecture;
import gestionIO.convertirIndex;

=======
>>>>>>> refs/remotes/origin/master
import java.io.File;
import java.io.IOException;

public class ThreadEcriture implements Runnable{
	
	Bool l;
	
	public ThreadEcriture(Bool l, StructureStockage triplets, File index, BTree vocabulaire) {
		super();
		this.l = l;
		this.triplets = triplets;
		this.index = index;
		this.vocabulaire=vocabulaire;
	}

	StructureStockage triplets=new StructureStockage();
	File index;
	BTree vocabulaire;
	
	@Override
	public void run() {
		File f=new File(new File(index,"index"), "mots");
		try{
			convertirIndex.deleteFolder(f);
		}catch(IOException e){
			System.out.println("impossible de vider le dossier");
		}
		f.mkdir();
		String path=f.getAbsolutePath()+"/";
		
		Triplet tr;
		while(!triplets.isEmpty() || l.v==false){
			tr=triplets.get();
			EcritureIndex.ajouter(tr, path);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		
		// convertit id.txt en mot.txt
		System.out.println("conversion");
		EcritureIndex.conversionId(vocabulaire, index);
		System.out.println("réussie!");
	}
	
}
