package mode;

import gestionIO.Ecriture;
import gestionIO.Lecture;

import java.io.File;
import java.util.LinkedList;

import structure.BTree;
import structure.BTreeDoc;
import structure.Pair;
import structure.PairDoc;

public class Build {

	public static void build(File index, LinkedList<File> root, String regex) {
		
//-------		
//Recense tous les documents à indexer dans ListeDocuments
//-------
		LinkedList<String> l = new LinkedList<String>();
		//Parcours les dossiers root et leurs sous-dossiers
		while (!root.isEmpty()) {
			File[] temp = root.poll().listFiles();
			
			
			if (temp!= null) {
				for (int i=0; i<temp.length;i++) {
					try {
						if (temp[i].isFile() ) // Tout dossier "normal" sera indéxé
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
		BTreeDoc donnees;
		
		//Indexation de tous les documents
		for (int d = 0; d < listeDocuments.length; d++) {
			System.out.println("Indexation du fichier " + listeDocuments[d].getPath() + " ... ");
			//Récupère tout le vocabulaire du document dans un BTreeDoc
			donnees = Lecture.analyserDocument(new File(listeDocuments[d].getPath()));
			listeDocuments[d].setWd(donnees.calculWd());
			
			//Réinjecte donnees dans le BTree de vocabulaire total, tout en creant un flux de triplets
			Pair tmp;
			while ((tmp = donnees.retirerMot()) != null) { //retire un élement du BTreeDoc sous forme de paire	
				//TODO tester puis supprimer	
				System.out.println(tmp.getMot());
				int t = vocabulaire.insererMot(tmp.getMot()); // insertion dans le vocabulaire
				//Triplet formé par (insererMot(tmp.string),d,tmp.frequency)
				//TODO a implementer -- > Stockage.ajouter(t,d,tmp.getFrequence());
				//TODO
				//TODO
			}
			donnees = null; //Libération de la mémoire prise par le vocabulaire du document
			
			//TODO si on veut sauvegarder un run, c'est ici (vocabulaire à jour car update du nouveau document)
			//TODO
			//TODO
			System.out.println("réussie!");
		}
		//TODO remplacer tous les i.txt en string.txt ??
		//Ecris la liste des documents, indexes par numero, avec leur score Wd
		Ecriture.ecrireDocuments(index,listeDocuments);
	}
}
