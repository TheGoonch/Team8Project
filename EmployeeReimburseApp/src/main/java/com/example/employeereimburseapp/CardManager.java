package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CardManager extends RequestCard {

    @FXML
    private Label userIdLbl;

    @FXML
    public void examineReq() throws IOException {
        FxHelper.nextPage("managerExamine-page");
    }

    public void setUserId(int UserId){
        userIdLbl.setText("" + UserId);
    }

}
