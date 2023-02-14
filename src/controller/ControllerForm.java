package controller;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.DatabaseSQL;
import model.DonHang;
import model.IDatabase;
import model.Sach;

public class ControllerForm implements Initializable {
	@FXML
	Label lbtensp, lbtenkh, lbdiachi, lbsdt, lbemail, lbsl;
	@FXML
	TextField tftenkh, tfdiachi, tfsdt, tfemail, tfsl;
	
	private static Sach mathang;
	
	public static void setDonHang (Sach mathang) {
		ControllerForm.mathang = mathang;
	}
	
	private boolean validateSdt(String sdt) {
		Pattern pattern = Pattern.compile("^\\d{8,11}$");
		Matcher matcher = pattern.matcher(sdt);
		return matcher.matches();
	}
	
	private boolean validateName(String name) {
		Pattern pattern = Pattern.compile("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$");
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	private boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lbtensp.setText(mathang.getTensach());
		
	}
	public void clickOk(ActionEvent e) {
		lbtenkh.setText("");
		lbdiachi.setText("");
		lbsdt.setText("");
		lbemail.setText("");
		lbsl.setText("");
		
		if (tftenkh.getText().isEmpty()) {
			lbtenkh.setText("Không để trống");
			return;
		} else if (tfdiachi.getText().trim().isEmpty()) {
			lbdiachi.setText("Không để trống");
			return;
		} else if (!validateSdt(tfsdt.getText())) {
			lbsdt.setText("Nhập sai sdt");
			return;
		} else if (!validateEmail(tfemail.getText())) {
			lbemail.setText("Nhập sai email");
			return;
		} if (!validateName(tftenkh.getText())) {
			lbtenkh.setText("Nhập sai tên");
			return;
		}
		int sl;
		try {
			sl = Integer.parseInt(tfsl.getText());
		} catch (Exception e2) {
			// TODO: handle exception
			lbsl.setText("Nhập sai số lượng");
			return;
		}
		DonHang donhang = new DonHang();
		donhang.setTenkh(tftenkh.getText());
		donhang.setDiachi(tfdiachi.getText());
		donhang.setEmail(tfemail.getText());
		donhang.setIdmathang(mathang.getId());
		donhang.setSodienthoai(tfsdt.getText());
		donhang.setTentaikhoan("");
		donhang.setTrangthai(false);
		donhang.setSoluong(sl);
		donhang.setDate(new Date(System.currentTimeMillis()));
		//xac nhan
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Xác nhận");
		alert.setHeaderText("Xác nhận đơn hàng");
		alert.setContentText("Tên sách: "+mathang.getTensach()+" Số lượng: "+tfsl.getText());

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null) {
			// do not something
		} else if (option.get() == ButtonType.OK) {
			// xac nhan mua
			IDatabase db = new DatabaseSQL();
			boolean flag = db.themDonHang(donhang);
			if (flag = true) {
				System.out.println("đơn thành công");
			} else {
				System.out.println("đơn thất bại");
				return;
			}
			Stage st = (Stage)lbtenkh.getScene().getWindow();
			st.close();
			System.out.println("close");
		} else if (option.get() == ButtonType.CANCEL) {
			//do not something
		} else {
			// do not something
		}
		
	}

}
