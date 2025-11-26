module com.example.employeereimburseapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires jdk.jfr;


    opens com.example.employeereimburseapp to javafx.fxml;
    exports com.example.employeereimburseapp;
}