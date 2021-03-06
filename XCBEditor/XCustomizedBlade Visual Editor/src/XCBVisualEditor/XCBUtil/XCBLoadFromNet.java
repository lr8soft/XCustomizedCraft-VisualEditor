package XCBVisualEditor.XCBUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;

public class XCBLoadFromNet {
	 public static String openFile(String filePath) {
	        int HttpResult; // 服务器返回的状态
	        String ee = new String();
	        try
	        {
	            URL url =new URL(filePath); // 创建URL
	            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
	            urlconn.connect();
	            HttpURLConnection httpconn =(HttpURLConnection)urlconn;
	            HttpResult = httpconn.getResponseCode();
	            if(HttpResult != HttpURLConnection.HTTP_OK) {
	            	JOptionPane.showMessageDialog(null, "无法连接到服务器！");
	            } else {
	                int filesize = urlconn.getContentLength(); // 取数据长度
	                InputStreamReader isReader = new InputStreamReader(urlconn.getInputStream());
	                BufferedReader reader = new BufferedReader(isReader);
	                StringBuffer buffer = new StringBuffer();
	                String line; // 用来保存每行读取的内容
	                line = reader.readLine(); // 读取第一行
	                while (line != null) { // 如果 line 为空说明读完了
	                	buffer.append(line);
	                    buffer.append(" "); // 添加换行符
	                    line = reader.readLine(); // 读取下一行
	                }
	                System.out.print(buffer.toString());
	                ee = buffer.toString();
	            }
	        }
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        return  ee;
	    }
}
