package model;

import java.util.List;

public interface IDatabase {
	public List<Sach> getAll();
	public List<Sach> timkiem(String key);
	public boolean themSach(Sach sach);
	public boolean xoaSach(Sach sach);
	public boolean suaSach (Sach sach);
	public boolean themDonHang(DonHang donhang);
	public List<ThongKe> getThongke(int songay);
	public List<DonHang> getDonhangs();
	public boolean xuly(DonHang donhang);
	public boolean xoa(DonHang donhang);

}
