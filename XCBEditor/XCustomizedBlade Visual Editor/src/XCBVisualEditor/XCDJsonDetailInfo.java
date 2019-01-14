package XCBVisualEditor;

import java.util.Vector;

import com.google.gson.JsonArray;

public class XCDJsonDetailInfo {
	public int sa,standby,bladeduration,color,SELevel;
	public boolean iswitched;public float bladedamage;
	public String bladename,bladeModel,bladeTexture,showName,SEName;
	public Vector EnchantName,EnchantLevel;
	public XCDJsonDetailInfo(int sa,int standby,int duration,int color,boolean iswithed,float bladedamage,String bladename,
			String showName,String bladeModel,String bladeTexture,
			Vector EnchantName,Vector EnchantLevel,String SEName,int SELevel) {
		this.sa=sa;this.standby=standby;this.bladeduration=duration;this.iswitched=iswithed;
		this.bladedamage=bladedamage;this.bladeModel=bladeModel;this.bladeTexture=bladeTexture;
		this.bladename=bladename;this.color=color;this.showName=showName;
		this.EnchantName=EnchantName;this.EnchantLevel=EnchantLevel;
		this.SEName=SEName;this.SELevel=SELevel;
	}
}
