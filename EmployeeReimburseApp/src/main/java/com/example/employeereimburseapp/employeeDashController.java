package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class employeeDashController {

    @FXML
    private Label welcomeLbl;

    public void initialize() {
        UserSession user = UserSession.getUser();
        assert user != null;
        welcomeLbl.setText("Welcome! " + user.getName());

    }


}
