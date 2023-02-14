package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DatabaseSQL;
import model.DonHang;
import model.IDatabase;

public class ControllerDonhang implements Initializable {
	@FXML
	private TableView<DonHang> tableview;
	@FXML
	private TableColumn<DonHang, String> tenkh, diachi, sodienthoai, email, tentaikhoan, date;
	@FXML
	private TableColumn<DonHang, Integer>  id, idmathang, soluong;
	@FXML
	private TableColumn<DonHang, Boolean> trangthai;
	@FXML
	private TextField textField;
	@FXML
	private Button xoa, xuly;
	
	private DonHang selected = null;
	ObservableList<DonHang> olist;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		xoa.setDisable(true);
		xuly.setDisable(true);
		IDatabase db = new DatabaseSQL();
		olist = FXCollections.observableArrayList(db.getDonhangs());
		FilteredList<DonHang> flist = new FilteredList<>(olist);
		textField.textProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable arg0) {
				// TODO Auto-generated method stub
				flist.setPredicate(textField.getText().trim().isEmpty() ? s -> true : s -> s.chitiet().toUpperCase().contains(textField.getText().toUpperCase()));
			}
		});		
		tenkh.setCellValueFactory(new PropertyValueFactory<>("tenkh"));
		diachi.setCellValueFactory(new PropertyValueFactory<>("diachi"));
		sodienthoai.setCellValueFactory(new PropertyValueFactory<>("sodienthoai"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		tentaikhoan.setCellValueFactory(new PropertyValueFactory<>("tentaikhoan"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		idmathang.setCellValueFactory(new PropertyValueFactory<>("idmathang"));
		soluong.setCellValueFactory(new PropertyValueFactory<>("soluong"));
		trangthai.setCellValueFactory(new PropertyValueFactory<>("trangthai"));
		
		tableview.setItems(flist);
		
	}
	
	public void clickBack(ActionEvent e ) throws IOException {
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/View0.fxml"));
		Scene scene = new Scene(home,1080,700);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage st = (Stage)tableview.getScene().getWindow();
//		st.hide();
		st.setScene(scene);
		st.show();

	}
	
	public void clickXoa (ActionEvent e) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Xác nhận");
		alert.setHeaderText("Bạn muốn xóa đơn khỏi kho lưu trữ?");
		alert.setContentText("ID: "+selected.getId());

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null) {
			// do not something
		} else if (option.get() == ButtonType.OK) {
			// xac nhan xoa
			IDatabase db = new DatabaseSQL();
			boolean flag = db.xoa(selected);
			if (flag = true) {
				System.out.println("xóa đơn thành công");
			} else {
				System.out.println("xóa thất bại bại");
				return;
			}
			olist.remove(selected);
			selected = null;
			xuly.setDisable(true);
			xoa.setDisable(true);
		} else if (option.get() == ButtonType.CANCEL) {
			//do not something
		} else {
			// do not something
		}

	}
	
	public void clickTable(MouseEvent e) {
		try {
			selected = tableview.getSelectionModel().getSelectedItem();
			System.out.println("click table");
		} 
		catch (Exception e2) {
		}
		if (selected != null) {
			xoa.setDisable(false);
			xuly.setDisable(false);
		}
	}
	
	public void clickXuly (ActionEvent e) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Xác nhận");
		alert.setHeaderText(null);
		alert.setContentText("ID: "+selected.getId());

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null) {
			// do not something
		} else if (option.get() == ButtonType.OK) {
			// xac nhan xoa
			IDatabase db = new DatabaseSQL();
			boolean flag = db.xuly(selected);
			if (flag) {
				selected.setTrangthai(!selected.isTrangthai());
				trangthai.setVisible(false);
				trangthai.setVisible(true);
			}			
		} else if (option.get() == ButtonType.CANCEL) {
			//do not something
		} else {
			// do not something
		}

	}

}
