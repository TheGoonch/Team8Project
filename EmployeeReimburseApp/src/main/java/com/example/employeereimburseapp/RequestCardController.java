package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class RequestCardController {
    @FXML
    private Label reqLbl;

    @FXML
    private Label locLbl;

    @FXML
    private Label costLbl;

    @FXML
    private Label statusLbl;

    public void setData(int reqId, String loc, double cost, String status) {
        reqLbl.setText("" + reqId);
        locLbl.setText(loc);
        costLbl.setText(cost + "$");
        statusLbl.setText(status);
    }

}
