package com.example.employeereimburseapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CentralDatabase {
    public static final String url = "jdbc:postgresql://ep-raspy-glade-ahx0yn1q-pooler.c-3.us-east-1.aws.neon.tech:5432/postgres";
    public static final String user = "neondb_owner";
    public static final String password = "npg_Gr4h0nFXcxtv";

    public  static Connection con;

    public static Connection getConnection(){
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        return con;
    }


}
