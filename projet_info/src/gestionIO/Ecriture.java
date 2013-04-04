package gestionIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import structure.Pair;

public class Ecriture {
	
	
	//Enregistre sur le disque dur le tableau documents
	public static void ecrireDocuments (String index, Pair[] documents) {
		//Cree le dossier qui va bien
		File indexFolder = new File(index + "index");
		if (!indexFolder.exists())
			indexFolder.mkdir();
		//TODO tester le cas ou ce n'est pas un dossier ?
		
		//Objet serializable : profitons en !
		try {
			ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(index+"index/documents"));
			f.writeObject(documents);
			f.close();
		}
		catch(Exception e) {
			System.err.println("Impossible d'enregistrer la liste des documents indéxés : " + e.getMessage());
			//TODO supprimer toute trace de l'index via une méthode si ce dernier n'est pas utilisable ??
			System.exit(1);
		}
	}
}
