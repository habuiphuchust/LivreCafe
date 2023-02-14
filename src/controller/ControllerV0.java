package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.DatabaseSQL;
import model.IDatabase;
import model.Sach;

public class ControllerV0 implements Initializable{
	@FXML
	private Button button0, button1, button2, button3, button4;
	
	private IDatabase sql = new DatabaseSQL();
	
	public void clickButton1 (ActionEvent e) throws IOException {
		ObservableList<Sach> list = FXCollections.observableArrayList(sql.getAll());
		ControllerV1.setList(list);
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/View1.fxml"));
		Scene scene = new Scene(home,1080,700);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage st = (Stage)button1.getScene().getWindow();
//		st.hide();
		st.setScene(scene);
		st.show();
	}
	public void clickButton2 (ActionEvent e) throws IOException {
		ControllerV1.setList(FXCollections.observableArrayList());
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/View1.fxml"));
		Scene scene = new Scene(home,1080,700);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage st = (Stage)button2.getScene().getWindow();
//		st.hide();
		st.setScene(scene);
		st.show();
	}
	public void clickThongke (ActionEvent e) throws IOException {
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/Thongke.fxml"));
		Scene scene = new Scene(home,1080,700);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage st = (Stage)button2.getScene().getWindow();
//		st.hide();
		st.setScene(scene);
		st.show();
	}
	public void clickDonhang(ActionEvent e) throws IOException {
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/DonHang.fxml"));
		Scene scene = new Scene(home,1080,700);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage st = (Stage)button2.getScene().getWindow();
//		st.hide();
		st.setScene(scene);
		st.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Image img = new Image("file:///C:/Users/DELL/eclipse-workspace/Dich_Vu_Sach/lib/timkiem.jpg");
	    ImageView view = new ImageView(img);
	    view.setFitHeight(100);
	    view.setFitWidth(100);
	    view.setPreserveRatio(true);
	    button2.setGraphic(view);
	    
		Image img1 = new Image("file:///C:/Users/DELL/eclipse-workspace/Dich_Vu_Sach/lib/khosach.jpg");
	    ImageView view1 = new ImageView(img1);
	    view1.setFitHeight(110);
	    view1.setFitWidth(160);
	    view1.setPreserveRatio(true);
	    button1.setGraphic(view1);
	    
	    Image img2 = new Image("file:///C:/Users/DELL/eclipse-workspace/Dich_Vu_Sach/lib/donhoa.jpg");
	    ImageView view2 = new ImageView(img2);
	    view2.setFitHeight(110);
	    view2.setFitWidth(160);
	    view2.setPreserveRatio(true);
	    button3.setGraphic(view2);
	    
	    Image img3 = new Image("file:///C:/Users/DELL/eclipse-workspace/Dich_Vu_Sach/lib/thongke.jpg");
	    ImageView view3 = new ImageView(img3);
	    view3.setFitHeight(110);
	    view3.setFitWidth(160);
	    view3.setPreserveRatio(true);
	    button4.setGraphic(view3);
	    
		
	}

}
