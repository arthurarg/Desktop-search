import gestionIO.Ecriture;
import gestionIO.Lecture;
import structure.BTree;


public class Test {


	public static void main(String[] args) {
		//Teste la structure de BTree
		//TestBTree();
		//Test le mode build sur un minuscule échantillon
		//TestFacile();
		//Teste fonctions d'écritures et de lecture de données
		//TestIO();
		
		
	}

	public static void TestBTree() {
		BTree t = new BTree();
		t.insererMot("manger");
		t.insererMot("coucou");
		t.insererMot("hahaha");
		t.insererMot("coucou");
		t.insererMot("soeur");
		t.insererMot("ok");
		t.insererMot("wtf");
		System.out.println(t.insererMot("fuit"));
		System.out.println(t);
	}
	
	public static void TestFacile() {
		// Chemin à  modifier sur chaque machine
		String index = "/Users/jean-maxime/Documents/Informatique/Git/projet_info/projet_info/fichierstests/";
		String[] arguments = ("build --index-directory " + index + " --root " + index).split(" ");
		Main.main(arguments);
	}
	
	public static void TestIO() {
		String index = "/Users/jean-maxime/Documents/Informatique/Git/projet_info/projet_info/fichierstests/";
		BTree t = new BTree();
		t.insererMot("manger");
		t.insererMot("coucou");
		t.insererMot("hahaha");
		t.insererMot("coucou");
		t.insererMot("soeur");
		t.insererMot("ok");
		t.insererMot("wtf");
		t.insererMot("fuit");
		System.out.println(t);
		Ecriture.ecrireVocabulaire(index, t);
		t = Lecture.lireVocabulaire(index);
		System.out.print(t);
		
		
	}
}
