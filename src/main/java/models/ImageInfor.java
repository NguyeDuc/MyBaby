package models;

import java.util.Date;

public class ImageInfor {

	private int id;
	private String name;
	private String imageBase64;
	private Date createDate;
	
	public ImageInfor(int id, String name, String imageBase64, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.imageBase64 = imageBase64;
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
	public String getImageBase64() {
		return imageBase64;
	}
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
