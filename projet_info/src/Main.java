import java.io.File;
import java.util.LinkedList;
import java.util.regex.Pattern;

import mode.Build;
import mode.Query;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Variables déterminées par les arguments optionnels
		//TODO si --root non précisé en mode build, on envoie une liste chaînée vide
		//TODO si --index-directory non precise, on prend le repertoire depuis lequel le programme est execute
		File indexDir = new File(System.getProperty("user.dir"));
		String regex = "";
		LinkedList<File> root = new LinkedList<File> ();
		// TODO Par defaut, on affiche les 10 résultats les plus pertinents
		int n = 0; 
		
		
		// Traitement des options
		for (int i = 1; i<args.length && i!=0; i++) {
		
			
			if (args[i].matches("^--index-directory$") && isPathValid(args[i+1])) // cas ou l'option --index-directory est appele
					indexDir = new File(args[++i]); // le chemin est enregistré dans le args suivant; i incrémenté pour éviter le traitement de l'args suivant	
					
			else if (args[i].matches("^--root$") && isPathValid(args[i+1])) // cas ou l'option --root est appelé
				root.add(new File(args[++i]));
			
			else if (args[i].matches("^--accept$") && isRegexValid(args[i+1])) { // cas ou l'option --accept est appele
				if (regex=="")
					regex = args[++i];
				else
					regex+= "|" + args[++i];
			}
			
			else if (args[i].matches("^--max$"))// cas ou l'option --max est appele
				n = Integer.parseInt(args[++i]);
			
			else  {// ce n'est pas une option, il y a un probleme
				throw new IllegalArgumentException(args[i] + " isn't a valid option");
			}
		}
		
		
		//TODO supprimer cet batterie de tests
		for (File f : root) {
			try { System.out.println(f.getCanonicalPath()); }
			catch (Exception e) { System.err.print("wtf"); }
		}
		if (root.size()==0)
			System.out.println("ok");
		
		//On s'intéresse au mode, stocké dans args[0]
		if (args[0].equals("query"))
			Query.query(indexDir,n);
		else if (args[0].equals("build"))
			Build.build(indexDir,root,regex);
		else
			throw new IllegalArgumentException ("The mode " + args[0] + " doesn't exit. Existing modes : build or query");

	}

	// --------------------
	// Fonctions auxiliaires
	// --------------------

	public static boolean isPathValid(String path) {
		if (!new File(path).isDirectory()) 
			throw new IllegalArgumentException (path + " is not a valid path for a directory");	
		return true;
	}
	
	public static boolean isRegexValid(String regex) {
		try { Pattern.compile(regex); }
		catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return true;
	}

}
