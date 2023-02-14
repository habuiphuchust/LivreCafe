package model;

import javafx.scene.image.ImageView;

public class Sach {
	private ImageView image;
	private String tensach = "", tacgia = "", theloai= "", anhbia = "", motathem="", nhaxuatban="";
	private int gia=0, namxuatban=0, id, daban=0;
	private boolean tinhtrang=true;
	
	public void setImage(ImageView image) {
		this.image = image;
	}
	public void setTensach(String tensach) {
		this.tensach = tensach;
	}
	public void setTacgia(String tacgia) {
		this.tacgia = tacgia;
	}
	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}
	public void setAnhbia(String anhbia) {
		this.anhbia = anhbia;
	}
	public void setMotathem(String motathem) {
		this.motathem = motathem;
	}
	public void setNhaxuatban(String nhaxuatban) {
		this.nhaxuatban = nhaxuatban;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public void setNamxuatban(int namxuatban) {
		this.namxuatban = namxuatban;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTinhtrang(boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
	public ImageView getImage() {
		return image;
	}
	public String getTensach() {
		return tensach;
	}
	public String getTacgia() {
		return tacgia;
	}
	public String getTheloai() {
		return theloai;
	}
	public String getAnhbia() {
		return anhbia;
	}
	public String getMotathem() {
		return motathem;
	}
	public String getNhaxuatban() {
		return nhaxuatban;
	}
	public int getGia() {
		return gia;
	}
	public int getNamxuatban() {
		return namxuatban;
	}
	public int getId() {
		return id;
	}
	public boolean isTinhtrang() {
		return tinhtrang;
	}
	public int getDaban() {
		return daban;
	}
	public void setDaban(int daban) {
		this.daban = daban;
	}
	public String chitiet () {
		return tensach + tacgia + theloai + motathem + nhaxuatban +
				Integer.toString(gia) + Integer.toString(id) +
				Integer.toString(namxuatban) + Integer.toString(daban);
	}
	
	
}