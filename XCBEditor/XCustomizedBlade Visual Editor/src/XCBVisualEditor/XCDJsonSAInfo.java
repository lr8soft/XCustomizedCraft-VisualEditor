package XCBVisualEditor;

public class XCDJsonSAInfo {
	public String SAName;
	public String SAStep[];
	public int SACount[];
	public int StepDamage[];
	public int StepCount,SAcost,SANum;
	public XCDJsonSAInfo(String name,int SANum,String stepName[],int count[],int StepDamage[],int step,int cost) {
		this.SANum=SANum;
		this.SAName=name;
		this.SAStep=stepName;
		this.SAcost=cost;
		this.SACount=count;
		this.StepCount=step;
		this.StepDamage=StepDamage;
	}
}