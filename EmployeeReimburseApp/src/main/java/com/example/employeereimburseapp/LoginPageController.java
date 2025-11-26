package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import javafx.scene.control.Button;
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
            ps = con.prepareStatement("SELECT * FROM \"UNBEmployee\" WHERE \"emp_id\" = ?");
            ps.setInt(1, Integer.parseInt(empIDField.getText()));
            rs = ps.executeQuery();
            if(!rs.next()){
                System.out.println("No such user in UNB database exists");
                return;
            }

            int idDat = rs.getInt("emp_id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");

            ps = con.prepareStatement("SELECT 1 FROM \"User\" WHERE \"user_id\" = ?");
            ps.setInt(1, Integer.parseInt(empIDField.getText()));
            rs = ps.executeQuery();
            if(rs.next()){

                if(!email.equals(emailField.getText())){
                    System.out.println("Invalid Email");
                    return;
                }

                if(password.equals(passwordField.getText())){
                    createSession(con);
                    UserSession user = UserSession.getUser();
                    if(user == null){
                        System.out.println("Session Creation Failed");
                        FxHelper.closeScene(event);
                    }else{

                        if(role.equals("employee")){
                            FxHelper.nextPage("employeeDashboard-page.fxml");
                            FxHelper.closeScene(event);
                        }else if(role.equals("manager")){
                            FxHelper.nextPage("managerDashboard-page.fxml");
                            FxHelper.closeScene(event);
                        }

                    }

                }else{
                    System.out.println("Invalid Password");
                }

            }else {
                int rows = createNewUser(con);
                if(rows < 0){
                    System.out.println("Account Creation Failed");
                }else{
                    System.out.println("Account Creation Success");
                    createSession(con);
                    UserSession user = UserSession.getUser();
                    if(user == null){
                        System.out.println("Session Creation Failed");
                        FxHelper.closeScene(event);
                    }else{

                        if(role.equals("employee")){
                            FxHelper.nextPage("employeeDashboard-page.fxml");
                            FxHelper.closeScene(event);
                        }else if(role.equals("manager")){
                            FxHelper.nextPage("managerDashboard-page.fxml");
                            FxHelper.closeScene(event);
                        }

                    }

                }

            }
        }catch(SQLException e){
            System.out.println("LoginPageController Error\n" + e.getMessage());
        }

    }

    public int createNewUser(Connection con)  throws IOException{
        try(PreparedStatement ps = con.prepareStatement("SELECT * FROM \"UNBEmployee\" WHERE \"emp_id\" = ?")){
            ps.setInt(1, Integer.parseInt(empIDField.getText()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            int empId = rs.getInt("emp_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            Date curdate = new Date(System.currentTimeMillis());

            System.out.println(empId+" "+name+" "+email+" "+password+" "+role);
            PreparedStatement ins = con.prepareStatement("INSERT INTO \"User\" (\"user_id\", \"name\", \"email\", \"role\", \"date_create\", \"password\") VALUES (?,?,?,?,?,?)");
            ins.setInt(1, empId);
            ins.setString(2, name);
            ins.setString(3, email);
            ins.setString(4, role);
            ins.setDate(5, curdate);
            ins.setString(6, password);
            int rows = ins.executeUpdate();
            return rows;

        }catch(SQLException e){
            System.out.println("LoginPageController Error\n" + e.getMessage());
        }

        return 0;

    }

    public void createSession(Connection con) throws SQLException{
        try(PreparedStatement ps = con.prepareStatement("SELECT * FROM \"UNBEmployee\" WHERE \"emp_id\" = ?")){
            ps.setInt(1, Integer.parseInt(empIDField.getText()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            int empId = rs.getInt("emp_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String role = rs.getString("role");
            UserSession.createUser(empId, name, email, role);
            UserSession user = UserSession.getUser();
        }catch(SQLException e){
            System.out.println("LoginPageController Error\n" + e.getMessage());
        }
    }

    public void setEmpIDField(TextField empIDField){
        this.empIDField = empIDField;
    }
    public void setPasswordField(TextField passwordField){
        this.passwordField = passwordField;
    }
    public void setEmailField(TextField emailField){
        this.emailField = emailField;
    }

    public TextField getEmpIDField(){
        return empIDField;
    }
    public TextField getPasswordField(){
        return passwordField;
    }
    public TextField getEmailField(){
        return emailField;
    }

}
