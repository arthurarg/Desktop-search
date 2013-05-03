package gestionIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;

import structure.BTree;
import structure.PairDoc;

public class Ecriture {
	
	
//Enregistre sur le disque dur le tableau documents
	public static void ecrireDocuments (File index, PairDoc[] documents) {
		//Cree le dossier qui va bien
		try {
			File indexFolder = new File(index.getCanonicalPath() + "/index");
			if (!indexFolder.exists())
				indexFolder.mkdir();}
		catch (Exception ex1) {}
		

		//TODO tester le cas ou ce n'est pas un dossier ?
		
		//Objet serializable : profitons en !
		try {
			ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(index.getCanonicalPath()+"/index/documents"));
			f.writeObject(documents);
			f.close();
		}
		catch(Exception ex2) {
			System.err.println("Impossible d'enregistrer la liste des documents indexés : " + ex2.getMessage());
			//TODO supprimer toute trace de l'index via une méthode si ce dernier n'est pas utilisable ??
			System.exit(1);
		}
	}
	
	
	
	public static void ecrireVocabulaire (String index, BTree vocabulaire) {
		//Cree le dossier qui va bien
		File indexFolder = new File(index + "index");
		if (!indexFolder.exists())
			indexFolder.mkdir();
		//TODO tester le cas ou ce n'est pas un dossier ?
		
		try {
			BufferedWriter f = new BufferedWriter (new FileWriter(index+"index/vocabulaire"));
			vocabulaire.ecritureArbre(f);
			f.close();
		}
		catch(Exception e) {
			System.err.println("Impossible d'enregistrer le vocabulaire : " + e.getMessage());
			//TODO supprimer toute trace de l'index via une méthode si ce dernier n'est pas utilisable ??
			System.exit(1);
		}	
	}
}
