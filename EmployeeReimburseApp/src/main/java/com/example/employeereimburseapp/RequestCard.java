package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class RequestCard {
    @FXML
    private Label reqLbl;

    @FXML
    private Label locLbl;

    @FXML
    private Label costLbl;

    @FXML
    private Label statusLbl;

    @FXML
    private AnchorPane root;

    public void setData(int reqId, String loc, double cost, String status) {
        reqLbl.setText("" + reqId);
        locLbl.setText(loc);
        costLbl.setText(cost + "$");
        statusLbl.setText(status);
    }

    public int getRequestID(){
        return Integer.parseInt(reqLbl.getText());
    }

    public void removeScene(){
        Pane parent = (Pane) root.getParent();
        parent.getChildren().remove(root);
    }

}
