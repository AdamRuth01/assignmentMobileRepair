package org.example.manager;

import org.example.Main;

import java.sql.*;
import java.util.Scanner;

public class CustomersMGR {
    static String url = "jdbc:mysql://localhost:3306/assignment_mobile_reparation";
    static String username = "root";
    static String password = "root";

    public void crudCustomersMenu() {


        System.out.println("Menu selection CRUD for Customers!");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Existing customers\n");
            readDataFromTable();
            System.out.println();
            System.out.println(
                    "1: Create Table\n" +
                            "2: Insert Into Table\n" +
                            "3: Update From Table \n" +
                            "4: Delete Data From Table\n" +
                            "0: Exit Customers (Back to [main menu] !!! )");
            int customerInputSelection = scanner.nextInt();
            customerSelection(customerInputSelection);
            if(customerInputSelection == 0){
                Main.main(null);
            }
        }
    }

    private void customerSelection(int customerInputSelection) {


        switch (customerInputSelection){
            case 1:
                creatTable();
                break;
            case 2:
                insertIntoTable();
                break;
            case 3:
                updateDataIntoTable();
                break;
            case 4:
                deleteDataFromTable();
                break;



        }
    }

    private void creatTable() {
        String sql = "CREATE TABLE customers(" +
                "id int AUTO_INCREMENT PRIMARY KEY," +
                "customer_name VARCHAR(255) NOT NULL," +
                "customer_phone_number VARCHAR(255) NOT NULL," +
                "customer_adress VARCHAR(255));";
        System.out.println(sql);
        /*if (1 == 1){
            return;
        }*/
        try  (Connection conn = DriverManager.getConnection(url,username,password);
              Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("The table has been created!");
        }catch (SQLException e) {
            System.out.println("The table was mot created");
            e.printStackTrace();
        }



    }

    private void insertIntoTable() {

        String sql = " INSERT INTO customers (customer_name,customer_phone_number,customer_adress)\n" +
                "VALUES (?,?,?);";
        try  (Connection conn = DriverManager.getConnection(url,username,password);
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please insert customer name  : ");
            String name = scanner.nextLine();
            System.out.print("Please insert customer phone number name  : ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Please insert customer adress  : ");
            String adress = scanner.nextLine();

            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, adress);
            pstmt.executeUpdate();
            System.out.println("En post har lagts till i tabellen.");
        }catch (SQLException e) {
            System.out.println("Faild during insert statement");
            e.printStackTrace();
        }

    }

    private void readDataFromTable() {

        String sql = "SELECT * FROM customers;";

        try (Connection conn = DriverManager.getConnection(url,username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String namn = rs.getString("customer_name");
                String phoneNumber = rs.getString("customer_phone_number");
                String adress = rs.getString("customer_adress");
                System.out.println(id + ", " + namn + ", " + phoneNumber + ", " + adress);
            }
        } catch (SQLException e) {
            System.out.println("Kunde inte läsa data.");
            e.printStackTrace();
        }
    }


    private void updateDataIntoTable() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose customer_id to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the new name: ");
        String name = scanner.nextLine();
        String sql = "UPDATE customers SET customer_name = ? WHERE customer_id = ?;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,name);
            pstmt.setInt(2, id);
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " poster uppdaterades.");
        } catch (SQLException e) {
            System.out.println("Kunde inte uppdatera data.");
            e.printStackTrace();
        }
    }




    private void deleteDataFromTable() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose customer_id to delete: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM customers WHERE customer_id = ?;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " poster uppdaterades.");
        } catch (SQLException e) {
            System.out.println("Kunde inte uppdatera data.");
            e.printStackTrace();
        }

    }


}
