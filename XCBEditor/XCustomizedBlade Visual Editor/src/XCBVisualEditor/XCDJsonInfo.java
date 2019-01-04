package XCBVisualEditor;

import java.util.Vector;

import com.google.gson.JsonArray;

public class XCDJsonInfo {
	public String[] recipeItems;
	public int sa,standby,bladeduration,color;
	public boolean iswitched,useCustomRecipe;public float bladedamage;
	public String bladename,bladeModel,bladeTexture,showName;
	public Vector EnchantName,EnchantLevel;
	public XCDJsonInfo(int sa,int standby,int duration,int color,boolean iswithed,float bladedamage,String bladename,
			String showName,String bladeModel,String bladeTexture,boolean useCustomRecipe,String[] recipeItems,
			Vector EnchantName,Vector EnchantLevel) {
		this.sa=sa;this.standby=standby;this.bladeduration=duration;this.iswitched=iswithed;
		this.bladedamage=bladedamage;this.bladeModel=bladeModel;this.bladeTexture=bladeTexture;
		this.bladename=bladename;this.color=color;this.showName=showName;
		this.recipeItems=recipeItems;this.useCustomRecipe=useCustomRecipe;
		this.EnchantName=EnchantName;this.EnchantLevel=EnchantLevel;
	}
}