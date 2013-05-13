import mode.Query;
import gestionIO.Ecriture;
import gestionIO.Lecture;
import structure.BTree;


public class Test {


	public static void main(String[] args) {
		//Teste la structure de BTree
		//TestBTree();
		//Teste fonctions d'écritures et de lecture de données
		//TestIO();
		//Teste l'algorithme de TriFusion
		//TestTriFusion();
		
		//Test le mode build sur un minuscule échantillon
		//TestBuildFacile();
		//Test le mode query sur un minuscule échantillon
		//TestQueryFacile();
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
	
	public static void TestBuildFacile() {
		// Chemin à  modifier sur chaque machine
		String index = "/Users/jean-maxime/Documents/Informatique/Git/projet_info/projet_info/fichierstests/";
		String[] arguments = ("build --index-directory " + index + " --root " + index).split(" ");
		Main.main(arguments);
	}
	
	public static void TestQueryFacile() {
		// Chemin à  modifier sur chaque machine
		String index = "/Users/jean-maxime/Documents/Informatique/Git/projet_info/projet_info/fichierstests/";
		String[] arguments = ("query --index-directory " + index + " --max 5").split(" ");
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
	
	public static void TestTriFusion() {
		int n = 10;
		double[] essai = new double[n];
		for (int j = 0; j < n; j++)
			essai[j] = (double) j*j - 1 - 5*j;
		
		int[] resultat = Query.triFusion(essai);
		for (int k = 0; k < n; k++)
			System.out.println(resultat[k]);
	}
}
