package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;

import java.sql.*;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    public void testPress(ActionEvent event) throws IOException {
        try(Connection con = CentralDatabase.getConnection();){
            String test = "Select * from UNBEmployee";
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UNBEmployee");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int user_id = rs.getInt("emp_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                System.out.println(name +  " " + email + " " + password + " " + role);
            }

        }catch(SQLException e){
            System.out.println("LoginPageController Error\n" + e.getMessage());
        }

    }

}
