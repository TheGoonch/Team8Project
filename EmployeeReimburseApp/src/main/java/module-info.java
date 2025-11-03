module com.example.employeereimburseapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.employeereimburseapp to javafx.fxml;
    exports com.example.employeereimburseapp;
}