package constants;

import java.io.File;

public class Util {

	public static void checkFolderExistAndCreate(int baby) {
		File file = null;
		if(baby == 0) {
			file = new File(Const.FILE_PATH_IMAGE);
		}else {
			file = new File(Const.FILE_IMAGE_BABY);
		}
        // if the directory does not exist, create it
        if (!file.exists()) {
            try{
            	file.mkdir();
            }catch(Exception e){
            	e.printStackTrace();
            }        
            
        }
	}
	
}
