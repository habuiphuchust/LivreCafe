package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DatabaseSQL;
import model.IDatabase;
import model.Sach;

public class ControllerEditBook implements Initializable {
	@FXML
	private ImageView imageview;
	@FXML
	private TextField tten, tgia, ttacgia, tnhaxuatban, tnamxuatban, ttheloai, ttinhtrang, tmotathem;
	@FXML
	private Label label1, label2, label3, label4, label5, label6, label7, label8;
	
	private String ten , gia, tacgia, nhaxuatban, namxuatban, theloai, tinhtrang, motathem, anhbia = "";
	// them = false, sua = true
	static public boolean themsua;
	// dung khi sua
	static public Sach sachbandau = null ;

	
	public void ChooseImage (ActionEvent e){
        Stage stage = (Stage) imageview.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Chọn 1 ảnh");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fc.getExtensionFilters().add(imageFilter);
        File file = fc.showOpenDialog(stage);
        if (file != null){
            Image image = new Image(file.toURI().toString(),320, 213, false, true);
            imageview.setImage(image);
            anhbia = file.toURI().toString();
            System.out.println(file.toURI().toString());
        }
    }
	public void clickOk (ActionEvent e) {
		label1.setText("");
		label2.setText("");
		label3.setText("");
		label4.setText("");
		label5.setText("");
		label6.setText("");
		label7.setText("");
		label8.setText("");
		if (tten.getText().isEmpty()) {
			label1.setText("Chưa nhập");
			return;
		} else if (tten.getText().length() > 49) {
			label1.setText("Vui lòng nhập ít hơn 50 kí tự");
		} else if (tgia.getText().trim().isEmpty()) {
			label2.setText("Chưa nhập");
			return;
		} else if (ttacgia.getText().length() > 49) {
			label3.setText("Vui lòng nhập ít hơn 50 kí tự");
			return;
		} else if (tnhaxuatban.getText().trim().length() > 49) {
			label4.setText("Vui lòng nhập ít hơn 50 kí tự");
			return;
		} else if (tnamxuatban.getText().isEmpty()) {
			label5.setText("Chưa nhập");
			return;
		} else if (ttheloai.getText().length() > 49) {
			label6.setText("Vui lòng nhập ít hơn 50 kí tự");
			return;
		} else if (ttinhtrang.getText().isEmpty()) {
			label7.setText("Chưa nhập");
			return;
		} else if (tmotathem.getText().length() > 200) {
			label8.setText("Vui lòng nhập ít hơn 200 kí tự");
			return;
		}
		
		ten = tten.getText();
		gia = tgia.getText();
		if (ttacgia.getText().trim().isEmpty())
			tacgia = "";
		else
			tacgia = ttacgia.getText();
		if (tnhaxuatban.getText().trim().isEmpty())
			nhaxuatban = "";
		else
			nhaxuatban = tnhaxuatban.getText();
		namxuatban = tnamxuatban.getText();
		if (ttheloai.getText().trim().isEmpty())
			theloai = "";
		else
			theloai = ttheloai.getText();
		tinhtrang = ttinhtrang.getText();
		if (tmotathem.getText().trim().isEmpty())
			motathem = "";
		else
			motathem = tmotathem.getText();		
		Sach book = new Sach();
		try {
			book.setNamxuatban(Integer.parseInt(namxuatban));
		} catch (Exception e2) {
			// TODO: handle exception
			label5.setText("Nhập số nguyên");
			return;
		}
		try {
			book.setGia(Integer.parseInt(gia));
		} catch (Exception e2) {
			// TODO: handle exception
			label2.setText("Nhập số nguyên");
			return;
		}
		
		
		book.setTensach(ten);
		book.setTacgia(tacgia);
		book.setNhaxuatban(nhaxuatban);
		book.setTheloai(theloai);
		book.setMotathem(motathem);
		if (tinhtrang.equals("true"))
			book.setTinhtrang(true);
		else 
			book.setTinhtrang(false);
		
		book.setAnhbia(anhbia);
		IDatabase db = new DatabaseSQL();
		boolean flag = false;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thông báo");
		alert.setHeaderText("Kết quả:");
		if (themsua == false) {
		    flag = db.themSach(book);
			if (flag == true) {	
				alert.setContentText("Thêm sách thành công, xác nhận tại kho sách");
			} else {
				alert.setContentText("Thất bại, vui lòng thử lại");
			}
		} else {
			book.setId(sachbandau.getId());
			flag = db.suaSach(book);
			if (flag == true) {	
				alert.setContentText("Sửa sách thành công, xác nhận tại kho sách");
			} else {
				alert.setContentText("Thất bại, vui lòng thử lại");
			}
		}
		alert.showAndWait();
		clickCancel(new ActionEvent());
	}
	public void clickCancel (ActionEvent e) {
		Stage st = (Stage)label1.getScene().getWindow();
		st.close();
		System.out.println("close");

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (sachbandau != null) {
			if (sachbandau.getTensach()!=null)
				tten.setText(sachbandau.getTensach());
			if (sachbandau.getTacgia()!=null)
				ttacgia.setText(sachbandau.getTacgia());
			if (sachbandau.getNhaxuatban()!=null)
				tnhaxuatban.setText(sachbandau.getNhaxuatban());
			if (sachbandau.getTheloai()!=null)
				ttheloai.setText(sachbandau.getTheloai());
			if (sachbandau.getMotathem()!=null)
				tmotathem.setText(sachbandau.getMotathem());
			tgia.setText(Integer.toString(sachbandau.getGia()));
			if (sachbandau.getNamxuatban()!=0)
				tnamxuatban.setText(Integer.toString(sachbandau.getNamxuatban()));
			if (sachbandau.isTinhtrang() == true) 
				ttinhtrang.setText("true");
			else 
				ttinhtrang.setText("false");
			if (sachbandau.getAnhbia()!=null)
				anhbia =sachbandau.getAnhbia();
			try {
				Image image = new Image(anhbia,320, 213, false, true);
			    imageview.setImage(image);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("sửa ảnh, không có ảnh bìa");
			}
		   
		}
		
	}

}
