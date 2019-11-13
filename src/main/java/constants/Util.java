package constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

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
	
	
	public static String getByteImage(Blob blob) {
		String base64Image = null;
		InputStream inputStream;
		try {
			inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	         
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);                  
	        }
	         
	        byte[] imageBytes = outputStream.toByteArray();
	        base64Image = Base64.getEncoder().encodeToString(imageBytes);
	        inputStream.close();
            outputStream.close();
		}catch(IOException io) {
			io.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return base64Image;
	}
	
}
