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
	
	public int getId(String mot) {
		if (this.t.equals(mot))
			return (this.id);
		if (this.t.compareTo(mot) > 0 && this.droit != null)
			return this.droit.getId(mot);
		if (this.t.compareTo(mot) < 0 && this.gauche != null)
			return this.droit.getId(mot);
		
		//Si le mot n'existe pas dans le BTree, on renvoie -1
		return -1;
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
	
	public String f(int n){
		if (isLeaf())
			return "";
		String r="";
		r=r+t;
		r=r+" --- ";
		if(this.droit!=null)
			r=r+this.droit.f(n+t.length()+5)+"\n";
		for(int i=0;i<n+t.length()+5;i++)
			r=r+" ";
		if(this.gauche!=null)
			r=r+this.gauche.f(n+t.length())+"\n";
		return r;
	}
	
	public void aff(){
		System.out.println(f(0));
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

	
	private void equilibrageGauche() {
		this.droit=new BTree(this.t, this.ft, this.id, this.gauche.droit, this.droit);
		t=gauche.t;
		ft=gauche.ft;
		id=gauche.id;
		gauche=gauche.gauche;		
	}
	
	private void equilibrageDroite() {
		this.gauche=new BTree(this.t, this.ft, this.id, this.gauche, this.droit.gauche);
		t=droit.t;
		ft=droit.ft;
		id=droit.id;
		droit=droit.droit;		
	}
	
	
//---------
// Fonctions auxiliaires pour écrire/lire l'arbre sur le disque dur
//---------
	public void ecritureArbre (BufferedWriter f) throws IOException {
		if (!this.isLeaf()) {
			f.write("" + this.t + " " + this.id + NEW_LINE);
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
		this.id = Integer.parseInt(data[1]);
		this.ft = 0;
		this.gauche = new BTree();
		this.droit = new BTree();
		this.gauche.lireArbre(f);
		this.droit.lireArbre(f);
		
	}
}
