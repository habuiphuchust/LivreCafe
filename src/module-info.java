module QL_Sach {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
