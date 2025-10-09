module com.example.employeereimbuseapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeereimbuseapp to javafx.fxml;
    exports com.example.employeereimbuseapp;
}