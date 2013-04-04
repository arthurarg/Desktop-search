package gestionIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import structure.BTree;
import structure.BTreeDoc;
import structure.Pair;

public class Lecture {


	public static BTreeDoc analyserDocument(File document) {
		try {
			FileReader f = new FileReader (document); 
			BufferedReader in = new BufferedReader(f);
			BTreeDoc donnees = new BTreeDoc();
			
			
			//Lis les caractères et insère les diffèrents mots
			int  k;
			char c;
			String s = null;
			while ((k = in.read()) != -1) {
				c = (char) k;
				if (Character.isLetter(c)) {
					if (s==null)
						s = "" + c;
					else
						s+= c;
				}
				else if (s!= null) {
					donnees.insererMot(s.toLowerCase());
					s = null;
				}
			}
			//Il faut ajouter le dernier mot !
			if (s != null) {
				donnees.insererMot(s.toLowerCase());
				s = null;
			}
			
			in.close();
			f.close();
			
			return donnees;
		}
		catch (IOException e) {
			System.err.println("Erreur : impossible d'indexer ce fichier");
			return new BTreeDoc();
		}
	}
	
	
	public static Pair[] lireDocuments (String index) {
		
		//Objet serializable : profitons en !
		Pair[] tab = null;
		try {
			ObjectInputStream f = new ObjectInputStream(new FileInputStream(index+"index/documents"));
			tab = (Pair[]) f.readObject();
			f.close();
		}
		catch(Exception e) {
			System.err.println("Impossible d'ouvrir la liste des documents indéxés : " + e.getMessage());
			System.exit(1);
		}
		return tab;
	}
	
	
	public static BTree lireVocabulaire (String index) {
		BTree vocabulaire = new BTree();
		try {
			BufferedReader f = new BufferedReader (new FileReader(index+"index/vocabulaire"));
			vocabulaire.lireArbre(f);
			f.close();
		}
		catch(Exception e) {
			System.err.println("Impossible d'ouvrir le vocabulaire : " + e.getMessage());
			//TODO supprimer toute trace de l'index via une méthode si ce dernier n'est pas utilisable ??
			System.exit(1);
		}	
		return vocabulaire;
	}
	
}
