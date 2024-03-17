package org.example.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Scanner;

public class DBConnectionBase_ {

    private Connection conn;
   //AWS conn // static String url = "jdbc:mysql://databaseaws.c3gwgeyewp8u.us-east-1.rds.amazonaws.com:3306/assignment_mobile_reparation";
   //AWS conn // static String username = "aws_admin";
   //AWS conn // static String password = "MWUwlqOUJ84AHfhwo7Zw";
   static String url = "jdbc:mysql://localhost:3306/assignment_mobile_reparation";
   static String username = "root";
   static String password = "root";

    public DBConnectionBase_()  {

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("-------------------------------------------");
            e.printStackTrace();
            System.out.println("-------------------------------------------");
            throw new RuntimeException(e);
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection cn) {
        this.conn = cn;
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


    protected Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
