package XCBVisualEditor.XCBUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class XCBToJsonArray {
	public static JsonArray StringToJsonArray(String[] input) {
		JsonArray returnArray=null;
		String head="{\r\n" + 
				"  \"temp\":[";
		String tail="]\r\n" + 
				"}";
		try {
			FileOutputStream output=new FileOutputStream(XCBGetPath.getNowPath()+"/XCBVE_Temp.json");
			output.write(head.getBytes());
			for(int i=0;i<input.length;i++) {
				if(i<input.length-1) {
					output.write(input[i].getBytes());
					output.write(",".getBytes());
				}else {
					output.write(input[i].getBytes());
				}
			}
			output.write(tail.getBytes());
			output.close();
			JsonParser jp=new JsonParser();
			JsonObject json=(JsonObject)jp.parse(new FileReader(XCBGetPath.getNowPath()+"/XCBVE_Temp.json"));
			returnArray=json.get("temp").getAsJsonArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnArray;
	}
	public static JsonArray IntToJsonArray(int[] input) {
		JsonArray returnArray=null;
		String head="{\r\n" + 
				"  \"temp\":[";
		String tail="]\r\n" + 
				"}";
		try {
			FileOutputStream output=new FileOutputStream(XCBGetPath.getNowPath()+"/XCBVE_Temp.json");
			output.write(head.getBytes());
			for(int i=0;i<input.length;i++) {
				if(i<input.length-1) {
					output.write(String.valueOf(input[i]).getBytes());
					output.write(",".getBytes());
				}else {
					output.write(String.valueOf(input[i]).getBytes());
				}
			}
			output.write(tail.getBytes());
			output.close();
			JsonParser jp=new JsonParser();
			JsonObject json=(JsonObject)jp.parse(new FileReader(XCBGetPath.getNowPath()+"/XCBVE_Temp.json"));
			returnArray=json.get("temp").getAsJsonArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnArray;
	}
	public static JsonArray FloatToJsonArray(float[] input) {
		JsonArray returnArray=null;
		String head="{\r\n" + 
				"  \"temp\":[";
		String tail="]\r\n" + 
				"}";
		try {
			FileOutputStream output=new FileOutputStream(XCBGetPath.getNowPath()+"/XCBVE_Temp.json");
			output.write(head.getBytes());
			for(int i=0;i<input.length;i++) {
				if(i<input.length-1) {
					output.write(String.valueOf(input[i]).getBytes());
					output.write(",".getBytes());
				}else {
					output.write(String.valueOf(input[i]).getBytes());
				}
			}
			output.write(tail.getBytes());
			output.close();
			JsonParser jp=new JsonParser();
			JsonObject json=(JsonObject)jp.parse(new FileReader(XCBGetPath.getNowPath()+"/XCBVE_Temp.json"));
			returnArray=json.get("temp").getAsJsonArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnArray;
	}
	public static JsonArray VectorToJsonArray(Vector vec0,Vector vec1) {
		JsonArray returnArray=null;
		String head="{\r\n" + 
				"  \"temp\":[";
		String tail="]\r\n" + 
				"}";
		String format=new String();
		for(int i=0;i<vec0.size();i++) {
			format+="\"";
			format+=vec0.get(i);
			format+="\"";
			format+=",";
			format+=String.valueOf(vec1.get(i));
			if(i<vec0.size()-1) format+=",";
		}
		try {
			FileOutputStream output=new FileOutputStream(XCBGetPath.getNowPath()+"/XCBVE_Temp.json");
			output.write(head.getBytes());
			output.write(format.getBytes());
			output.write(tail.getBytes());
			output.close();
			JsonParser jp=new JsonParser();
			JsonObject json=(JsonObject)jp.parse(new FileReader(XCBGetPath.getNowPath()+"/XCBVE_Temp.json"));
			returnArray=json.get("temp").getAsJsonArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnArray;
	}
	public static Vector JsonArrayToVector(JsonArray info,boolean isString) {
		Vector vec=new Vector();
		if(info==null) {
			System.out.println("XCC JsonArray->Vector:NULL data.");
			vec=null;
			return vec;
		}else {
			System.out.println("XCC JsonArray->Vector:Translate start.");
			for(int i=0;i<info.size();i++) {
				if(isString) {
					if(i%2==0) {
						vec.add(info.get(i).getAsString());
					}
				}else {
					if(i%2!=0) {
						vec.add(info.get(i).getAsInt());
					}
				}
			}
		}
		return vec;
	}
}
