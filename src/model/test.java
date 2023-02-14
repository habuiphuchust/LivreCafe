package model;

import java.sql.Date;
import java.util.List;

public class test {
	public static void main(String[] args) {
		IDatabase db = new DatabaseSQL();
		List<ThongKe> tks = db.getThongke(10);
		for (ThongKe tk : tks) {
			System.out.println(new Date(172800000 + tk.getDate().getTime()));
			System.out.println(tk.getDoanhthu());
		}
	}
}
