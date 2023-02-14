package model;

public class DonHang {
	private String tenkh, diachi, sodienthoai, email, tentaikhoan;
	private int idmathang, id, soluong;
	private boolean trangthai;
	private java.sql.Date date;
	public String getTenkh() {
		return tenkh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTentaikhoan() {
		return tentaikhoan;
	}
	public void setTentaikhoan(String tentaikhoan) {
		this.tentaikhoan = tentaikhoan;
	}
	public int getIdmathang() {
		return idmathang;
	}
	public void setIdmathang(int idmathang) {
		this.idmathang = idmathang;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isTrangthai() {
		return trangthai;
	}
	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String chitiet() {
		return tenkh + diachi + sodienthoai + email + tentaikhoan +
				Integer.toString(id) + Integer.toString(idmathang) +
				Integer.toString(soluong) + date.toString() + Boolean.toString(trangthai);
	}
	

}
