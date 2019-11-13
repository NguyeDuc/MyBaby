package dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import dbContext.DBUtil;
import models.ImageInfor;



public class ImageDAO {
	
	@SuppressWarnings("null")
	public void saveImage(String name, InputStream ins){			
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO SaveImage VALUES(NULL,?,?)";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setBlob(2, ins);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ImageInfor getImage() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SaveImage WHERE Id = (SELECT MAX(Id) FROM SaveImage)";
		Blob blob = null;
		ImageInfor imageInfor = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				blob = rs.getBlob("Image");
				InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
				imageInfor = new ImageInfor(id, name, blob, base64Image);
			}
			return imageInfor;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
