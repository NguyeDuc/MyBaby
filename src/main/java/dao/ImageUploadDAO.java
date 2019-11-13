package dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constants.Util;
import dbContext.DBUtil;
import models.ImageInfor;
import models.ImageUpload;

public class ImageUploadDAO {

	
	public void insertImageUpload(String name, InputStream imgUp, InputStream imgUp2) {
		Connection conn = null;
		CallableStatement cs = null;
		String sql = "CALL pro_insert_insertImgUpload(?,?,?)";
		try {
			conn = DBUtil.getConnection();
			cs = conn.prepareCall(sql);
			cs.setString(1, name);
			cs.setBlob(2, imgUp);
			cs.setBlob(3, imgUp2);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn, cs);
		}
	}
	
	public List<ImageUpload> getListMakeBaby() {
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sql = "CALL getListMakeBaby()";
		Blob blobBaby = null;
		List<ImageUpload> listImgUp = null;
		try {
			listImgUp = new ArrayList<ImageUpload>();
			conn = DBUtil.getConnection();
			cs = conn.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Baby_Name");
				blobBaby = rs.getBlob("Baby_Image");
				String imgBabyBase64 = Util.getByteImage(blobBaby);
				Blob blobImgUp = rs.getBlob("Image_Upload");
				Blob blobImgUp2 = rs.getBlob("Image_Upload_2");
				String imgUpBase64 = Util.getByteImage(blobImgUp);
				String imgUpBase64_2 = Util.getByteImage(blobImgUp2); 
				Timestamp date = rs.getTimestamp("Create_Date");
				listImgUp.add(new ImageUpload(id, name, imgBabyBase64, imgUpBase64, imgUpBase64_2, date));
			}
			return listImgUp;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn, cs);
		}
		return null;
		
	}
	public ImageUpload getNewImageBaby(int opt, int id) {
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sqlSelect = "SELECT si.`Id`, si.`Name` AS Baby_Name, si.`Image` AS Baby_Image, si.`Create_Date`,"
								+ " iu.`Image_Upload`, iu.`Image_Upload_2`" + 
							" FROM SaveImage AS si" + 
							" INNER JOIN ImageUpload AS iu ON si.`Id` = iu.`Id_Image_Baby`";
		String where_opt_1 = " WHERE si.`Id` = ?";									// ajax
		String where_opt_2 = " WHERE si.`Id` = (SELECT MAX(Id) FROM SaveImage)";	// new
		String sql = "";
		Blob blobBaby = null;
		ImageUpload makeBaby = null;
		try {
			conn = DBUtil.getConnection();
			if(opt == 1) {
				sql = sqlSelect.concat(where_opt_1);
				cs = conn.prepareCall(sql);
				cs.setInt(0, id);
			} else {
				sql = sqlSelect.concat(where_opt_2);
				cs = conn.prepareCall(sql);
			}
			
			rs = cs.executeQuery();
			while(rs.next()) {
				int idBaby = rs.getInt("Id");
				String name = rs.getString("Baby_Name");
				blobBaby = rs.getBlob("Baby_Image");
				String imgBabyBase64 = Util.getByteImage(blobBaby);
				Blob blobImgUp = rs.getBlob("Image_Upload");
				Blob blobImgUp2 = rs.getBlob("Image_Upload_2");
				String imgUpBase64 = Util.getByteImage(blobImgUp);
				String imgUpBase64_2 = Util.getByteImage(blobImgUp2);
				Timestamp date = rs.getTimestamp("Create_Date");
				makeBaby = new ImageUpload(idBaby, name, imgBabyBase64, imgUpBase64, imgUpBase64_2, date);
			}
			return makeBaby;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn, cs);
		}
		return null;
		
	}
	
}
