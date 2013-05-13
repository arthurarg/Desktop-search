package structure;

import java.io.File;

import mode.Build;

public class ThreadLecture implements Runnable{
	
	public ThreadLecture(Bool l, PairDoc[] listeDocuments, File index,
			BTree vocabulaire, StructureStockage triplets) {
		super();
		this.listeDocuments = listeDocuments;
		this.index = index;
		this.vocabulaire = vocabulaire;
		this.triplets = triplets;
		this.l=l;
		
		l.v=false;
	}

	Bool l;
	
	PairDoc[] listeDocuments;
	File index;
	BTree vocabulaire;
	StructureStockage triplets=new StructureStockage();
	
	@Override
	public void run() {
		try {
			Build.CreationBTree(listeDocuments, index, vocabulaire, triplets);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//vocabulaire.aff();
		l.v=true;
		
	}
	
}
