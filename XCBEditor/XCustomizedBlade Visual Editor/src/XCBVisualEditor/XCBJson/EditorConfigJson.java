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

import XCBVisualEditor.XCBUtil.XCBFileOperate;
import XCBVisualEditor.XCBUtil.XCBGetPath;

public class EditorConfigJson {
	public JsonObject jsoninfo;
	private String jsonpath;
	private JsonArray configinfo;
	private String InitFileInfo="{\r\n" + 
								"  \"ConfigPath\": []\r\n" + 
								"}";
	public EditorConfigJson() {
		JsonParser jp=new JsonParser();
		jsonpath=XCBGetPath.getNowPath()+"/XCB-VE.config";
		try {
			jsoninfo=(JsonObject)jp.parse(new FileReader(jsonpath));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			ConfigInit();
			try {
				jsoninfo=(JsonObject)jp.parse(new FileReader(jsonpath));
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e2) {
				jsoninfo=null;
			}
		}
	}
	public String[] GetConfigList() {
		String[] info=null;
		if(jsoninfo!=null) {
			try {
				configinfo=jsoninfo.get("ConfigPath").getAsJsonArray();
				int configLen=configinfo.size();
				info=new String[configLen];
				for(int i=0;i<configLen;i++) {
					info[i]=configinfo.get(i).getAsString();
				}
			}catch(Exception error){}
		}
		return info;
	}
	public void AddToConfig(String info) {
		if(jsoninfo!=null) {
			if(isExisted(info)) return;
			configinfo.add(info);
			XCBFileOperate.writeToJson(jsoninfo, configinfo, "ConfigPath", jsonpath);
		}
	}
	private boolean isExisted(String name) {
		if(jsoninfo==null) return false;
		for(int i=0;i<configinfo.size();i++) {
			String temp=configinfo.get(i).getAsString();
			if(name.equals(temp)) {
				return true;
			}
		}
		return false;
	}
	private void ConfigInit() {
		try {
			System.out.println("XCC-VE:Now setting the first time running environment.");
			FileOutputStream out=new FileOutputStream(jsonpath);
			 out.write(this.InitFileInfo.getBytes());
			 out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
