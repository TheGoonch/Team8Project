package com.example.employeereimburseapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardEmployee extends RequestCard {

    @FXML
    public void moreInfo(ActionEvent event) throws IOException {
        try(Connection con = CentralDatabase.getConnection()){
            if(checkStatus(con)){
                System.out.println("Request cannot be viewed");
                return;
            }
        }catch(SQLException e){
            System.out.println("Error in moreInfo\n" + e.getMessage() + e.getSQLState());
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("requestInfo-page.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();

        requestInfoController requestController = loader.getController();
        requestController.loadData(getRequestID());
        newStage.setTitle("Employee Reimburse App");
        newStage.setScene(scene);
        newStage.show();



        FxHelper.closeScene(event);
    }

    @FXML
    public void cancelRequest(){
        try(Connection con = CentralDatabase.getConnection()){

            if(checkStatus(con)){
                System.out.println("Request cannot be cancelled");
                return;
            }
            PreparedStatement ps = con.prepareStatement("DELETE FROM \"Request\" WHERE \"req_id\" = ?");
            ps.setInt(1, getRequestID());
            ps.executeUpdate();
            System.out.println("Request deleted");
            removeScene();
        }catch(SQLException e){
            System.out.println("Error in cancelling request card\n" + e.getMessage() + e.getSQLState());
        }
    }

    public boolean checkStatus(Connection con){
        try(PreparedStatement ps = con.prepareStatement("SELECT \"status\" FROM \"Request\" WHERE \"req_id\" = ?");){
            ps.setInt(1, getRequestID());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String status = rs.getString("status");
            if (status.equals("Rejected") || status.equals("Approved")) {
                return true;
            }else if(status.equals("Pending")){
                return false;
            }
        }catch (SQLException e){
            System.out.println("Check Status failed\n" + e.getMessage());
            return true;
        }
        return false;
    }



}
