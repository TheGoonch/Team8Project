package com.example.employeereimburseapp;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


public class FxHelper {

    public static void nextPage(String sceneName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FxHelper.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();
        newStage.setTitle("Employee Reimburse App");
        newStage.setScene(scene);
        newStage.show();
    }

    public static void closeScene(ActionEvent event) throws IOException{
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}
