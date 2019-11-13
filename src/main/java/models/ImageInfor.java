package models;

import java.sql.Blob;

public class ImageInfor {

	private int id;
	private String name;
	private Blob image;
	private String imageBase64;
	
	public ImageInfor(int id, String name, Blob image, String imageBase64) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.imageBase64 = imageBase64;
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
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getImageBase64() {
		return imageBase64;
	}
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
	
}
