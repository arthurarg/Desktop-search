package mode;

import gestionIO.Lecture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import structure.PairDoc;

public class Query {	
	public static void query(File index, int n) {
		boolean continuer = true;
		Scanner sc = new Scanner(System.in);

		PairDoc[] documents = Lecture.lireDocuments(index);
		double[] scores;
		
		while (continuer) {
			//(Re)initialisation des scores
			scores = new double[documents.length];
			
			//TODO dans quel cas s'arrète-t-on ?
			System.out.println("Entrez les mots que vous recherchez");
			String[] mots = sc.next().split(" "); 
			
			//Traite chaque mot de la recherche
			for (int j = 0; j < mots.length; j++) {
				File f = new File(index.getAbsolutePath() + "/index/mots/" + mots[j] + ".txt");

				if (f.exists()) {
					
					try {
						BufferedReader in = new BufferedReader(new FileReader(f));
						// On lit la seule ligne du fichier
						String[] donnees = in.readLine().split(" ");
						
						for (int k = 0; k < donnees.length; k+=2) {
							//Indice k : numéro du document, indice k+1 : frequence du mot dans ce document
							scores[Integer.parseInt(donnees[k])] += Math.log(1 + (2*(double)documents.length)/((double)donnees.length))
																			*(1 + Math.log(Double.parseDouble(donnees[k+1])));
							//TODO vérifier formule; le facteur 2 vient du fait que donnees contient deux chiffres (n° doc, frequence)
							
						}
						//Normalisaton en divisant par wd
						for (int l = 0; l < scores.length; l++)
							scores[l] /= documents[l].getWd();
						
						
						//fermeture de la source de lecture
						in.close();
					}
					catch (Exception e) { System.out.println("File not found : " + e.getMessage());}	
				}
			
				
				int[] resultats = renvoiIndicesMax(scores,n);
				
				System.out.println("Resultats pertinents : ");
				for (int l = 0; l < resultats.length; l++) {
					if (scores[resultats[l]] != 0)
						System.out.println(documents[resultats[l]].getPath());
				}
			}
		}		
	}
	
	
	private static int[] renvoiIndicesMax(double[] tab, int n) {

		int[] permutation = triFusion(tab);
		
		int[] resultat = new int[n];
		for (int w=0; w<n; w++)
			resultat[w] = permutation[w];
		
		return resultat;
	}
	
	//Renvoie la permutation qui trie dans l'ordre décroissant
	//En effet, on ne peut changer l'ordre des éléments, car leur indice correspond au numéro du document
	public static int[] triFusion(double[] tab) { 
		return triFusion(tab,0,tab.length-1); 
	}	
		
	public static int[] triFusion(double[] tab, int depart, int arrivee) {
		
		
		int[] permutation = new int[arrivee - depart + 1];
		
		if (depart==arrivee) { // Cas où le tableau considere n'a qu'un seul élément
			permutation[0] = depart;
			return permutation;
		}
		
		int[] permutation1 = triFusion(tab, depart, (depart+arrivee)/2); //tri tableau de gauche
		int[] permutation2 = triFusion(tab, (depart+arrivee)/2+1, arrivee); // tri tableau de droite
		
		
		//FUUUSIOOON
		int marqueur = 0, marqueur1 = 0, marqueur2 = 0;
		while (marqueur1<permutation1.length && marqueur2<permutation2.length) {
			if (tab[permutation1[marqueur1]] > tab[permutation2[marqueur2]]) {
				permutation[marqueur]=permutation1[marqueur1];
				marqueur1++;
			}
			else {
				permutation[marqueur]=permutation2[marqueur2];
				marqueur2++;
			}
			marqueur++;
			
		}
		
		if (marqueur1==permutation1.length) {
			while (marqueur2 < permutation2.length) {
				permutation[marqueur]=permutation2[marqueur2];
				marqueur2++;
				marqueur++;
			}
		}
		else {
			while (marqueur1 < permutation1.length) {
				permutation[marqueur]=permutation1[marqueur1];
				marqueur1++;
				marqueur++;
			}
		}
		
		return permutation;
	}
}
