package structure;

public class PairDoc implements java.io.Serializable {
	//Make it serializable
	private static final long serialVersionUID = 9068635201840963112L;
	
	String path;
	double Wd;
	
	public PairDoc(String s, int i) {
		this.path = s;
		this.Wd = i;
	}
	
	public String getPath() {
		return this.path;
	}
	public double getWd() {
		return this.Wd;
	}
	public void setWd(double i) {
		this.Wd = i;
	}

}
