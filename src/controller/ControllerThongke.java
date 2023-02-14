package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.DatabaseSQL;
import model.IDatabase;
import model.ThongKe;

public class ControllerThongke implements Initializable {
	@FXML
	LineChart<String, Integer> lineChart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		IDatabase db = new DatabaseSQL();
		List<ThongKe> tks = db.getThongke(10);
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (ThongKe tk : tks) {
			XYChart.Data<String, Integer> tkc = new XYChart.Data<>(tk.getDate().toString(), tk.getDoanhthu());
			series.getData().add(tkc);
		}
		series.setName("Doanh thu cửa hàng");
		lineChart.getData().add(series);
	}
	public void clickBack(ActionEvent e) throws IOException {
		Parent home = (Parent)FXMLLoader.load(getClass().getResource("/view/View0.fxml"));
		Scene scene = new Scene(home,1080,700);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		Stage st = (Stage)lineChart.getScene().getWindow();
//		st.hide();
		st.setScene(scene);
		st.show();
	}

}
