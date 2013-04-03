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
	
	public BTreeDoc(String t, int ft, BTreeDoc gauche, BTreeDoc droit) {
		this.t = t;
		this.frequence = frequence;
		this.gauche = gauche ;
		this.droit = droit;
	}


	// Insere un nouveau mot dans l'arbre
	public void insererMot(String mot) { 
		insererMot(mot, 1);
	}
	public void insererMot(String mot, int ft) {
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
			this.frequence++;
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
	private void ajouterMot(String t, int ft) {
		this.t = t;
		this.frequence = ft;
		//TODO est-ce vraiment genant d'avoir un étage de feuilles vides ?! modifiable facilement
		this.gauche = new BTreeDoc();
		this.droit = new BTreeDoc();
	}	
	
	//Fonction de reequilibrage
	//TODO verifier que c'est ok
	private void equilibrageGauche() {
		// On sait que this.gauche n'est pas une feuille quand on appelle cette fonction
		if (this.droit!=null)
			this.droit = new BTreeDoc(this.t, this.frequence, this.droit, this.gauche.droit);
		else {
			this.droit.t = this.t;
			this.droit.frequence = this.frequence;
		}	
		
		this.t = this.gauche.t;
		this.frequence = this.gauche.frequence;
		this.gauche = this.gauche.gauche;				
	}
	
	private void equilibrageDroite() {
		// On sait que this.droit n'est pas une feuille quand on appelle cette fonction
		if (this.gauche != null)
			this.gauche = new BTreeDoc(this.t, this.frequence, this.gauche, this.droit.gauche);
		else {
			this.gauche.t = this.t;
			this.gauche.frequence = this.frequence;
		}	
		
		this.t = this.droit.t;
		this.frequence = this.droit.frequence;
		this.droit = this.droit.droit;			
	}
}
