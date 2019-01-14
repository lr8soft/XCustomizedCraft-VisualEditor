package XCBVisualEditor;

public class XCDJsonSEInfo {
	public String SEName;public int SECost,SELevel;public String[] SEStep;
	public int[] SERuntime;public double[] SEDamage;
	public XCDJsonSEInfo(String name,int cost,int level,String[] step,int[] runtime,double[] damage) {
		this.SEName=name;this.SECost=cost;this.SELevel=level;this.SEStep=step;
		this.SERuntime=runtime;this.SEDamage=damage;
	}
	
}
