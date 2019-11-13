package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import api.CallAPI;
import constants.Const;
import constants.Util;
import dao.ImageDAO;
import dao.ImageUploadDAO;
import models.ImageInfor;
import models.ImageUpload;


public class MakeBaby extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public MakeBaby() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	        if(ServletFileUpload.isMultipartContent(request)){
	        	try {
		        	FileItemFactory itemFactory = new DiskFileItemFactory();
		        	ServletFileUpload upload = new ServletFileUpload(itemFactory);
		            @SuppressWarnings("unchecked")
					List<FileItem> listImage = upload.parseRequest(request);
		            String imgUpload1 = new File(listImage.get(0).getName()).getName();
		            String imgUpload2 = new File(listImage.get(1).getName()).getName();
		            String gender = listImage.get(2).getString();
		            String skin = listImage.get(3).getString();
		            String babyName = listImage.get(4).getString();
		            Util.checkFolderExistAndCreate(Const.IMAGE_UPLOAD); // create folder
		            for(FileItem item : listImage){
		                if(!item.isFormField()){
		                    String name = new File(item.getName()).getName();
		                    item.write( new File(Const.FILE_PATH_IMAGE + File.separator + name));
		                }
		            }
		            String imageBaby = CallAPI.callService(imgUpload1, 1, imgUpload2, 2, Integer.parseInt(gender), Integer.parseInt(skin));
		            
		            ImageDAO imgDao = new ImageDAO();	
		            ImageUploadDAO imgUpDao = new ImageUploadDAO();
		            InputStream ins = new FileInputStream(Const.FILE_IMAGE_BABY + imageBaby);        	// insert new image    
		            imgDao.saveImage(babyName, ins);
		            ins = new FileInputStream(Const.FILE_PATH_IMAGE + "\\" + imgUpload1);				// insert imgUp1
		            InputStream ins2 = new FileInputStream(Const.FILE_PATH_IMAGE + "\\" + imgUpload2);			// insert imgUp2
		            imgUpDao.insertImageUpload("Duc", ins, ins2);
		            imgDao = new ImageDAO();
//					ImageInfor img = imgDao.getImage(1, 1);		// new image
					List<ImageUpload> listImgUp = imgUpDao.getListMakeBaby();		// list img up
//					List<ImageInfor> listImgNew = imgDao.getAllNewImage();
					ImageUpload imgUpload = imgUpDao.getNewImageBaby(2, 11);		// get lastest image
					request.setAttribute("imgUp1", imgUpload.getImgUp1Base64());
					request.setAttribute("imgUp2", imgUpload.getImgUp2Base64());
					request.setAttribute("imgBaby", imgUpload.getImgBabyBase64());
					request.setAttribute("nameBaby", imgUpload.getName());
					request.setAttribute("createDate", imgUpload.getCreateDate());
					request.setAttribute("listImgNew", listImgUp);
					
					request.getRequestDispatcher("views/result.jsp").forward(request, response);
		            } catch (Exception ex) {
		            request.setAttribute("messUpImg", "File Upload Failed due to " + ex);
		            ex.printStackTrace();
		            request.getRequestDispatcher("views/result.jsp").forward(request, response);
	            } 
	          
	        }else{
	            request.setAttribute("message",
	                                 "Sorry this Servlet only handles file upload request");
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
