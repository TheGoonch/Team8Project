package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;

import java.sql.*;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private TextField empIDField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    public void testPress(ActionEvent event) throws IOException {
        try(Connection con = CentralDatabase.getConnection();){
            ResultSet rs;
            PreparedStatement ps;



            ps = con.prepareStatement("SELECT * FROM \"User\" WHERE \"user_id\" = ?");
            ps.setInt(1, Integer.parseInt(empIDField.getText()));;
            rs = ps.executeQuery();
            if(rs.next()){
                FxHelper.nextPage("works-page.fxml",event);
            }


            int user_id = rs.getInt("emp_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            System.out.println(name +  " " + email + " " + password + " " + role);

        }catch(SQLException e){
            System.out.println("LoginPageController Error\n" + e.getMessage());
        }

    }

}
