package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.sql.*;
import java.sql.SQLException;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class requestInfoController {
    @FXML
    private TextField requestField;

    @FXML
    private TextField userField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField costField;

    @FXML
    private TextField locField;

    @FXML
    private TextField expenseTypeField;

    @FXML
    private TextField reasonField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField urlField;

    @FXML
    private Button costBtn;

    @FXML
    private Button locBtn;

    @FXML
    private Button expBtn;

    @FXML
    private Button reasonBtn;

    @FXML
    private Button urlBtn;

    private boolean costEditOn= false;
    private boolean locEditOn= false;
    private boolean expEditOn= false;
    private boolean reasonEditOn= false;
    private boolean urlEditOn= false;



    public void loadData(int requestId){
        requestField.setText(Integer.toString(requestId));

        try(Connection con = CentralDatabase.getConnection()){
            ResultSet rs;
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM \"Request\" WHERE \"req_id\" = ?" );
            ps.setInt(1, requestId);
            rs = ps.executeQuery();


            if(rs.next()){
                userField.setText(rs.getString("user_id"));
                statusField.setText(rs.getString("status"));
                locField.setText(rs.getString("location"));
                expenseTypeField.setText(rs.getString("expense_type"));
                costField.setText(rs.getString("cost"));
                reasonField.setText(rs.getString("reason"));
                urlField.setText(rs.getString("reciept_url"));
                dateField.setText(rs.getString("date_submitted"));

            }

        }
        catch(SQLException e){
            System.out.println("requestInfoController Error\n" + e.getMessage());
        }
    }

    @FXML
    public void editCost(ActionEvent event){
        if(!costEditOn){
            costField.setDisable(false);
            costField.setEditable(true);
            costBtn.setText("Confirm");
            costEditOn=true;
        }
        else{
            try(Connection con = CentralDatabase.getConnection()){
                ResultSet rs;
                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE \"Request\" SET \"cost\" = ? WHERE \"req_id\" = ?" );
                ps.setDouble(1,Double.parseDouble(costField.getText()));
                ps.setInt(2, Integer.parseInt(requestField.getText()));
                ps.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("requestInfoController Error\n" + e.getMessage());
            }
            costField.setDisable(true);
            costField.setEditable(false);
            costBtn.setText("Edit");
            costEditOn=false;
        }

    }

    @FXML
    public void editLoc(ActionEvent event){
        if(!locEditOn){
            locField.setDisable(false);
            locField.setEditable(true);
            locBtn.setText("Confirm");
            locEditOn=true;
        }
        else{
            try(Connection con = CentralDatabase.getConnection()){
                ResultSet rs;
                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE \"Request\" SET \"location\" = ? WHERE \"req_id\" = ?");
                ps.setString(1, locField.getText());
                ps.setInt(2, Integer.parseInt(requestField.getText()));
                ps.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("requestInfoController Error\n" + e.getMessage());
            }
            locField.setDisable(true);
            locField.setEditable(false);
            locBtn.setText("Edit");
            locEditOn=false;
        }

    }

    @FXML
    public void editExp(ActionEvent event){
        if(!expEditOn){
            expenseTypeField.setDisable(false);
            expenseTypeField.setEditable(true);
            expBtn.setText("Confirm");
            expEditOn=true;
        }
        else{
            try(Connection con = CentralDatabase.getConnection()){
                ResultSet rs;
                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE \"Request\" SET \"expense_type\" = ? WHERE \"req_id\" = ?");
                ps.setString(1, expenseTypeField.getText());
                ps.setInt(2, Integer.parseInt(requestField.getText()));
                ps.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("requestInfoController Error\n" + e.getMessage());
            }
            expenseTypeField.setDisable(true);
            expenseTypeField.setEditable(false);
            expBtn.setText("Edit");
            expEditOn=false;
        }

    }

    @FXML
    public void editReason(ActionEvent event){
        if(!reasonEditOn){
            reasonField.setDisable(false);
            reasonField.setEditable(true);
            reasonBtn.setText("Confirm");
            reasonEditOn=true;
        }
        else{
            try(Connection con = CentralDatabase.getConnection()){
                ResultSet rs;
                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE \"Request\" SET \"expense_type\" = ? WHERE \"req_id\" = ?");
                ps.setString(1, reasonField.getText());
                ps.setInt(2, Integer.parseInt(requestField.getText()));
                ps.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("requestInfoController Error\n" + e.getMessage());
            }
            reasonField.setDisable(true);
            reasonField.setEditable(false);
            reasonBtn.setText("Edit");
            reasonEditOn=false;
        }

    }

    @FXML
    public void editUrl(ActionEvent event){
        if(!urlEditOn){
            urlField.setDisable(false);
            urlField.setEditable(true);
            urlBtn.setText("Confirm");
            urlEditOn=true;
        }
        else{
            try(Connection con = CentralDatabase.getConnection()){
                ResultSet rs;
                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE \"Request\" SET \"reciept_url\" = ? WHERE \"req_id\" = ?");
                ps.setString(1, urlBtn.getText());
                ps.setInt(2, Integer.parseInt(requestField.getText()));
                ps.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("requestInfoController Error\n" + e.getMessage());
            }
            urlField.setDisable(true);
            urlField.setEditable(false);
            urlBtn.setText("Edit");
            urlEditOn=false;
        }

    }

    @FXML
    public void goBack(ActionEvent event) throws IOException{

        FxHelper.nextPage("EmployeeDashboard-page.fxml");
        FxHelper.closeScene(event);

    }


}
