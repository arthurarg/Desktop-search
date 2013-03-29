package structure;

public class BTree {
	
	String t;
	int ft;
	double pointeur;
	BTree gauche, droit;
	
	//Constructeur
	public BTree() {
		this.t = null;
		this.ft = 0;
		this.pointeur = -1;
		this.gauche = this.droit = null;
	}
	
	/*public BTree(String t, int ft, double pointeur, BTree gauche, BTree droit) {
		this.t = t;
		this.ft = ft;
		this.pointeur = pointeur;
		this.gauche = gauche; 
		this.droit = droit;
	}*/

	// Insere un nouveau mot dans l'arbre
	public void insererMot(String mot) { 
		insererMot(mot, 1);
	}
	public void insererMot(String mot, int f) {
		if (isLeaf())
			ajouterMot(mot,ft);
		else if (mot.compareTo(this.t) < 0) {
			this.gauche.insererMot(mot,ft);
			if (this.gauche.size() > this.droit.size())
				equilibrageGauche();
		}
		else if (mot.compareTo(this.t) > 0) {
			this.droit.insererMot(mot,ft);
			if (this.gauche.size() + 1 < this.droit.size())
				equilibrageDroite();
		}
		else
			this.ft++;
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
	
	
	//Permet de connaître la taille de l'ensemble des invertedList; met à jour les "pointeurs"
	//TODO en vue d'un tableau de Pair (comment identifier un document avec un numéro ? stocker une hashmap ? + petit fichier texte pur dire quels dossiers sont indexes
	//Prendre n=0 pour appeler la fonction
	public double prepareIndex(double n) {
		if (isLeaf())
			return n;
		this.pointeur = n;
		n+= (double) this.ft;
		n = this.gauche.prepareIndex(n);
		n = this.droit.prepareIndex(n);
		return n;
	}

	
	
//----------------------------------
// Fonctions auxiliaires pour construire un BTree	
//----------------------------------
	//Update les champs lors de l'insertion
	private void ajouterMot(String t, int ft) {
		this.t = t;
		this.ft = ft;
		//TODO est-ce vraiment genant d'avoir un étage de feuilles vides ?! modifiable facilement
		this.gauche = new BTree();
		this.droit = new BTree();
	}	
	
	//Fonction de reequilibrage
	//TODO verifier que c'est ok
	private void equilibrageGauche() {
		String motRacine = this.t;
		int nombreRacine = this.ft;
		
		this.t = this.gauche.t;
		this.ft = this.gauche.ft;
		this.gauche = this.gauche.gauche;
		insererMot(motRacine, nombreRacine);
	}
	
	private void equilibrageDroite() {
		String motRacine = this.t;
		int nombreRacine = this.ft;
		
		this.t = this.droit.t;
		this.ft = this.droit.ft;
		this.droit = this.droit.droit;
		insererMot(motRacine, nombreRacine);
	}
}
