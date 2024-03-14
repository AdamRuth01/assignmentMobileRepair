package org.example.manager;

import org.example.Main;

import org.example.models.MobileDevice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

import static java.sql.DriverManager.getConnection;

public class MobileDevicesMGR {
        static String url = "jdbc:mysql://localhost:3306/assignment_mobile_reparation";
        static String username = "root";
        static String password = "root";

        public void crudMobileDevicesMenu() throws SQLException {
            System.out.println("Menu selection CRUD for Mobile  Devices!");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Existing Mobile Devices\n");
                readDataFromTable();
                System.out.println();
                System.out.println(
                        "1: Create Table\n" +
                                "2: Insert Into Table\n" +
                                "3: Update From Table \n" +
                                "4: Delete Data From Table\n" +
                                "0: Exit Mobile Devices (Back to [main menu] !!! )");
                int mobileDeviceInputSelection = scanner.nextInt();
                mobileDeviceSelection(mobileDeviceInputSelection);
                if (mobileDeviceInputSelection == 0) {
                    Main.main(null);
                }
            }
        }

        private void mobileDeviceSelection(int customerInputSelection) {
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
            String sql = "CREATE TABLE mobile_devices (\n" +
                    "mobile_device_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "imei_number INTEGER NOT NULL," +
                    "phone_brand VARCHAR (255) NOT NULL," +
                    "model_number VARCHAR (255) NOT NULL" +
                    ");";
            System.out.println(sql);
            try (Connection conn = getConnection(url, username, password);
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("The table has been created!");
            } catch (SQLException e) {
                System.out.println("The table was mot created");
                e.printStackTrace();
            }
        }

        //OLD INSERT
        private void insert(MobileDevice obj){
            String sql = "INSERT INTO mobile_devices (imei_number,phone_brand,model_number)"+
                    "VALUES (?,?,?);";
            try (Connection conn = getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, obj.getImeiNumber());
                pstmt.setString(2, obj.getPhonebrand());
                pstmt.setString(3, obj.getModelNumber());
                pstmt.executeUpdate();
                System.out.println("Update success!.");
            } catch (SQLException e) {
                System.out.println("Update failed during insert statement");
                e.printStackTrace();
            }
        }
        private void insertIntoTable()   {
            MobileDevice obj = new MobileDevice();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please insert imei number: ");
            int imei = scanner.nextInt();
            scanner.nextLine();
            obj.setImeiNumber(imei);
            System.out.print("Please insert phone brand: ");
            String phoneBrand = scanner.nextLine();
            obj.setPhonebrand(phoneBrand);
            System.out.print("Please insert model number: ");
            String modelNumber = scanner.nextLine();
            obj.setModelNumber(modelNumber);
            insert(obj);

        }

        private List<MobileDevice> getAll() {
            var result = new ArrayList<MobileDevice>();
            try (Connection conn = getConnection(url,username, password);
                 Statement stmt = conn.createStatement()){
                var resultSet = stmt.executeQuery("SELECT * FROM mobile_devices;");
                while (resultSet.next()) {
                    var obj = new MobileDevice();

                    obj.setId(resultSet.getInt("mobile_device_id"));
                    obj.setImeiNumber(resultSet.getInt("imei_number"));
                    obj.setPhonebrand(resultSet.getString("phone_brand"));
                    obj.setModelNumber(resultSet.getString("model_number"));
                    result.add(obj);
                }
            } catch (SQLException e){
                System.out.println("Could not read");
                e.printStackTrace();
            }
            return result;
        }
        private void readDataFromTable() {
            List<MobileDevice> mobileDevices = getAll();
            for (MobileDevice obj: mobileDevices) {
                System.out.println(obj);
            }
        }

        private void updateDataIntoTable() {
            MobileDevice obj = new MobileDevice();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Chose mobile device (id) to update: ");
            int id = scanner.nextInt();
            obj.setId(id);
            scanner.nextLine();
            System.out.print("Enter the new (imei number): ");
            int imei = scanner.nextInt();
            obj.setImeiNumber(imei);
            scanner.nextLine();
            System.out.println("Enter the new (phone brand): ");
            String phoneBrand = scanner.nextLine();
            obj.setPhonebrand(phoneBrand);
            System.out.println("Enter (model number): ");
            String modelNumber = scanner.nextLine();
            obj.setModelNumber(modelNumber);

            String sql = "UPDATE mobile_devices SET imei_number = ?, phone_brand = ?, model_number = ? WHERE mobile_device_id = ?;";
            try (Connection conn = getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, obj.getImeiNumber());
                pstmt.setString(2, obj.getPhonebrand());
                pstmt.setString(3, obj.getModelNumber());
                pstmt.setInt(4, obj.getId());
                System.out.println("The updated ID: "+ obj.getId());
                System.out.println( "The updated name: "+ obj.getImeiNumber());
                System.out.println("The updated phone number: " + obj.getPhonebrand());
                System.out.println("The updated address: " + obj.getModelNumber());

                int affectedRows = pstmt.executeUpdate();
                System.out.println(affectedRows + "Update success!");
            } catch (SQLException e) {
                System.out.println("Could not update object!");
                e.printStackTrace();
            }
        }


        private void deleteDataFromTable() {
            MobileDevice obj = new MobileDevice();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Chose mobile Device (id) to delete: ");
            int id = scanner.nextInt();
            obj.setId(id);
            String sql = "DELETE FROM mobile_devices WHERE mobile_device_id = ?;";
            try (Connection conn = getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, obj.getId());
                int affectedRows = pstmt.executeUpdate();
                System.out.println("You deleted id: " + obj.getId());
                System.out.println(affectedRows + " deleted success!");
                // Ska jag ta bort affeted rows?
            } catch (SQLException e) {
                System.out.println("Could not update data.");
                e.printStackTrace();
            }
        }
    }






