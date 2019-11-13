package models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

public class ImageUpload {

	private int id;
	private String name;
//	private Blob imgBaby;
	private String imgBabyBase64;
//	private Blob imgUp1;
	private String imgUp1Base64;
//	private Blob imgUp2;
	private String imgUp2Base64;
	private Timestamp createDate;
	
	public ImageUpload(int id, String name, String imgBabyBase64, 
			String imgUp1Base64, String imgUp2Base64, Timestamp createDate) {
		super();
		this.id = id;
		this.name = name;
		this.imgBabyBase64 = imgBabyBase64;
		this.imgUp1Base64 = imgUp1Base64;
		this.imgUp2Base64 = imgUp2Base64;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgBabyBase64() {
		return imgBabyBase64;
	}

	public void setImgBabyBase64(String imgBabyBase64) {
		this.imgBabyBase64 = imgBabyBase64;
	}

	public String getImgUp1Base64() {
		return imgUp1Base64;
	}

	public void setImgUp1Base64(String imgUp1Base64) {
		this.imgUp1Base64 = imgUp1Base64;
	}

	public String getImgUp2Base64() {
		return imgUp2Base64;
	}

	public void setImgUp2Base64(String imgUp2Base64) {
		this.imgUp2Base64 = imgUp2Base64;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
	
	
	
	
	
}
