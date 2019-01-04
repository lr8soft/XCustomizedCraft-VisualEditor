package XCBVisualEditor.XCBJson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class SyncJsonReader {
	private String jsonpath;
	private JsonObject jsonfile,jsondata;
	public SyncJsonReader(String path) {
		this.jsonpath=path;
		JsonParser jp=new JsonParser();
		try {
			jsonfile=(JsonObject)jp.parse(new FileReader(jsonpath));
			jsondata=jsonfile.get("ServerInfo").getAsJsonObject();
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
		}
	}
	public String getServerAddress() {
		String address=null;
		if(jsondata!=null) {
			address=jsondata.get("ServerHostName").getAsString();
		}
		return address;
	}
	public int getServerPort() {
		int port=0;
		if(jsondata!=null) {
			port=jsondata.get("ServerPort").getAsInt();
		}
		return port;
	}
	public boolean getServerSync() {
		boolean sync=false;
		if(jsondata!=null) {
			sync=jsondata.get("SyncConfig").getAsBoolean();
		}
		return sync;
	}
	public int writeAddress(String address) {
		if(jsondata!=null) {
			jsondata.remove("ServerHostName");
			jsondata.addProperty("ServerHostName",address);
			this.jsonfile.remove("ServerInfo");
			this.jsonfile.add("ServerInfo", jsondata);
			Gson out=new Gson();
			try {
				FileOutputStream output=new FileOutputStream(jsonpath);
				output.write(out.toJson(jsonfile).getBytes());
				output.close();
				return 1;
			} catch (FileNotFoundException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			} catch (IOException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			}
		}
		return 0;
	}
	public int writePort(int address) {
		if(jsondata!=null) {
			jsondata.remove("ServerPort");
			jsondata.addProperty("ServerPort",address);
			this.jsonfile.remove("ServerInfo");
			this.jsonfile.add("ServerInfo", jsondata);
			Gson out=new Gson();
			try {
				FileOutputStream output=new FileOutputStream(jsonpath);
				output.write(out.toJson(jsonfile).getBytes());
				output.close();
				return 1;
			} catch (FileNotFoundException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			} catch (IOException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			}
		}
		return 0;
	}
	public int writeSync(boolean address) {
		if(jsondata!=null) {
			jsondata.remove("SyncConfig");
			jsondata.addProperty("SyncConfig",address);
			this.jsonfile.remove("ServerInfo");
			this.jsonfile.add("ServerInfo", jsondata);
			Gson out=new Gson();
			try {
				FileOutputStream output=new FileOutputStream(jsonpath);
				output.write(out.toJson(jsonfile).getBytes());
				output.close();
				return 1;
			} catch (FileNotFoundException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			} catch (IOException e) {
				System.out.println("XCC Error:"+e.getMessage());
				return 0;
			}
		}
		return 0;
	}
}