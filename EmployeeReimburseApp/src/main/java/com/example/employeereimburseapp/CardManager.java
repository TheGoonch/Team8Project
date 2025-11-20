package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class CardManager extends RequestCard {

    @FXML
    private Label userIdLbl;

    public void setUserId(int userId) {
        userIdLbl.setText(String.valueOf(userId));
    }

    @FXML
    public void examineReq() throws IOException {
        ManagerExamineController.selectedRequestId = getRequestID();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("managerExamine-page.fxml"));
        Node root = loader.load();

        ManagerExamineController controller = loader.getController();

        ManagerDashController dash = (ManagerDashController) userIdLbl.getScene().getUserData();

        controller.setDashboardController(dash);

        Stage stage = new Stage();
        stage.setTitle("Examine Request");
        stage.setScene(new Scene((Parent) root));
        stage.show();
    }
}
