package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.sql.*;

public class ManagerExamineController {

    @FXML
    private Label reqIdLabel;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label expenseTypeLabel;

    @FXML
    private Label costLabel;

    @FXML
    private Label reasonLabel;

    @FXML
    private Label receiptUrlLabel;

    private int requestId;

    public void setRequestId(int requestId) {
        this.requestId = requestId;
        loadRequestInfo();
    }

    private void loadRequestInfo() {
        try (Connection con = CentralDatabase.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM \"Requests\" WHERE \"request_id\" = ?"
            );
            ps.setInt(1, requestId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reqIdLabel.setText(String.valueOf(rs.getInt("request_id")));
                userIdLabel.setText(String.valueOf(rs.getInt("emp_id")));
                locationLabel.setText(rs.getString("location"));
                expenseTypeLabel.setText(rs.getString("expense_type"));
                costLabel.setText(String.valueOf(rs.getDouble("amount")));
                reasonLabel.setText(rs.getString("description"));
                receiptUrlLabel.setText(rs.getString("receipt_url"));
            } else {
                System.out.println("Request not found");
            }

        } catch (SQLException e) {
            System.out.println("Error loading request info: " + e.getMessage());
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

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE \"Requests\" SET \"status\" = ? WHERE \"request_id\" = ?"
            );

            ps.setString(1, newStatus);
            ps.setInt(2, requestId);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                System.out.println("Status updated to: " + newStatus);

                FxHelper.nextPage("managerDashboard-page.fxml", event);
            }

        } catch (SQLException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
    }
}
