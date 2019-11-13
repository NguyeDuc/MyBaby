package api;

import java.io.File;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import connection.API;
import connection.LuxandAPI;
import constants.Const;
import constants.Util;

public class CallAPI {

	
	public static String callService(String nameImg1, int typeImg1, String nameImg2, int typeImg2, int gender, int skinBaby) {
		System.setProperty("http.proxyHost", "113.160.234.147");
        System.setProperty("http.proxyPort","56570");
        
        try {
            URL url = new URL( "https://memorynotfound.com/configure-http-proxy-settings-java/");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            System.out.println(connection.getResponseCode());
        } catch (Exception e) {
          e.printStackTrace();
        }
        API api = new LuxandAPI();
        System.out.println("Get UID and Cookie from Luxand");
        api.initiateConnection();
        File file1 = new File(Const.FILE_PATH_IMAGE + File.separator + nameImg1);
        System.out.println("Update image of partner 1");
        api.uploadImage(file1, typeImg1);
        File file2 = new File(Const.FILE_PATH_IMAGE + File.separator + nameImg2);
        System.out.println("Update image of partner 2");
        api.uploadImage(file2, typeImg2);
        //TODO use asynchronous technique to update 2 file
        
          /*  gender : 1 boy
                    0 girl
                    -1 either */
        System.out.println("make baby: ");
        String imageName = api.makeBaby(gender, skinBaby);
        System.out.println("url baby: "+imageName);
        Util.checkFolderExistAndCreate(Const.IMAGE_BABY);
        String fileOuputPath = Const.FILE_IMAGE_BABY + imageName;
        System.out.println("save image "+imageName+" to "+fileOuputPath);
        api.saveImage(imageName, fileOuputPath);
        return imageName;
	}
	
}
