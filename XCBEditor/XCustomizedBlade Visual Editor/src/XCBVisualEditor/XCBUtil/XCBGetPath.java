package XCBVisualEditor.XCBUtil;

import java.io.File;
import java.io.IOException;

public class XCBGetPath {
	 public static String getNowPath() {
			File directory = new File("");
			String courseFile=null;
			try {
				courseFile = directory.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return courseFile;
	 }

}
