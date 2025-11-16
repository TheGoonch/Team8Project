package com.example.employeereimburseapp;

import javafx.fxml.FXML;

import java.io.IOException;

public class cardManager extends RequestCard {

    @FXML
    public void examineReq() throws IOException {
        FxHelper.nextPage("managerExamine-page");
    }

}
