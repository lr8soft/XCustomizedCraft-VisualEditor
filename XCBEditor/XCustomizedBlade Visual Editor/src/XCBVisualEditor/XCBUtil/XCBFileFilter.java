package XCBVisualEditor.XCBUtil;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XCBFileFilter extends FileFilter {  
	@Override
    public boolean accept(File file) {  
        if(file.isDirectory())  
            return true;  
        else  
        {  
            String name = file.getName();  
            if(name.endsWith(".json") || name.endsWith("XCustomizedBlade.json"))  
                return true;  
            else  
                return false;  
        }  
          
    }
	@Override
	public String getDescription() {
		return null;
	}  
  
}  
