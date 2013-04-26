package structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class BTree {
	//Utilisé pour connaitre l'id du prochain mot
	static int whats_the_id = 1;
	public static final String NEW_LINE = System.getProperty("line.separator" );
	
	String t;
	int ft;
	int id;
	BTree gauche, droit;
	
	//Constructeurs
	public BTree() {
		this.t = null;
		this.ft = 0;
		this.id = -1;
		this.gauche = this.droit = null;
	}
	
	public BTree(String t, int ft, int id, BTree gauche, BTree droit) {
		this.t = t;
		this.ft = ft;
		this.id = id;
		this.gauche = gauche ;
		this.droit = droit;
	}


	// Insere un nouveau mot dans l'arbre
	public int insererMot(String mot) { 
		return insererMot(mot, 1);
	}
	public int insererMot(String mot, int ft) {
		int id; 
		if (isLeaf())
			return ajouterMot(mot,ft);
		else if (mot.compareTo(this.t) < 0) {
			id = this.gauche.insererMot(mot,ft);
			if (this.gauche.size() > this.droit.size())
				equilibrageGauche();
			return id;
		}
		else if (mot.compareTo(this.t) > 0) {
			id = this.droit.insererMot(mot,ft);
			if (this.gauche.size() + 1 < this.droit.size())
				equilibrageDroite();
			return id;
		}
		else {
			this.ft++;
			return this.id;
		}
	}
	
	// Attention, cette méthode de retrait n'assure pas la structure équilibrée de l'arbre !
	// En effet, elle sera utilisée pour vider tout l'arbre, les insertions ayant été effectuées au préalable
	// Opération ultime sur le BTree, aucune autre méthode autre que isLeaf() ne doit être appelée après le moindre appel à cette méthode
	public PairMot retirerMot() {
		PairMot p = null;
		
		if (this.t != null) {
			p = new PairMot(this.t, this.id); 
			this.t = null;
			return p;
		}
		
		if (this.gauche != null)
			p = this.gauche.retirerMot();
		
		if (p==null && this.droit != null) // Cas ou il n'y a plus de mots à gauche et qu'il peut y en avoir à droite
			p = this.droit.retirerMot();
		
		return p;
	}
	
	
	public boolean isLeaf() {
		return (this.t == null);
	}
	public int size() {
		if (isLeaf())
			return 0;
		else
			return Math.max(this.gauche.size(), this.droit.size()) +1;
	}
	public String toString() {
		if (isLeaf())
			return "";
		else
			return "" + this.gauche + "(" + this.t + ", Freq : " + this.ft + ", Id : " + this.id + 
					", Taille Gauche : " + this.gauche.size() + ", Taille Droite : " + this.droit.size() + ")" + NEW_LINE + this.droit;
	}

	
	
//----------------------------------
// Fonctions auxiliaires pour construire un BTree	
//----------------------------------
	//Update les champs lors de l'insertion
	private int ajouterMot(String t, int ft) {
		this.t = t;
		this.ft = ft;
		this.id = whats_the_id++; //affecte puis incremente
		//TODO est-ce vraiment genant d'avoir un étage de feuilles vides ?! modifiable facilement
		this.gauche = new BTree();
		this.droit = new BTree();
		
		return this.id;
	}	
	
	//Fonction de reequilibrage
	//TODO verifier que c'est ok
	private void equilibrageGauche() {
		// On sait que this.gauche n'est pas une feuille quand on appelle cette fonction
		if (this.droit!=null)
			this.droit = new BTree(this.t, this.ft, this.id, this.droit, this.gauche.droit);
		else {
			this.droit.t = this.t;
			this.droit.ft = this.ft;
			this.droit.id = this.id;
		}	
		
		this.t = this.gauche.t;
		this.ft = this.gauche.ft;
		this.id = this.gauche.id;
		this.gauche = this.gauche.gauche;				
	}
	
	private void equilibrageDroite() {
		// On sait que this.droit n'est pas une feuille quand on appelle cette fonction
		if (this.gauche != null)
			this.gauche = new BTree(this.t, this.ft, this.id, this.gauche, this.droit.gauche);
		else {
			this.gauche.t = this.t;
			this.gauche.ft = this.ft;
			this.gauche.id = this.id;
		}	
		
		this.t = this.droit.t;
		this.ft = this.droit.ft;
		this.id = this.droit.id;
		this.droit = this.droit.droit;			
	}
	
//---------
// Fonctions auxiliaires pour écrire/lire l'arbre sur le disque dur
//---------
	public void ecritureArbre (BufferedWriter f) throws IOException {
		if (!this.isLeaf()) {
			f.write("" + this.t + " " + this.ft + " " + this.id + NEW_LINE);
			this.gauche.ecritureArbre(f);
			this.droit.ecritureArbre(f);
		}
		if (this.isLeaf())
			f.write("#" + NEW_LINE);
	}
	
	public void lireArbre (BufferedReader f) throws IOException {
		String s = f.readLine();
		if (s== null) //Fin du dossier attend
			return;
		
		if (s.equals("#")) //Cela reste une feuille
			return;
		
		String[] data = s.split(" ");
		this.t = data[0];
		this.ft = Integer.parseInt(data[1]);
		this.id = Integer.parseInt(data[2]);
		this.gauche = new BTree();
		this.droit = new BTree();
		this.gauche.lireArbre(f);
		this.droit.lireArbre(f);
		
	}
}
