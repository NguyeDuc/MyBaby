package dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import constants.Util;
import dbContext.DBUtil;
import models.ImageInfor;



public class ImageDAO {
	
	public void saveImage(String name, InputStream ins){			
		Connection conn = null;
		CallableStatement cs = null;
		String sql = "CALL pro_insert_saveNewImage(?,?)";
		try {
			conn = DBUtil.getConnection();
			cs = conn.prepareCall(sql);
			cs.setString(1, name);
			cs.setBlob(2, ins);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn, cs);
		}
	}
	
	
	public ImageInfor getImage(int opt, int id) {
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sql = "";
		
		Blob blobImg = null;
		ImageInfor imageInfor = null;
		try {
			conn = DBUtil.getConnection();
			
			if(opt == 1) {	// new image
				sql = "SELECT * FROM SaveImage WHERE Id = (SELECT MAX(Id) FROM SaveImage)";
				cs = conn.prepareCall(sql);
			}else {
				sql = "SELECT * FROM SaveImage WHERE Id = ?";
				cs = conn.prepareCall(sql);
				cs.setInt(0, id);
			}
			rs = cs.executeQuery();
			while(rs.next()) {
				String name = rs.getString("Name");	
				blobImg = rs.getBlob("Image");
				Date createDate = rs.getDate("Create_Date");
				String imgBabyBase64 = Util.getByteImage(blobImg);
                
				imageInfor = new ImageInfor(id,name,imgBabyBase64,createDate);
			}
			return imageInfor;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn, cs);
		}
		return null;
	}
	
	
//	public List<ImageInfor> getAllNewImage() {
//		Connection conn = null;
//		CallableStatement cs = null;
//		ResultSet rs = null;
//		String sql = "SELECT * FROM SaveImage";
//		Blob blobImg = null;
//		List<ImageInfor> listImage = null;
//		try {
//			listImage = new ArrayList<ImageInfor>();
//			conn = DBUtil.getConnection();
//			cs = conn.prepareCall(sql);
//			rs = cs.executeQuery();
//			while(rs.next()) {
//				int id = rs.getInt("Id");
//				String name = rs.getString("Name");	
//				blobImg = rs.getBlob("Image");
//				Date createDate = rs.getDate("Create_Date");
//				String imgBabyBase64 = Util.getByteImage(blobImg);
//                
//				listImage.add(new ImageInfor(id,name,imgBabyBase64,createDate));
//			}
//			return listImage;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DBUtil.closeConnection(conn, cs);
//		}
//		return null;
//		
//	}
	

}
