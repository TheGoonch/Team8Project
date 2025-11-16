package com.example.employeereimburseapp;

import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cardEmployee extends RequestCard {

    @FXML
    public void moreInfo() {
        System.out.println("More Info");
    }

    @FXML
    public void cancelRequest(){
        try(Connection con = CentralDatabase.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM \"Request\" WHERE \"req_id\" = ?");
            ps.setInt(1, getRequestID());
            ps.executeUpdate();
            System.out.println("Request deleted");
            removeScene();
        }catch(SQLException e){
            System.out.println("Error in getting database connection in Request Card\n" + e.getMessage());
        }
    }

}
