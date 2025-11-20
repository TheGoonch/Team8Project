package com.example.employeereimburseapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.*;

public class ManagerExamineController {

    public static int selectedRequestId;

    @FXML private Label reqIdLabel;
    @FXML private Label userIdLabel;
    @FXML private Label locationLabel;
    @FXML private Label expenseTypeLabel;
    @FXML private Label costLabel;
    @FXML private Label reasonLabel;
    @FXML private Label receiptUrlLabel;

    private int requestId;

    private ManagerDashController dashboardController;

    public void setDashboardController(ManagerDashController controller) {
        this.dashboardController = controller;
    }

    @FXML
    public void initialize() {
        requestId = selectedRequestId;
        loadRequestInfo();
    }

    private void loadRequestInfo() {
        try (Connection con = CentralDatabase.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM \"Request\" WHERE req_id = ?");
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reqIdLabel.setText(String.valueOf(rs.getInt("req_id")));
                userIdLabel.setText(String.valueOf(rs.getInt("user_id")));
                locationLabel.setText(rs.getString("location"));
                expenseTypeLabel.setText(rs.getString("expense_type"));
                costLabel.setText(String.valueOf(rs.getDouble("cost")));
                reasonLabel.setText(rs.getString("reason"));
                receiptUrlLabel.setText(rs.getString("reciept_url"));
            }

        } catch (SQLException e) {
            System.out.println("Error loading request info: " + e.getMessage());
        }
    }

    @FXML
    public void approve(ActionEvent event) {
        updateStatus("Approved");
    }

    @FXML
    public void reject(ActionEvent event) {
        updateStatus("Rejected");
    }

    private void updateStatus(String newStatus) {
        try (Connection con = CentralDatabase.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE \"Request\" SET status = ? WHERE req_id = ?");
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                if (dashboardController != null) {
                    dashboardController.reloadRequests();
                }

                Stage stage = (Stage) reqIdLabel.getScene().getWindow();
                stage.close();
            }

        } catch (SQLException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
    }
}
