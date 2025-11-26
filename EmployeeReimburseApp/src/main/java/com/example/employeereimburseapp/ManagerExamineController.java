package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.*;

public class ManagerExamineController {

    @FXML
    private Label reqIdLabel;

    @FXML
    private Label empIdLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label descLabel;

    @FXML
    private Label statusLabel;

    private int requestId;

    public void setRequestId(int requestId) {
        this.requestId = requestId;
        loadRequestInfo();
    }

    private void loadRequestInfo() {
        try (Connection con = CentralDatabase.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM \"Requests\" WHERE \"request_id\" = ?");
            ps.setInt(1, requestId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reqIdLabel.setText(String.valueOf(rs.getInt("request_id")));
                empIdLabel.setText(String.valueOf(rs.getInt("emp_id")));
                amountLabel.setText(String.valueOf(rs.getDouble("amount")));
                descLabel.setText(rs.getString("description"));
                statusLabel.setText(rs.getString("status"));
            } else {
                System.out.println("Request not found");
            }
        } catch (SQLException e) {
            System.out.println("ExaminePage Error: " + e.getMessage());
        }
    }

    @FXML
    public void approve(ActionEvent event) {
        updateStatus("Approved", event);
    }

    @FXML
    public void reject(ActionEvent event) {
        updateStatus("Rejected", event);
    }

    private void updateStatus(String newStatus, ActionEvent event) {
        try (Connection con = CentralDatabase.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE \"Requests\" SET \"status\" = ? WHERE \"request_id\" = ?");
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Status Updated to: " + newStatus);

                FxHelper.nextPage("managerDashboard-page.fxml", event);
            } else {
                System.out.println("Status update failed.");
            }

        } catch (SQLException e) {
            System.out.println("ExaminePageController Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
