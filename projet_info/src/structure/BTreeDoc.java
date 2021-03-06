package structure;

public class BTreeDoc {
	
	String t;
	int frequence;
	BTreeDoc gauche, droit;
	
	//Constructeurs
	public BTreeDoc() {
		this.t = null;
		this.frequence = 0;
		this.gauche = this.droit = null;
	}
	
	public BTreeDoc(String t, int frequence, BTreeDoc gauche, BTreeDoc droit) {
		this.t = t;
		this.frequence = frequence;
		this.gauche = gauche ;
		this.droit = droit;
	}


	// Insere un nouveau mot dans l'arbre
	public void insererMot(String mot) { 
		if (isLeaf())
			ajouterMot(mot);
		else if (mot.compareTo(this.t) < 0) {
			this.gauche.insererMot(mot);
			if (this.gauche.size() > this.droit.size())
				equilibrageGauche();
		}
		else if (mot.compareTo(this.t) > 0) {
			this.droit.insererMot(mot);
			if (this.gauche.size() + 1 < this.droit.size())
				equilibrageDroite();
		}
		else
			this.frequence++;
	}
	
	
	// Attention, cette méthode de retrait n'assure pas la structure équilibrée de l'arbre !
	// En effet, elle sera utilisée pour vider tout l'arbre, les insertions ayant été effectuées au préalable
	// Opération ultime sur le BTreeDoc, aucune autre méthode autre que isLeaf() ne doit être appelée après le moindre appel à cette méthode
	public Pair retirerMot() {
		Pair p = null;
		
		if (this.t != null) {
			p = new Pair(this.t, this.frequence);
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
	public static final String NEW_LINE = System.getProperty("line.separator" );
	public String toString() {
		if (isLeaf())
			return "";
		else
			return "" + this.gauche + "(" + this.t + ", Freq : " + this.frequence +  
					", Taille Gauche : " + this.gauche.size() + ", Taille Droite : " + this.droit.size() + ")" + NEW_LINE + this.droit;	}

	
	//Calcul de Wd à partir de l'arbre
	public double calculWd() {
		return Math.sqrt(this.calculs());
	}
	private double calculs() {
		if (isLeaf())
			return 0;
		return Math.pow(1 + Math.log(this.frequence), 2) + this.gauche.calculs() + this.droit.calculs();
	}
	
//----------------------------------
// Fonctions auxiliaires pour construire un BTree	
//----------------------------------
	//Update les champs lors de l'insertion
	private void ajouterMot(String t) {
		this.t = t;
		this.frequence = 1;
		//TODO est-ce vraiment genant d'avoir un étage de feuilles vides ?! modifiable facilement
		this.gauche = new BTreeDoc();
		this.droit = new BTreeDoc();
	}	
	
	//Fonction de reequilibrage
	//TODO verifier que c'est ok
	private void equilibrageGauche() {
		this.droit=new BTreeDoc(this.t, this.frequence, this.gauche.droit, this.droit);
		t=gauche.t;
		frequence=gauche.frequence;
		gauche=gauche.gauche;
	}
	
	private void equilibrageDroite() {
		this.gauche=new BTreeDoc(this.t, this.frequence, this.gauche, this.droit.gauche);
		t=droit.t;
		frequence=droit.frequence;
		droit=droit.droit;
	}
}
