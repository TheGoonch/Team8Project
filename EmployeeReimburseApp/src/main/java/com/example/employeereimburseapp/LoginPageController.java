package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import javafx.scene.control.TextField;

public class LoginPageController {

    @FXML
    private TextField empIDField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    public void testPress(ActionEvent event) throws IOException {
        try(Connection con = CentralDatabase.getConnection()){
            ResultSet rs;
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT 1 FROM \"UNBEmployee\" WHERE \"emp_id\" = ?");
            ps.setInt(1, Integer.parseInt(empIDField.getText()));
            rs = ps.executeQuery();
            if(!rs.next()){
                System.out.println("Invalid Id");
                return;
            }

            ps = con.prepareStatement("SELECT 1 FROM \"User\" WHERE \"user_id\" = ?");
            ps.setInt(1, Integer.parseInt(empIDField.getText()));
            rs = ps.executeQuery();
            if(rs.next()){
                ps = con.prepareStatement("SELECT 1 FROM \"User\" WHERE \"email\" = ?");
                ps.setString(1, emailField.getText());
                rs = ps.executeQuery();
                if(!rs.next()){
                    System.out.println("Invalid Email");
                    return;
                }
                ps = con.prepareStatement("SELECT 1 FROM \"User\" WHERE \"password\" = ?");
                ps.setString(1, passwordField.getText());
                rs = ps.executeQuery();
                if(rs.next()){
                    FxHelper.nextPage("works-page.fxml",event);
                }else{
                    System.out.println("Invalid Password");
                }
            }else {
                ps = con.prepareStatement("SELECT * FROM \"UNBEmployee\" WHERE \"emp_id\" = ?");
                ps.setInt(1, Integer.parseInt(empIDField.getText()));

                rs = ps.executeQuery();
                rs.next();
                int empId = rs.getInt("emp_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                Date curdate = new Date(System.currentTimeMillis());

                System.out.println(empId+" "+name+" "+email+" "+password+" "+role);
                ps = con.prepareStatement("INSERT INTO \"User\" (\"user_id\", \"name\", \"email\", \"role\", \"date_create\", \"password\") VALUES (?,?,?,?,?,?)");
                ps.setInt(1, empId);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, role);
                ps.setDate(5, curdate);
                ps.setString(6, password);
                int rows = ps.executeUpdate();
                if(rows < 0){
                    System.out.println("Account Creation Failed");
                }else{
                    System.out.println("Account Creation Failed");
                    FxHelper.nextPage("works-page.fxml",event);
                }

            }
        }catch(SQLException e){
            System.out.println("LoginPageController Error\n" + e.getMessage());
        }

    }

}
