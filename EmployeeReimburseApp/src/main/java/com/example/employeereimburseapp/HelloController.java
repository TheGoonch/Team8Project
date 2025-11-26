package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;

public class HelloController {

    @FXML
    protected void takeToLogInPage(ActionEvent event) throws IOException {
        FxHelper.nextPage("login-page.fxml", event);
    }
}
