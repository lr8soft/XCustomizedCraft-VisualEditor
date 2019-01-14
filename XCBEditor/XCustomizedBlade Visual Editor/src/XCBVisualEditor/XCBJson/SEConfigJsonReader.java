package XCBVisualEditor.XCBJson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import XCBVisualEditor.XCDJsonSAInfo;
import XCBVisualEditor.XCDJsonSEInfo;
import XCBVisualEditor.XCBUtil.XCBToJsonArray;

public class SEConfigJsonReader {
	private JsonObject jsoninfo;
	private String jsonpath;
	public JsonArray jsondata;
	public boolean willSERun;
	private JsonArray tSEStep,tStepCount,tStepDamage;
	private String[] SEStep;
	private int[] StepCount;
	private float[] StepDamage;
	public SEConfigJsonReader(String path) {
		this.jsonpath=path;
		this.willSERun=true;
		JsonParser jp=new JsonParser();
		try {
			jsoninfo=(JsonObject)jp.parse(new FileReader(jsonpath));
			try {
				if(jsoninfo.get("XCustomizedBladeVER").getAsDouble()>=1.50) {
					jsondata=jsoninfo.get("XCustomizedSE").getAsJsonArray();
				}else {
					//this.willSERun=false;
					try {
						jsoninfo.get("XCustomizedSE").getAsJsonArray();
						JOptionPane.showMessageDialog(null, "配置文件存在自定义SE部分，若要加载请将配置文件版本改为1.50以上！");
					}catch(NullPointerException error) {
						jsoninfo.add("XCustomizedSE", new JsonArray());
					}
					JOptionPane.showMessageDialog(null, "配置文件版本低于1.50，自定义SE将不会被加载");
					this.willSERun=false;
				}
			}catch(Exception e) {this.willSERun=false;}
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			System.out.println("XCustomizedSE:Fail to load from config.");
			this.willSERun=false;
		}
	}
	public boolean isExisted(String name) {
		for(int i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				String tempName=temp.get("SEName").getAsString();
				if(tempName.equals(name)) {
					System.out.println(tempName+"~"+name+" is existed.");
					return true;
				}
			}catch(NullPointerException e) {
				continue;
			}
		}
		return false;
	}
	public int GetSECount() {
		int ret=0;
		for(int i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				temp.get("SEName").getAsString();
				ret++;
			}catch(NullPointerException error) {
				continue;
			}
		}
		return ret;
	}
	public XCDJsonSEInfo GetInfo(String name) {
		XCDJsonSEInfo ret = null;
		for(int i=0;i<jsondata.size();i++) {
			try {
				JsonObject tempobj=jsondata.get(i).getAsJsonObject();
				String sename=tempobj.get("SEName").getAsString();
				if(sename.equals(name)) {
					int cost=tempobj.get("SECost").getAsInt();
					int level=tempobj.get("SELevel").getAsInt();
					JsonArray step=tempobj.get("SEStep").getAsJsonArray();
					JsonArray runtime=tempobj.get("SERuntime").getAsJsonArray();
					JsonArray damage=tempobj.get("SEDamage").getAsJsonArray();
					String[] stepName=new String[step.size()];
					int[] stepRuntime=new int[step.size()];
					double[] stepDamage=new double[step.size()];
					for(int len=0;len<step.size();len++) {
						stepName[len]=step.get(len).getAsString();
						stepRuntime[len]=runtime.get(len).getAsInt();
						stepDamage[len]=damage.get(len).getAsDouble();
					}
					if(name==null||step==null||runtime==null||damage==null) continue;
					ret=new XCDJsonSEInfo(name,cost,level,stepName,stepRuntime,stepDamage);
				}
			}catch(Exception error) {
				System.out.println("XCustomizedSE:An error occur while loading.");
				error.printStackTrace();
				continue;
			}
		}
		return ret;
	}
	public int deleteToJson(String name) {
		if(name.equals(null)) return -1;
		for(int i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				if(temp.get("SEName").getAsString().equals(name)) {
					temp.remove("SEName");temp.remove("SECost");
					temp.remove("SELevel");temp.remove("SEStep");
					temp.remove("SERuntime");temp.remove("SEDamage");
					this.jsoninfo.remove("XCustomizedSE");
					this.jsoninfo.add("XCustomizedSE", jsondata);
					Gson out=new Gson();
					try {
						FileOutputStream output=new FileOutputStream(jsonpath);
						output.write(out.toJson(jsoninfo).getBytes());
						output.close();
					} catch (FileNotFoundException e) {
						System.out.println("XCC Error:"+e.getMessage());
						return -1;
					} catch (IOException e) {
						System.out.println("XCC Error:"+e.getMessage());
						return -1;
					}
					return 1;
				}
			}catch(NullPointerException e) {
				continue;
			}
		}
		return 0;
	}
	public int changeToJson(String name,int level,int cost,String[] stepName,int[] count,int[] damage) {
		System.out.println("XCC Info:Try to replace the old blade->"+name);
		for(int i=0;i<jsondata.size();i++) {
			JsonObject temp=jsondata.get(i).getAsJsonObject();
			try {
				if(temp.get("SEName").getAsString().equals(name)) {
					temp.remove("SEName");temp.addProperty("SEName",name);
					temp.remove("SECost");temp.addProperty("SECost", cost);
					temp.remove("SELevel");temp.addProperty("SELevel",level);
					temp.remove("SEStep");temp.add("SEStep", XCBToJsonArray.StringToJsonArray(stepName));
					temp.remove("SERuntime");temp.add("SERuntime", XCBToJsonArray.IntToJsonArray(count));
					temp.remove("SEDamage");temp.add("SEDamage", XCBToJsonArray.IntToJsonArray(damage));

					//this.jsondata.add(temp);
					this.jsoninfo.remove("XCustomizedSE");
					this.jsoninfo.add("XCustomizedSE", jsondata);
					Gson out=new Gson();
					try {
						FileOutputStream output=new FileOutputStream(jsonpath);
						output.write(out.toJson(jsoninfo).getBytes());
						output.close();
					} catch (FileNotFoundException e) {
						System.out.println("XCC Error:"+e.getMessage());
						return 0;
					} catch (IOException e) {
						System.out.println("XCC Error:"+e.getMessage());
						return 0;
					}
					return 2;
				}
			}catch(NullPointerException e) {
				continue;
			}
		}
		return 0;
	}
	public int addToJson(String name,int level,int cost,String[] stepName,int[] count,int[] damage) {
		boolean ret=isExisted(name);
		if(!ret) {
			JsonObject temp=new JsonObject();
			temp.addProperty("SEName", name);temp.addProperty("SELevel", level);
			temp.addProperty("SECost", cost);
			temp.add("SEStep", XCBToJsonArray.StringToJsonArray(stepName));
			temp.add("SERuntime", XCBToJsonArray.IntToJsonArray(count));
			temp.add("SEDamage", XCBToJsonArray.IntToJsonArray(damage));
			this.jsondata.add(temp);
			this.jsoninfo.add("XCustomizedSE", jsondata);
			Gson out=new Gson();
			try {
				FileOutputStream output=new FileOutputStream(jsonpath);
				output.write(out.toJson(jsoninfo).getBytes());
				output.close();
			} catch (FileNotFoundException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			} catch (IOException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			}
			return 1;
		}else {
			int rret=changeToJson(name,level,cost,stepName,count,damage);
			return rret;
		}
	}
}