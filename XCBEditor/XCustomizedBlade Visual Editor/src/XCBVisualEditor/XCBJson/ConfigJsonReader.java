package XCBVisualEditor.XCBJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import XCBVisualEditor.XCDJsonDetailInfo;
import XCBVisualEditor.XCBUtil.XCBFileOperate;
import XCBVisualEditor.XCBUtil.XCBGetPath;
import XCBVisualEditor.XCBUtil.XCBToJsonArray;
public class ConfigJsonReader {
	public boolean addToolRecipe;
	public JsonObject json;private JsonObject serverdata;
	public String path;
	public JsonArray jsondata;
	public int datalen;
	public ConfigJsonReader(String inpath) {
		this.path=inpath;
		this.datalen=0;
		this.json=null;
		this.serverdata=null;
	}
	public int changeTinyCore(boolean sync) {
		try {
			return XCBFileOperate.writeBooleanToJson(json, sync, "XCBTinyCore", path);
		}catch(NullPointerException error) {
			return 0;
		}
	}
	public boolean TinyCoreSupport() {
		boolean sync=false;
		try {
			sync=json.get("XCBTinyCore").getAsBoolean();
		}catch(NullPointerException error) {}
		return sync;
	}
	public double GetConfigVersion() {
		double ret=0;
			try {
				ret=json.get("XCustomizedBladeVER").getAsDouble();
			}catch(NullPointerException error) {}
		return ret;
	}
	public int GetBladeCount() {
		int ret=0;
		for(int i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				temp.get("BladeName").getAsString();
				temp.get("BladeShowName").getAsString();
				temp.get("BladeModel").getAsString();
				temp.get("BladeTexture").getAsString();
				ret++;
			}catch(NullPointerException error) {
				continue;
			}
		}
		return ret;
	}
	public XCDJsonDetailInfo GetBladeInfo(String name) {
		XCDJsonDetailInfo ret=null;
		if(name==null) return ret;
		String SEName=null;
		int sa = 0,standby=0,duration=200,color=3316172,SELevel;boolean iswithed=false;JsonArray Enchantment=null,recipeItems=null;
		float bladedamage=10;String bladename=null,bladeModel=null,bladeTexture=null,showName=null;String[] recipeName= new String[11];
		for(int i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				if(temp.get("BladeName").getAsString().equals(name)) {
					try {
						bladename=temp.get("BladeName").getAsString();
						showName=temp.get("BladeShowName").getAsString();
						bladeModel=temp.get("BladeModel").getAsString();
						bladeTexture=temp.get("BladeTexture").getAsString();
					}catch(NullPointerException e) {break;}
					try {
						sa=temp.get("BladeSA").getAsInt();
						standby=temp.get("BladeStandBy").getAsInt();
						duration=temp.get("BladeDuration").getAsInt();
						color=temp.get("SwordColor").getAsInt();
						iswithed=temp.get("BladeWitched").getAsBoolean();
						bladedamage=temp.get("BladeDamge").getAsFloat();
					}catch(NullPointerException e) {}
					try {
						Enchantment=temp.get("Enchantment").getAsJsonArray();
					}catch(NullPointerException e) {}
					try {
						SELevel=temp.get("SELevel").getAsInt();
						SEName=temp.get("BladeSE").getAsString();
					}catch(NullPointerException e) {
						SELevel=0;SEName=null;
						System.out.println("XCustomizedBlade Warning:"+bladename+":"+showName+" haven\'t SE.");
					}
					ret=new XCDJsonDetailInfo(sa,standby,duration,color,
						iswithed,bladedamage,bladename,showName,bladeModel,bladeTexture,
						XCBToJsonArray.JsonArrayToVector(Enchantment,true),XCBToJsonArray.JsonArrayToVector(Enchantment,false),
						SEName,SELevel);
				}
			}catch(NullPointerException error) {
				continue;
			}
		}
		return ret;
	}
	public XCDJsonDetailInfo[] GetInfo() {
		XCDJsonDetailInfo[] info = new XCDJsonDetailInfo[jsondata.size()];
		this.datalen=jsondata.size();
		String SEName=null;
		int i,sa = 1,standby=1,duration=200,color=16744192,SELevel=10;boolean iswithed=false;JsonArray Enchantment=null;
		float bladedamage=10;String bladename=null,bladeModel=null,bladeTexture=null,showName=null;
		for(i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				bladename=temp.get("BladeName").getAsString();
				showName=temp.get("BladeShowName").getAsString();
				bladeModel=temp.get("BladeModel").getAsString();
				bladeTexture=temp.get("BladeTexture").getAsString();
			}catch(NullPointerException e) {
				continue;
			}
			try {
				sa=temp.get("BladeSA").getAsInt();
				standby=temp.get("BladeStandBy").getAsInt();
				duration=temp.get("BladeDuration").getAsInt();
				color=temp.get("SwordColor").getAsInt();
				iswithed=temp.get("BladeWitched").getAsBoolean();
				bladedamage=temp.get("BladeDamge").getAsFloat();
			}catch(NullPointerException e) {}
			try {
				Enchantment=temp.get("Enchantment").getAsJsonArray();
			}catch(NullPointerException e) {
				System.out.println("XCustomizedBlade Info:"+bladename+":"+showName+" haven\'t enchantment.");
			}
			try {
				Enchantment=temp.get("Enchantment").getAsJsonArray();
			}catch(NullPointerException e) {
				System.out.println("XCustomizedBlade Info:"+bladename+":"+showName+" haven\'t enchantment.");
			}
			try {
				SELevel=temp.get("SELevel").getAsInt();
				SEName=temp.get("BladeSE").getAsString();
			}catch(NullPointerException e) {
				SELevel=0;SEName=null;
				System.out.println("XCustomizedBlade Warning:"+bladename+":"+showName+" haven\'t SE.");
			}
				info[i]=new XCDJsonDetailInfo(sa,standby,duration,color,
						iswithed,bladedamage,bladename,showName,bladeModel,bladeTexture,
						XCBToJsonArray.JsonArrayToVector(Enchantment,true),XCBToJsonArray.JsonArrayToVector(Enchantment,false)
						,SEName,SELevel);
		}
		return info;
		
	}
	public int readFromJson() {
		double version;
		System.out.println("XCustomizedBlade_Config_Path:"+path);
		JsonParser jp=new JsonParser();
			try {
				json=(JsonObject)jp.parse(new FileReader(path));
				version=json.get("XCustomizedBladeVER").getAsDouble();
				if(version>=0.9) {
					try {
						if(!json.get("XCBTinyCore").getAsBoolean()) {
							addToolRecipe=json.get("ToolRecipe").getAsBoolean();
						}
					}catch(Exception e) {
						System.out.println("XCBVisualEditor OLD VER no TinyCore...");
					}
				}
				if(version>=1.3){
					try {
						serverdata=json.get("ServerInfo").getAsJsonObject();
						String hostname;int serverport;
						hostname=serverdata.get("ServerHostName").getAsString();
						serverport=serverdata.get("ServerPort").getAsInt();
					}catch(Exception e) {
						System.out.println("XCustomizedBlade:No server info!");
					}
				}
				jsondata=json.get("XCustomizedBladeConfig").getAsJsonArray();
				return 1;
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				e.printStackTrace();
				return 0;
			}
	}
	public void startJsonWork() {
		switch(readFromJson()) {
			case 0:break;
			case -1:
				JOptionPane.showMessageDialog(null, "Can\'t read config from file!");
				break;
		}
	}
	public void changeToJson(XCDJsonDetailInfo info,int datalen) {
		System.out.println("XCC Info:Try to replace the old blade->"+info.bladename);
		for(int i=0;i<datalen;i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				if(temp.get("BladeName").getAsString().equals(info.bladename)) {
					temp.remove("BladeShowName");temp.addProperty("BladeShowName", info.showName);
					temp.remove("BladeModel");temp.addProperty("BladeModel",info.bladeModel);
					temp.remove("BladeTexture");temp.addProperty("BladeTexture",info.bladeTexture);
					temp.remove("BladeDamge");temp.addProperty("BladeDamge",info.bladedamage);
					temp.remove("BladeDuration");temp.addProperty("BladeDuration",info.bladeduration);
					temp.remove("BladeWitched");temp.addProperty("BladeWitched",info.iswitched);
					temp.remove("BladeStandBy");temp.addProperty("BladeStandBy",info.standby);
					temp.remove("BladeSA");temp.addProperty("BladeSA", info.sa);
					temp.remove("SwordColor");temp.addProperty("SwordColor",info.color);
					try {temp.remove("Enchantment");}catch(Exception e) {}
					try {
						temp.remove("BladeSE");
						temp.remove("SELevel");
					}catch(Exception e) {}
					temp.addProperty("BladeSE", info.SEName);
					temp.addProperty("SELevel", info.SELevel);
					temp.add("Enchantment",XCBToJsonArray.VectorToJsonArray(info.EnchantName, info.EnchantLevel));
				
					XCBFileOperate.writeToJson(json, jsondata, "XCustomizedBladeConfig", path);
				}
			}catch(NullPointerException e) {
				continue;
			}
		}
		this.readFromJson();
	}
	public boolean isExisted(String name) {
		for(int i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				if(name.equals(temp.get("BladeName").getAsString())) {
					System.out.println("XCC Warning:"+temp.get("BladeName").getAsString()+"~"+name+"is existed.");
					return true;
				}
			}catch(NullPointerException e) {
				continue;
			}
		}
		return false;
	}
	public int addToJson(XCDJsonDetailInfo info) {
		if(info.bladename==null||info.showName==null||this.json==null||info.bladeModel==null||info.bladeTexture==null) return 0;
		boolean isexisted=isExisted(info.bladename);
		System.out.println("XCC:Is blade existed?"+isexisted);
		if(isexisted==true) {
			 changeToJson(info,jsondata.size());
			 return -2;
		}else {
			JsonObject temp=new JsonObject();
			temp.addProperty("BladeName", info.bladename);
			temp.addProperty("BladeShowName", info.showName);
			temp.addProperty("BladeModel",info.bladeModel);
			temp.addProperty("BladeTexture",info.bladeTexture);
			temp.addProperty("BladeDamge",info.bladedamage);
			temp.addProperty("BladeDuration",info.bladeduration);
			temp.addProperty("BladeWitched",info.iswitched);
			temp.addProperty("BladeStandBy",info.standby);
			temp.addProperty("BladeSA", info.sa);
			temp.addProperty("SELevel",info.SELevel);
			temp.addProperty("BladeSE",info.SEName);
			temp.addProperty("SwordColor",info.color);
			JsonArray ret=XCBToJsonArray.VectorToJsonArray(info.EnchantName, info.EnchantLevel);
			if(ret!=null)temp.add("Enchantment",ret);
			
			this.jsondata.add(temp);
			int retNum= XCBFileOperate.writeToJson(json, jsondata, "XCustomizedBladeConfig", path);
			this.readFromJson();
			return retNum;
		}
	}
	public int deleteToJson(String name) {
		for(int i=0;i<datalen;i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				if(temp.get("BladeName").getAsString().equals(name)) {
					temp.remove("BladeShowName");temp.remove("BladeModel");
					temp.remove("BladeTexture");temp.remove("BladeDamge");
					temp.remove("BladeDuration");temp.remove("BladeWitched");
					temp.remove("BladeStandBy");temp.remove("BladeSA");
					temp.remove("SwordColor");temp.remove("BladeName");
					try {temp.remove("Enchantment");}catch(Exception e) {}
					try {temp.remove("SELevel");}catch(Exception e) {}
					try {temp.remove("BladeSE");}catch(Exception e) {}
					
					return XCBFileOperate.writeToJson(json, jsondata, "XCustomizedBladeConfig", path);
				}
			}catch(NullPointerException e) {
				continue;
			}
		}
		return 0;
	}

}
