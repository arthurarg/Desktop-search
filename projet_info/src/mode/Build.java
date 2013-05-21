package mode;

import gestionIO.Ecriture;
import gestionIO.Lecture;

import java.io.File;
import java.util.LinkedList;

import structure.BTree;
import structure.BTreeDoc;
import structure.Bool;
import structure.Pair;
import structure.PairDoc;
import structure.StructureStockage;
import structure.ThreadEcriture;
import structure.ThreadLecture;

public class Build {

	public static void build(File index, LinkedList<File> root, String regex) {
//-------
//TODO Supprime l'index s'il existe deja
//-------
		if (new File(index.getAbsolutePath() + "/index/").exists())
			new File(index.getAbsolutePath() + "/index/").delete();
		
		new File(index.getAbsolutePath() + "/index/").mkdir();
		
		
//-------		
//Recense tous les documents à indexer dans ListeDocuments
//-------
		LinkedList<String> l = new LinkedList<String>();//Parcours les dossiers root et leurs sous-dossiers
		while (!root.isEmpty()) {
			File[] temp = root.poll().listFiles();
			
			
			if (temp!= null) {
				for (int i=0; i<temp.length;i++) {
					try {
						if (temp[i].isFile() && temp[i].getCanonicalPath().matches(regex)) // Tout dossier "normal" sera indéxé
							l.add(temp[i].getCanonicalPath());
						else if (temp[i].isDirectory()) // On parcourt les sous-dossiers
							root.add(temp[i]);
					}
					catch (Exception e) {
						System.err.println("Erreur d'ouverture d'un fichier");
					}
				}
			}
		}
		
		//Stockage de Documents dans le tableau à partir de la LinkedList
		if (l.size() == 0) {
			System.out.println("ok"); // TODO supprimer ligne
			return;
		}


		PairDoc[] listeDocuments = new PairDoc[l.size()];
		int numDoc = 0;
		for (String s : l) {
				listeDocuments[numDoc] = new PairDoc (s, -1); // Score Wd initialisé à -1;
				numDoc++;
		}
		
//-------
// Travail document par document
//-------
		
		//Vocabulaire de l'ensemble des documents
		BTree vocabulaire = new BTree();
		StructureStockage triplets=new StructureStockage();
		Bool boolThread=new Bool(false);
		
		Thread lecture=new Thread(new ThreadLecture(boolThread, listeDocuments, index, vocabulaire, triplets));
		Thread ecriture=new Thread(new ThreadEcriture(boolThread, triplets, index, vocabulaire));
		
		System.out.println("création du BTree");
		lecture.start();
		//CreationBTree(listeDocuments, vocabulaire, triplets); TODO supprimer
		System.out.println("réussie!");
		
		// ecrit l'index dans le dossier "mots"
		System.out.println("ecriture de l'index");
		ecriture.start();
		
		//EcritureIndex.creation(triplets, index);
		System.out.println("réussie!");

	}
	
	public static void CreationBTree(PairDoc[] listeDocuments, File index,
			BTree vocabulaire, StructureStockage triplets) throws InterruptedException{
		
		BTreeDoc donnees;
		for (int d = 0; d < listeDocuments.length; d++) {
			System.out.println("Indexation du fichier " + listeDocuments[d].getPath() + " ... ");
			//Récupère tout le vocabulaire du document dans un BTreeDoc
			donnees = Lecture.analyserDocument(new File(listeDocuments[d].getPath()));
			listeDocuments[d].setWd(donnees.calculWd());
			
			//Réinjecte donnees dans le BTree de vocabulaire total, tout en creant un flux de triplets
			Pair tmp;
			
			while ((tmp = donnees.retirerMot()) != null) { //retire un élement du BTreeDoc sous forme de paire	
				//TODO tester puis supprimer	
				int t = vocabulaire.insererMot(tmp.getMot()); // insertion dans le vocabulaire
				System.out.println(tmp.getMot()+" "+t);
				//Triplet formé par (insererMot(tmp.string),d,tmp.frequency)
				triplets.add(t,d, (int)tmp.getFrequence());
				Thread.sleep(1);
			}
			//Libération de la mémoire prise par le vocabulaire du document
			donnees = null; 
			
		}
		//Ecris la liste des documents, indexes par numero, avec leur score Wd
		Ecriture.ecrireDocuments(index,listeDocuments);
		
	}

}
