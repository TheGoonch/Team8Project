package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;

public class HelloController {

    @FXML
    protected void takeToLogInPage(ActionEvent event) throws IOException {
        FxHelper.nextPage("login-page.fxml");
        FxHelper.closeScene(event);
    }
}
