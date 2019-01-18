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

public class InitConfigJson {
	private String path;
	private JsonObject jsoninfo;
	public InitConfigJson(String input) {
		this.path=input;
		JsonParser jp=new JsonParser();
		try {
			jsoninfo=(JsonObject)jp.parse(new FileReader(path));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "配置文件读取失败！\n"+e.getMessage());
			jsoninfo=null;
		}
	}
	public void BladeInit() {
		if(jsoninfo!=null) {
			//jsondata=jsoninfo.get("XCustomizedBladeConfig").getAsJsonArray();
			JsonArray temp=new JsonArray();
			try {
				try {jsoninfo.remove("XCustomizedBladeConfig");}catch(Exception unerror) {}
				jsoninfo.add("XCustomizedBladeConfig", temp);
				boolean ret=FileWriter();
				if(ret)
					JOptionPane.showMessageDialog(null, "拔刀剑配置已初始化");
				else
					JOptionPane.showMessageDialog(null, "写入配置时发生错误！");
			}catch(Exception error) {
				JOptionPane.showMessageDialog(null, "拔刀剑配置初始化失败！\n"+error.getMessage());
			}
		}
	}
	public void SAInit() {
		if(jsoninfo!=null) {
			JsonArray temp=new JsonArray();
			try {
				try {jsoninfo.remove("XCustomizedSA");}catch(Exception unerror) {}
				jsoninfo.add("XCustomizedSA", temp);
				boolean ret=FileWriter();
				if(ret)
					JOptionPane.showMessageDialog(null, "SA配置已初始化");
				else
					JOptionPane.showMessageDialog(null, "写入配置时发生错误！");
			}catch(Exception error) {
				JOptionPane.showMessageDialog(null, "SA配置初始化失败！\n"+error.getMessage());
			}
		}
	}
	public void SEInit() {
		if(jsoninfo!=null) {
			JsonArray temp=new JsonArray();
			try {
				try {jsoninfo.remove("XCustomizedSE");}catch(Exception unerror) {}
				jsoninfo.add("XCustomizedSE", temp);
				boolean ret=FileWriter();
				if(ret)
					JOptionPane.showMessageDialog(null, "SE配置已初始化");
				else
					JOptionPane.showMessageDialog(null, "写入配置时发生错误！");
			}catch(Exception error) {
				JOptionPane.showMessageDialog(null, "SE配置初始化失败！\n"+error.getMessage());
			}
		}
	}
	public void VerInit(double version) {
		if(jsoninfo!=null) {
			try {
				jsoninfo.remove("XCustomizedBladeVER");
				jsoninfo.addProperty("XCustomizedBladeVER", version);
				boolean ret=FileWriter();
				if(ret)
					JOptionPane.showMessageDialog(null, "适配版本号已更新");
				else
					JOptionPane.showMessageDialog(null, "写入配置时发生错误！");
			}catch(Exception error) {
				JOptionPane.showMessageDialog(null, "版本号更新失败！\n"+error.getMessage());
			}
		}
	}
	private boolean FileWriter() {
		Gson out=new Gson();
		try {
			FileOutputStream output=new FileOutputStream(path);
			output.write(out.toJson(this.jsoninfo).getBytes());
			output.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("XCC Error:"+e.getMessage());
		} catch (IOException e) {
			System.out.println("XCC Error:"+e.getMessage());
		}
		return false;
	}
}
