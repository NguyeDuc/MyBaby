package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImageDAO;
import models.ImageInfor;

/**
 * Servlet implementation class LoadImage
 */
public class LoadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpg");
		try {
//			ImageDAO dao = new ImageDAO();
//			ImageInfor img = dao.getImage();
//			request.setAttribute("img", img);
//			response.getWriter().append("Served at: ").append(request.getContextPath());
//			request.getRequestDispatcher("view/result.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
