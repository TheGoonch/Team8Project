package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;

import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;


public class EmployeeDashController extends Dashboard{

    @FXML
    private TextField locField;

    @FXML
    private TextField expenseField;

    @FXML
    private TextField costField;

    @FXML
    private TextField reasonField;

    @FXML
    private TextField recieptField;

    @FXML
    private VBox reqListVB;

    @FXML
    public void requestCreate(){
        try(Connection con = CentralDatabase.getConnection()){
            UserSession user = UserSession.getUser();
            PreparedStatement ps = con.prepareStatement("INSERT INTO \"Request\" (\"user_id\", \"location\", \"expense_type\", " +
                    "\"cost\", \"reason\", \"reciept_url\", \"status\", \"date_submitted\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            assert user != null;
            String status = "Pending";
            double cost = Double.parseDouble(costField.getText());
            String loc = locField.getText();
            ps.setInt(1, user.getId());
            ps.setString(2, locField.getText());
            ps.setString(3, expenseField.getText());
            ps.setDouble(4, Double.parseDouble(costField.getText()));
            ps.setString(5, reasonField.getText());
            ps.setString(6, recieptField.getText());
            ps.setString(7, status);
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            int rows = ps.executeUpdate();

            if(rows < 0){
                System.out.println("Request Creation Failed");
            }else{
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int userId = rs.getInt(1);
                locField.clear();
                expenseField.clear();
                costField.clear();
                reasonField.clear();
                recieptField.clear();
                System.out.println("Request Creation Success");
                addReqCard(userId, loc, cost, status);
            }



        }catch(SQLException | IOException e){
            System.out.println("employeeDashController Error\n" + e.getMessage());
        }
    }

    public void addReqCard(int reqId, String loc, double cost, String status) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("employee-card.fxml"));
        Node card = loader.load();
        CardEmployee controller = loader.getController();
        controller.setData(reqId, loc, cost, status);
        reqListVB.getChildren().add(card);
    }

}
