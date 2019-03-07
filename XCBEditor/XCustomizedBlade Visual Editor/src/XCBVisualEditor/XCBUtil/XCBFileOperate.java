package XCBVisualEditor.XCBUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class XCBFileOperate {
	public static int writeToJson(JsonObject jsonfile,JsonArray newinfo,String objectName,String jsonPath) {
		jsonfile.remove(objectName);
		jsonfile.add(objectName, newinfo);
		Gson out=new Gson();
		try {
			FileOutputStream output=new FileOutputStream(jsonPath);
			output.write(out.toJson(jsonfile).getBytes());
			output.close();
			return 1;
		} catch (FileNotFoundException e) {
			System.out.println("XCC File Error:"+e.getMessage());
			return -1;
		} catch (IOException e) {
			System.out.println("XCC File Error:"+e.getMessage());
			return -1;
		}
	}
	public static int writeObjectToJson(JsonObject jsonfile,JsonObject newinfo,String objectName,String jsonPath) {
		jsonfile.remove(objectName);
		jsonfile.add(objectName, newinfo);
		Gson out=new Gson();
		try {
			FileOutputStream output=new FileOutputStream(jsonPath);
			output.write(out.toJson(jsonfile).getBytes());
			output.close();
			return 1;
		} catch (FileNotFoundException e) {
			System.out.println("XCC File Error:"+e.getMessage());
			return -1;
		} catch (IOException e) {
			System.out.println("XCC File Error:"+e.getMessage());
			return -1;
		}
	}
	public static int writeBooleanToJson(JsonObject jsonfile,boolean newinfo,String objectName,String jsonPath) {
		jsonfile.remove(objectName);
		jsonfile.addProperty(objectName, newinfo);
		Gson out=new Gson();
		try {
			FileOutputStream output=new FileOutputStream(jsonPath);
			output.write(out.toJson(jsonfile).getBytes());
			output.close();
			return 1;
		} catch (FileNotFoundException e) {
			System.out.println("XCC File Error:"+e.getMessage());
			return 0;
		} catch (IOException e) {
			System.out.println("XCC File Error:"+e.getMessage());
			return 0;
		}
	}
}
