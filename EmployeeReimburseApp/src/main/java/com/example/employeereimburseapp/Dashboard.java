package com.example.employeereimburseapp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.sql.Connection;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

public abstract class Dashboard {

    @FXML
    private Label welcomeLbl;

    public void initialize() {
        UserSession user = UserSession.getUser();
        assert user != null;
        welcomeLbl.setText("Welcome! " + user.getName() );
        try {
            loadAllRequests();
        } catch (SQLException e) {
            System.out.println("DashBoard Error\n" + e.getMessage());
        }

    }

    public abstract void addReqCard(int reqId, String loc, double cost, String status) throws IOException;

    public void loadAllRequests() throws SQLException{
        try(Connection con = CentralDatabase.getConnection()){
            UserSession user = UserSession.getUser();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM \"Request\" WHERE \"user_id\" = ?");
            assert user != null;
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int reqId = rs.getInt("req_id");
                String loc = rs.getString("location");
                double cost = rs.getDouble("cost");
                String status = rs.getString("status");
                addReqCard(reqId, loc, cost, status);
            }

        }catch (SQLException e){
            System.out.println("DashBoard Error\n" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void goBackToLogin(ActionEvent event) throws IOException{
        UserSession.destroyUser();
        FxHelper.nextPage("Login-page.fxml");
        FxHelper.closeScene(event);
    }
}
