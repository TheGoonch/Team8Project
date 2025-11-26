package com.example.employeereimburseapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ManagerDashController {

    @FXML
    private Button approveBtn;

    @FXML
    private Button rejectBtn;

    @FXML
    private Button refreshBtn1; // Examine button

    @FXML
    private Button filterBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button viewReceiptBtn;

    @FXML
    private VBox requestList;

    @FXML
    private Label welcomeLbl;