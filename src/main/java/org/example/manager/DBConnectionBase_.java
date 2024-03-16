package org.example.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Scanner;

public class DBConnectionBase_ {

    private Connection conn;
    private Scanner scanner;
    static String url = "jdbc:mysql://localhost:3306/assignment_mobile_reparation";
    static String username = "root";
    static String password = "root";

    public DBConnectionBase_()  {

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected DBConnectionBase_(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
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


    protected Connection getConnection(){
        return conn;
    }
}
