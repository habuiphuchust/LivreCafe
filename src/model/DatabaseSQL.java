package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DatabaseSQL implements IDatabase {
	 private String DB_URL = "jdbc:sqlserver://localhost:1433;"
	            + "databaseName=QL_Sach;";
	    private String USER_NAME = "sa";
	    private String PASSWORD = "123";
	    private List<Sach> listBooks;
	 
	public Connection getConnection(String dbURL, String userName, 
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
	public List<Sach> getData(String s) {
		// TODO Auto-generated method stub
		listBooks = new ArrayList<>();
		 try {
	            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(s);
	            while (rs.next()) {
	                Sach book = new Sach();
	                book.setId(rs.getInt("ID"));
	                book.setAnhbia(rs.getString("ANHBIA"));
	                book.setGia(rs.getInt("GIA"));
	                book.setMotathem(rs.getString("MOTATHEM"));
	                book.setNamxuatban(rs.getInt("NAMXUATBAN"));
	                book.setTacgia(rs.getString("TACGIA"));
	                book.setTensach(rs.getString("TENSACH"));
	                book.setTheloai(rs.getString("THELOAI"));
	                book.setTinhtrang(rs.getBoolean("TINHTRANG"));
	                book.setNhaxuatban(rs.getString("NHAXUATBAN"));
	                book.setDaban(rs.getInt("DABAN"));
	                String url = rs.getString("ANHBIA");
	                
	                if (url != null) {
	                	try {
	                		Image img = new Image(url, 150, 130, false, true);
	                		ImageView imgv = new ImageView(img);
//	                		imgv.setFitHeight(100);
//	                		imgv.setFitWidth(130);
	    	                book.setImage(imgv);
						} catch ( Exception e) {
							System.out.println(Integer.toString(book.getId()) + ": "+book.getTensach()+ "  " + "lỗi load ảnh: "+ url );
						}
	                }
	                listBooks.add(book);
	            }
	            conn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }

		return listBooks;
	}
	@Override
	public List<Sach> getAll() {
		// TODO Auto-generated method stub
		return getData("select * from SACH");
	}
	@Override
	public List<Sach> timkiem(String value) {
		// TODO Auto-generated method stub
		String keysearch = "N'%" + value + "%'";
		String cmd = "select * from SACH where TENSACH like " + keysearch + 
				"or TACGIA like " + keysearch + " or THELOAI like " + keysearch +
				"or NHAXUATBAN like " + keysearch + " or ID like " + keysearch +
				"or MOTATHEM like " + keysearch + " or NAMXUATBAN like " + keysearch;
				
		
		return getData(cmd);
	}
	@Override
	public boolean themSach(Sach sach) {
		// TODO Auto-generated method stub
        Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into SACH (TENSACH, GIA, TACGIA, NHAXUATBAN, NAMXUATBAN, THELOAI, TINHTRANG, DABAN, MOTATHEM, ANHBIA) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sach.getTensach());
			stmt.setInt(2, sach.getGia());
			stmt.setString(3, sach.getTacgia());
			stmt.setString(4, sach.getNhaxuatban());
			stmt.setInt(5, sach.getNamxuatban());
			stmt.setString(6, sach.getTheloai());
			stmt.setBoolean(7, sach.isTinhtrang());
			stmt.setInt(8, 0);
			stmt.setString(9, sach.getMotathem());
			stmt.setString(10, sach.getAnhbia());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean xoaSach(Sach sach) {
		// TODO Auto-generated method stub
        Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("delete from SACH where ID=?");
	        stmt.setInt(1, sach.getId());
	        stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean suaSach(Sach sach) {
		// TODO Auto-generated method stub
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		try {
			PreparedStatement stmt = conn.prepareStatement("update SACH set TENSACH=?, GIA=?, TACGIA=?, NHAXUATBAN=?, NAMXUATBAN=?, THELOAI=?, TINHTRANG=?, MOTATHEM=?, ANHBIA=? where ID=?");
			stmt.setString(1, sach.getTensach());
			stmt.setInt(2, sach.getGia());
			stmt.setString(3, sach.getTacgia());
			stmt.setString(4, sach.getNhaxuatban());
			stmt.setInt(5, sach.getNamxuatban());
			stmt.setString(6, sach.getTheloai());
			stmt.setBoolean(7, sach.isTinhtrang());
			stmt.setString(8, sach.getMotathem());
			stmt.setString(9, sach.getAnhbia());
			stmt.setInt(10, sach.getId());
			stmt.execute();
			System.out.println(sach.getId());
			System.out.println(sach.getTensach());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean themDonHang(DonHang donhang) {
		// TODO Auto-generated method stub
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into DONHANG (TENKH, DIACHI, SODIENTHOAI, EMAIL, SOLUONG, TRANGTHAI, TENTAIKHOAN, NGAYMUA, IDMATHANG) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, donhang.getTenkh());
			stmt.setString(2, donhang.getDiachi());
			stmt.setString(3, donhang.getSodienthoai());
			stmt.setString(4, donhang.getEmail());
			stmt.setInt(5, donhang.getSoluong());
			stmt.setBoolean(6, donhang.isTrangthai());
			stmt.setString(7, donhang.getTentaikhoan());
			stmt.setDate(8, donhang.getDate());
			stmt.setInt(9, donhang.getIdmathang());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public List<ThongKe> getThongke(int songay) {
		// TODO Auto-generated method stub
		List<ThongKe> tks = new ArrayList<>();
        Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		try {
			PreparedStatement stmt = conn.prepareStatement("select top "+Integer.toString(songay)+" NGAYMUA, SUM(GIA*SOLUONG) from SACH inner join DONHANG on SACH.ID=DONHANG.IDMATHANG group by NGAYMUA order by NGAYMUA DESC");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ThongKe tk = new ThongKe();
				tk.setDate(new Date(172800000 + rs.getDate(1).getTime()));
				tk.setDoanhthu(rs.getInt(2));
				tks.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tks;
	}
	@Override
	public List<DonHang> getDonhangs() {
		// TODO Auto-generated method stub
		List<DonHang> list = new ArrayList<>();
		try {
			Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from DONHANG");
            while (rs.next()) {
            	DonHang donhang = new DonHang();
            	donhang.setTenkh(rs.getString("TENKH"));
            	donhang.setDiachi(rs.getString("DIACHI"));
            	donhang.setEmail(rs.getString("EMAIL"));
            	donhang.setId(rs.getInt("ID"));
            	donhang.setIdmathang(rs.getInt("IDMATHANG"));
            	donhang.setSodienthoai(rs.getString("SODIENTHOAI"));
            	donhang.setSoluong(rs.getInt("SOLUONG"));
            	donhang.setTrangthai(rs.getBoolean("TRANGTHAI"));
            	donhang.setTentaikhoan(rs.getString("TENTAIKHOAN"));
            	donhang.setDate(new Date(172800000 + rs.getDate("NGAYMUA").getTime()));
            	list.add(donhang);
            }
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}
	@Override
	public boolean xuly(DonHang donhang) {
		// TODO Auto-generated method stub
		 Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
	        PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement("update DONHANG set TRANGTHAI=? where ID=?");
		        stmt.setInt(2, donhang.getId());
		        stmt.setBoolean(1, !donhang.isTrangthai());
		        stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
	}
	@Override
	public boolean xoa(DonHang donhang) {
		// TODO Auto-generated method stub
		 Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
	        PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement("delete from DONHANG where ID=?");
		        stmt.setInt(1, donhang.getId());
		        stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		
	}

}
