package com.example.employeereimburseapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDashController extends Dashboard{

    @FXML
    private VBox reqListVB;

    @FXML
    private TextField userIdField;

    private int filterId;

    @Override
    public void addReqCard(int reqId, String loc, double cost, String status) throws IOException {
        int userId = 0;
        try(Connection con = CentralDatabase.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT \"user_id\" FROM \"Request\" WHERE \"req_id\" = ?");
            ps.setInt(1, reqId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            userId = rs.getInt("user_id");
        } catch (SQLException e) {
            System.out.println("ManagerDashController Error\n" + e.getMessage());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manager-card.fxml"));
        Node card = loader.load();
        CardManager controller = loader.getController();
        controller.setData(reqId, loc, cost, status);
        controller.setUserId(userId);
        reqListVB.getChildren().add(card);
    }

    @Override
    public void loadAllRequests(){
        try(Connection con = CentralDatabase.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM \"Request\" WHERE \"status\" = ?");
            ps.setString(1, "Pending");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int reqId = rs.getInt("req_id");
                int userId = rs.getInt("user_id");
                String loc = rs.getString("location");
                double cost = rs.getDouble("cost");
                String status = rs.getString("status");
                addReqCard(reqId, loc, cost, status);
            }
        }catch (SQLException e){
            System.out.println("ManagerDashController SQLError\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("ManagerDashController IOError\n" + e.getMessage());
        }
    }

    @FXML
    public void filterList(ActionEvent event) {
    }
    @FXML
    public void clearFilter(ActionEvent event) {
    }
    @FXML
    public void refreshList(ActionEvent event) {
    }



}
