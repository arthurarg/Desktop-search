import structure.*;


public class Test {


	public static void main(String[] args) {
		//teste la structure de BTree
		TestBTree();

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
}
