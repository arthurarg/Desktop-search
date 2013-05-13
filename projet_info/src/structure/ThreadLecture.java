package structure;

import gestionIO.Lecture;

import java.io.File;

import mode.Build;

public class ThreadLecture implements Runnable{
	
	public ThreadLecture(Bool l, PairDoc[] listeDocuments,
			BTree vocabulaire, StructureStockage triplets) {
		super();
		this.listeDocuments = listeDocuments;
		this.vocabulaire = vocabulaire;
		this.donnees = donnees;
		this.triplets = triplets;
		this.l=l;
		
		l.v=false;
	}

	Bool l;
	
	PairDoc[] listeDocuments;
	BTree vocabulaire;
	BTreeDoc donnees;
	StructureStockage triplets=new StructureStockage();
	
	@Override
	public void run() {
		try {
			Build.CreationBTree(listeDocuments, vocabulaire, triplets);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//vocabulaire.aff();
		l.v=true;
		
	}
	
}
