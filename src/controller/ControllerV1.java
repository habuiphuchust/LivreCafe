package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DatabaseSQL;
import model.IDatabase;
import model.Sach;

public class ControllerV1 implements Initializable {
	@FXML
	private TableView<Sach> tableview;
	@FXML
	private TableColumn<Sach, String> tensach, nhaxuatban, tacgia, theloai;
	@FXML
	private TableColumn<Sach, Integer>  namxuatban, id, gia, daban;
	@FXML
	private TableColumn<Sach, ImageView> anhbia;
	@FXML
	private TableColumn<Sach, Boolean> tinhtrang;
	@FXML 
	private Button muahang, timkiem, xemthongtin, xoa, sua, back;
	@FXML
	private TextField textfield;
	
	private static ObservableList<Sach> list;
	private FilteredList<Sach> flist = null;
	private Sach selected = null;

	public static void setList(ObservableList<Sach> list) {
		ControllerV1.list = list;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		xemthongtin.setDisable(true);
		xoa.setDisable(true);
		sua.setDisable(true);
		muahang.setDisable(true);
		flist = new FilteredList<>(list);
		textfield.textProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable arg0) {
				// TODO Auto-generated method stub
				flist.setPredicate(textfield.getText().trim().isEmpty() ? s -> true : s -> s.chitiet().toUpperCase().contains(textfield.getText().toUpperCase()));
			}
		});		
		tensach.setCellValueFactory(new PropertyValueFactory<>("tensach"));
		tacgia.setCellValueFactory(new PropertyValueFactory<>("tacgia"));
		tinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
		nhaxuatban.setCellValueFactory(new PropertyValueFactory<>("nhaxuatban"));
		theloai.setCellValueFactory(new PropertyValueFactory<>("theloai"));
		namxuatban.setCellValueFactory(new PropertyValueFactory<>("namxuatban"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		gia.setCellValueFactory(new PropertyValueFactory<>("gia"));
		daban.setCellValueFactory(new PropertyValueFactory<>("daban"));
		anhbia.setCellValueFactory(new PropertyValueFactory<>("image"));
			
		tableview.setItems(flist);		
	}
	
	public void clickBack (ActionEvent e) throws IOException {
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/View0.fxml"));
		Scene scene = new Scene(home,1080,700);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage st = (Stage)back.getScene().getWindow();
//		st.hide();
		st.setScene(scene);
		st.show();
	}
	public void clickTimkiem (ActionEvent e) throws IOException {
		IDatabase sql = new DatabaseSQL();
		String tk = textfield.getText().trim();
		if (!tk.isEmpty()) {
			list.remove(0, list.size());
			list.addAll(sql.timkiem(tk));
		}
		else {
			list.remove(0, list.size());
			list.addAll(sql.getAll());
		}
		tableview.setItems(flist);
	}
	public void clickXoa (ActionEvent e) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Xác nhận");
		alert.setHeaderText("Bạn muốn xóa sách khỏi kho lưu trữ?");
		alert.setContentText("Tên sách: "+selected.getTensach());

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null) {
			// do not something
		} else if (option.get() == ButtonType.OK) {
			// xac nhan xoa
			IDatabase db = new DatabaseSQL();
			boolean flag = db.xoaSach(selected);
			if (flag = true) {
				System.out.println("xóa sách thành công");
			} else {
				System.out.println("xóa thất bại");
				return;
			}
			list.remove(selected);
			selected = null;
			xemthongtin.setDisable(true);
			xoa.setDisable(true);
			muahang.setDisable(true);
		} else if (option.get() == ButtonType.CANCEL) {
			//do not something
		} else {
			// do not something
		}

	}
	public void clickTable(MouseEvent e) {
		try {
			selected = tableview.getSelectionModel().getSelectedItem();
		} 
		catch (Exception e2) {
		}
		if (selected != null) {
			xemthongtin.setDisable(false);
			xoa.setDisable(false);
			sua.setDisable(false);
			muahang.setDisable(false);
		}
	}
	public void clickXemthongtin(ActionEvent e) throws IOException {
		if (selected != null) {
			Dialog<String> dialog = new Dialog<>(); 
			dialog.setTitle("Thông tin thêm");
			dialog.setHeaderText(null);
			dialog.getDialogPane().setContentText(selected.getMotathem());
			dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			dialog.showAndWait();

		}
	}
	public void clickThem(ActionEvent e) throws IOException {
		ControllerEditBook.sachbandau = null;
		ControllerEditBook.themsua = false;
		
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/EditBook.fxml"));
		Scene scene = new Scene(home,800,600);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setTitle("Thêm sách");
		stage.setScene(scene);
		Stage st = (Stage)back.getScene().getWindow();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(st);
		stage.show();
	}
	public void clickMuon (ActionEvent e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thông báo");
		alert.setHeaderText("Hệ thống chưa hỗ trợ chức năng này");
		alert.setContentText("Đến trực tiếp cửa hàng để mượn sách");
		alert.showAndWait();
	}
	public void clickSua (ActionEvent e) throws IOException {
		ControllerEditBook.sachbandau = selected;
		ControllerEditBook.themsua = true;
		
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/EditBook.fxml"));
		Scene scene = new Scene(home,800,600);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setTitle("Sửa sách");
		stage.setScene(scene);
		Stage st = (Stage)back.getScene().getWindow();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(st);
		stage.show();
		
	}
	public void clickMuahang (ActionEvent e) throws IOException {
		ControllerForm.setDonHang(selected);
		
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/Form.fxml"));
		Scene scene = new Scene(home,600,400);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setTitle("Điền form");
		stage.setScene(scene);
		Stage st = (Stage)back.getScene().getWindow();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(st);
		stage.show();
	}

}
