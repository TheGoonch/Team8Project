package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    protected void takeMeToLogin(ActionEvent event) throws IOException {
        System.out.println("Hello World");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();
        newStage.setTitle("Login Window");
        newStage.setScene(scene);
        newStage.show();
    }
}
