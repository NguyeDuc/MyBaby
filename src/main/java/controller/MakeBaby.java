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
import models.ImageInfor;


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
//			String skin = request.getParameter("skin");
//            String gender = request.getParameter("gender");
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
		            Util.checkFolderExistAndCreate(Const.IMAGE_UPLOAD); // create folder
		            for(FileItem item : listImage){
		                if(!item.isFormField()){
		                    String name = new File(item.getName()).getName();
		                    item.write( new File(Const.FILE_PATH_IMAGE + File.separator + name));
		                }
		            }
		            String imageBaby = CallAPI.callService(imgUpload1, 1, imgUpload2, 2, Integer.parseInt(gender), Integer.parseInt(skin));
		            
		            //System.out.println(Const.FILE_IMAGE_BABY + imageBaby+"Baaaaaaaa");
		            ImageDAO dao = new ImageDAO();
		            InputStream ins = new FileInputStream(Const.FILE_IMAGE_BABY + imageBaby);
		            dao.saveImage("Baby", ins);
		            
//		            request.setAttribute("urlImgBaby", Const.FILE_IMAGE_BABY + imageBaby);
//		            request.setAttribute("urlImgBaby2", "./image" + imageBaby);
//		            request.getRequestDispatcher("views/result.jsp").forward(request, response);
		            response.setContentType("image/jpg");
		            dao = new ImageDAO();
					ImageInfor img = dao.getImage();
					request.setAttribute("imgBaby", img.getImageBase64());
					request.setAttribute("nameBaby", img.getName());
					request.getRequestDispatcher("views/result.jsp").forward(request, response);
//		            //File uploaded successfully
//		            response.sendRedirect("LoadImage");
		            } catch (Exception ex) {
		            request.setAttribute("urlImgBaby", "File Upload Failed due to " + ex);
	            } 
	          
	        }else{
	            request.setAttribute("message",
	                                 "Sorry this Servlet only handles file upload request");
	        }
//	        ImageDAO dao = new ImageDAO();
//	        response.setContentType("image/jpg");
//			ImageInfor img = dao.getImage();
//			request.setAttribute("imgBaby", img.getImageBase64());
//			request.getRequestDispatcher("views/result.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
