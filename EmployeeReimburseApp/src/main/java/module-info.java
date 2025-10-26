module com.example.employeereimburseapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeereimburseapp to javafx.fxml;
    exports com.example.employeereimburseapp;
}