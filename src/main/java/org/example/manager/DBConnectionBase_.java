package org.example.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Scanner;

public class DBConnectionBase_ {

    private Connection conn;
    static String url = "jdbc:mysql://localhost:3306/assignment_mobile_reparation";
    static String username = "root";
    static String password = "root";

    public DBConnectionBase_() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    private void creatTable() throws SQLException {
        String sql = "CREATE TABLE customers(" +
                "id int AUTO_INCREMENT PRIMARY KEY," +
                "customer_name VARCHAR(255) NOT NULL," +
                "customer_phone_number VARCHAR(255) NOT NULL," +
                "customer_adress VARCHAR(255));";
        System.out.println(sql);
        try {
            getConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected Scanner getScanner() {
        return new Scanner(System.in);
    }

    protected Connection getConnection(){
        return conn;
    }
}
