module com.example.javabot {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.javabot to javafx.fxml;
    exports com.example.javabot;
}