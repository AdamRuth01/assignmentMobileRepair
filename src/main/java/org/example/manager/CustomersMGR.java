package org.example.manager;


import org.example.Main;
import org.example.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class CustomersMGR extends DBConnectionBase_ {

    public CustomersMGR()  {

        super();
    }

    public void crudCustomersMenu() throws SQLException {
        System.out.println("Menu selection CRUD for Customers!");
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
            if (customerInputSelection == 0) {
                Main.main(null);
            }
        }
    }

    private void customerSelection(int customerInputSelection) {
        switch (customerInputSelection) {
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
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("The table has been created!");
        } catch (SQLException e) {
            System.out.println("The table was mot created");
            e.printStackTrace();
        }
    }




    //OLD INSERT
    public void insert(Customer obj) {
        String sql = " INSERT INTO customers (customer_name,customer_phone_number,customer_adress)\n" +
                "VALUES (?,?,?);";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getName());
            pstmt.setString(2, obj.getPhoneNumber());
            pstmt.setString(3, obj.getAddress());
            pstmt.executeUpdate();
            System.out.println("The post has been inserted.");
        } catch (SQLException e) {
            System.out.println("Failed during insert statement");
            e.printStackTrace();
        }
    }

    private void insertIntoTable() {
        Customer obj = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert customer name  : ");
        String name = scanner.nextLine();
        obj.setName(name);
        System.out.print("Please insert customer phone number name  : ");
        String phoneNumber = scanner.nextLine();
        obj.setPhoneNumber(phoneNumber);
        System.out.print("Please insert customer address  : ");
        String adress = scanner.nextLine();
        obj.setAddress(adress);
        insert(obj);

    }

    public List<Customer> getAll() {
        var result = new ArrayList<Customer>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            var resultSet = stmt.executeQuery("SELECT * FROM customers;");
            while (resultSet.next()) {
                var obj = new Customer();

                obj.setId(resultSet.getInt("customer_id"));
                obj.setName(resultSet.getString("customer_name"));
                obj.setPhoneNumber(resultSet.getString("customer_phone_number"));
                obj.setAddress(resultSet.getString("customer_adress"));
                result.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("Kunde inte läsa data.");
            e.printStackTrace();
        }
        return result;
    }

    private void readDataFromTable() {
        List<Customer> customers = getAll();
        for (Customer obj : customers) {
            System.out.println(obj);
        }
    }

   public void updateDataIntoTable() {
        Customer obj = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose customer_id to update: ");
        int id = scanner.nextInt();
        obj.setId(id);
        scanner.nextLine();
        System.out.print("Enter the new name: ");
        String name = scanner.nextLine();
        obj.setName(name);
        System.out.println("Enter the new phone number: ");
        String phoneNumber = scanner.nextLine();
        obj.setPhoneNumber(phoneNumber);
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        obj.setAddress(address);

        String sql = "UPDATE customers SET customer_name = ?, customer_phone_number = ?,customer_adress = ? WHERE customer_id = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getName());
            pstmt.setString(2, obj.getPhoneNumber());
            pstmt.setString(3, obj.getAddress());
            pstmt.setInt(4, obj.getId());
            System.out.println("The updated ID: " + obj.getId());
            System.out.println("The updated name: " + obj.getName());
            System.out.println("The updated phone number: " + obj.getPhoneNumber());
            System.out.println("The updated address: " + obj.getAddress());

            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " poster uppdaterades.");
        } catch (SQLException e) {
            System.out.println("Kunde inte uppdatera data.");
            e.printStackTrace();
        }
    }


    public void deleteDataFromTable() {
        Customer obj = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose customer_id to delete: ");
        int id = scanner.nextInt();
        obj.setId(id);
        String sql = "DELETE FROM customers WHERE customer_id = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getId());
            int affectedRows = pstmt.executeUpdate();
            System.out.println("You deleted id: " + obj.getId());
            System.out.println(affectedRows + " deleted success!");
            // Ska jag ta bort affeted rows?
        } catch (SQLException e) {
            System.out.println("Could not delete inserted data.");
            e.printStackTrace();
        }
    }
}







