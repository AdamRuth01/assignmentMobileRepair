package org.example.manager;


import org.example.Main;

import java.sql.*;
import java.util.Scanner;

import static org.example.manager.CustomersMGR.*;

public class MobileDevisesMGR {

    public static void crudMobileDevisesMenu() {
        System.out.println("Menu selection CRUD for Mobile Devises!");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Existing mobile Devises: \n");
            readDataFromTable();
            System.out.println();
            System.out.println(
                    "1: Create Table\n" +
                            "2: Insert Into Table\n" +
                            "3: Update From Table \n" +
                            "4: Delete Data From Table\n" +
                            "0: Exit Customers (Back to [main menu] !!! )");
            int mobileDevisesInputSelection = scanner.nextInt();
            mobileDevisesSelection(mobileDevisesInputSelection);
            if(mobileDevisesInputSelection == 0){
                Main.main(null);
            }
        }
    }

    private static void mobileDevisesSelection(int ignoredMobileDevisesInputSelection) {

        switch (ignoredMobileDevisesInputSelection){
            case 1:
                createTable();
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
    private static void createTable() {
        String sql = "CREATE TABLE mobile_devises (" +
                "mobile_devise_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "customer_id INTEGER NOT NULL," +
                "imei_number INTEGER NOT NULL," +
                "phone_brand VARCHAR (255)," +
                " model_number VARCHAR (255)," +

                "FOREIGN KEY (customer_id) REFERENCES customers(customer_id));";


        System.out.println(sql);
        try  (Connection conn = DriverManager.getConnection(url,username,password);
              Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("The table has been created!");
        }catch (SQLException e) {
            System.out.println("The table was mot created");
            e.printStackTrace();
        }
    }
    private static void insertIntoTable() {

        String sql = " INSERT INTO mobile_devises(customer_id,imei_number,phone_brand,model_number)\n" +
                "VALUES (?,?,?,?);";
        try  (Connection conn = DriverManager.getConnection(url,username,password);
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please insert customer (id): ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Please insert (imei number): ");
            int imeiNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Please insert (phone brand): \n");
            String phoneBrand = scanner.nextLine();
            System.out.println("Please insert (model number): ");
            String modelNumber = scanner.nextLine();

            pstmt.setInt(1, id);
            pstmt.setInt(2, imeiNumber);
            pstmt.setString(3, phoneBrand);
            pstmt.setString(4, modelNumber);

            pstmt.executeUpdate();
            System.out.println("Insert Succeeded ");
        }catch (SQLException e) {
            System.out.println("Failed during insert statement");
            e.printStackTrace();
        }

    }
    private static void readDataFromTable() {

        String sql = "SELECT customers.*, mobile_devises.*\n" +
                "FROM customers\n" +
                "JOIN mobile_devises ON customers.customer_id = mobile_devises.customer_id;";

        try (Connection conn = DriverManager.getConnection(url,username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("mobile_devise_id");
                String namn = rs.getString("customer_name");
                int imeinumber = rs.getInt("imei_number");
                String phoneBrand = rs.getString("phone_brand");
                String modelNumber = rs.getString("model_number");

                System.out.println("Mobile devise id: " + id + ", \n" +   "**" + "Customer name: "  + namn + "**" + ", \n"  + namn + "'s" +" " + "Phone information! \n" + "Imei number: " + imeinumber + ", \n" + "Phone brand: " + phoneBrand + ", \n" + "model number: " + modelNumber + "! \n" + "-----------------------" );
            }
        } catch (SQLException e) {
            System.out.println("Error Could not reade data.");
            e.printStackTrace();
        }
    }
    private static void updateDataIntoTable() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose (mobile devise id) to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter (imei number): ");
        int imei_number = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter (phone brand)");
        String phoneBrand = scanner.nextLine();
        System.out.println("Enter (model number)");
        String modelNumber = scanner.nextLine();
        String sql = "UPDATE mobile_devises SET imei_number = ?, phone_brand = ?, model_number = ? WHERE mobile_devise_id = ?;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, imei_number);
            pstmt.setString(3, phoneBrand);
            pstmt.setString(4, modelNumber);
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " update succeeded");
        } catch (SQLException e) {
            System.out.println("Update error, could not update");
            e.printStackTrace();
        }
    }
    // ska jag ha mobile devise id här istället för customer_id
    private static void deleteDataFromTable() {
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







