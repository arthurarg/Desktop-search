package gestionIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import structure.BTree;

public class convertirIndex {

	/**
	 * @param args
	 */
	public static void conversion(File indexDir) throws IOException{
		(new File(indexDir.getAbsolutePath()+"/index/data.txt")).delete();
		
		FileWriter data = new FileWriter(indexDir.getAbsolutePath()+"/index/data.txt", true);
		
		File wordsDir=new File(new File(indexDir,"index"), "mots");
		
		File[] words = wordsDir.listFiles();
		
		BTree t=new BTree();
		int n=0;
		for (int i=0; words != null && i<words.length; i++){
			if(isTxt(words[i])){
				t.insererMot(getWord(words[i]), n);
				n=n+1+insererMot(data, words[i]);
				words[i].delete();
			}
		}
		data.close();
		wordsDir.delete();
		
		BufferedWriter voc = new BufferedWriter (new FileWriter(indexDir+"/index/vocabulaire"));
		t.ecritureArbre(voc);
		voc.close();
	}
	
	static boolean isTxt(File f){
		return f.getName().endsWith(".txt");
	}
	
	static int insererMot(FileWriter data, File doc){
		FileReader f;
		try {
			f = new FileReader (doc);
			BufferedReader in = new BufferedReader(f);
			
			try {
				String l=in.readLine();
				data.write(l+"\r\n");
				
				in.close();
				
				f.close();
				
				return l.length()+1;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	static String getWord(File doc){
		String l=doc.getName();
		int p=0;
		for(int i=0; i<l.length(); i++){
			if(l.charAt(i)=='.')
				p=i;
		}
		return l.substring(0, p);
	}
	
	public static void deleteFolder(File path) throws IOException {
		if (!path.exists()) throw new IOException(
			"File not found '" + path.getAbsolutePath() + "'");
			if (path.isDirectory()) {
				File[] children = path.listFiles();
				for (int i=0; children != null && i<children.length; i++)
					deleteFolder(children[i]);
				if (!path.delete()) throw new IOException(
					"No delete path '" + path.getAbsolutePath() + "'");
			}
			else if (!path.delete()) throw new IOException(
				"No delete file '" + path.getAbsolutePath() + "'");
    }
	
	/*static void analyseDoc(File doc){
		FileReader f;
		try {
			f = new FileReader (doc);
			BufferedReader in = new BufferedReader(f);
			
			try {
				String l=in.readLine();
				
				int s=0;
				for(int i=0; i<l.length(); i++){
					if(l.charAt(i)==' '){
						int c=Integer.valueOf(l.substring(s, i));
						s=i+1;
						System.out.println(c);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

}
