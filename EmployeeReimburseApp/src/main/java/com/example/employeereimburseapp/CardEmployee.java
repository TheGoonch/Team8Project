package com.example.employeereimburseapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardEmployee extends RequestCard {

    @FXML
    public void moreInfo(ActionEvent event) throws IOException {

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
