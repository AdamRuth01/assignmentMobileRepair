package org.example.manager;

import org.example.Main;
import org.example.models.Reparation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

import static java.sql.DriverManager.getConnection;

public class ReparationsMGR extends DBConnectionBase_{


    public ReparationsMGR(){
        super();
    }

    public void crudReparationMenu() throws SQLException {
            System.out.println("Menu selection CRUD for reparations!");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Existing reparations\n");
                readDataFromTable();
                System.out.println();
                System.out.println(
                        "1: Create Table\n" +
                                "2: Insert Into Table\n" +
                                "3: Update From Table \n" +
                                "4: Delete Data From Table\n" +
                                "0: Exit Mobile Devices (Back to [main menu] !!! )");
                int reparationInputSelection = scanner.nextInt();
                reparationSelection(reparationInputSelection);
                if (reparationInputSelection == 0) {
                    Main.main(null);
                }
            }
        }

        private void reparationSelection(int reparationInputSelection) {
            switch (reparationInputSelection) {
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
            String sql = "CREATE TABLE reparations(\n" +
                    "    reparation_id INTEGER AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    customer_id INTEGER NOT NULL,\n" +
                    "    mobile_device_id INTEGER NOT NULL,\n" +
                    "    employee_first_name VARCHAR (255) NOT NULL,\n" +
                    "    employee_last_name VARCHAR (255) NOT NULL,\n" +
                    "    employee_number INTEGER NOT NULL,\n" +
                    "    reparation_start_date DATETIME NOT NULL, \n" +
                    "    reparation_end_date DATETIME NOT NULL,   \n" +
                    "    repairation_status VARCHAR (255) NOT NULL,\n" +
                    "    reparation_description VARCHAR (555) NOT NULL,\n" +
                    "    images VARCHAR (255),\n" +
                    "    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),\n" +
                    "    FOREIGN KEY (mobile_device_id) REFERENCES mobile_devices(mobile_device_id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "\n";
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
        private void insert(Reparation obj){
            String sql = "INSERT INTO reparations (customer_id, mobile_device_id, employee_first_name, employee_last_name, employee_number, reparation_start_date, reparation_end_date, repairation_status, reparation_description, images)"+
                    "VALUES (?,?,?,?,?,?,?,?,?,?);";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, obj.getCustomerId());
                pstmt.setInt(2, obj.getMobileDeviceId());
                pstmt.setString(3, obj.getEmployeeFirstName());
                pstmt.setString(4, obj.getEmployeeLastName());
                pstmt.setInt(5, obj.getEmployeeNumber());
                pstmt.setDate(6, obj.getReparationStartDate());
                pstmt.setDate(7, obj.getReparationEndDate());
                pstmt.setString(8, obj.getReparationStatus());
                pstmt.setString(9, obj.getReparationDescription());
                pstmt.setString(10, obj.getImages());
                pstmt.executeUpdate();
                System.out.println("Update success!.");
            } catch (SQLException e) {
                System.out.println("Update failed during insert statement");
                e.printStackTrace();
            }
        }

    private void insertIntoTable() {
            try{
            Reparation obj = new Reparation();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Please insert customer id: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();
            obj.setCustomerId(customerId);

            System.out.print("Please insert mobile device id: ");
            int mobileDeviceId = scanner.nextInt();
            scanner.nextLine();
            obj.setMobileDeviceId(mobileDeviceId);

            System.out.print("Please insert employee first name: ");
            String employeeFirstName = scanner.nextLine();
            obj.setEmployeeFirstName(employeeFirstName);

            System.out.print("Please insert employee last name: ");
            String employeeLastName = scanner.nextLine();
            obj.setEmployeeLastName(employeeLastName);

            System.out.print("Please insert employee number: ");
            int employeeNumber = scanner.nextInt();
            scanner.nextLine();
            obj.setEmployeeNumber(employeeNumber);

            System.out.print("Please insert reparation start date (yyyy-mm-dd): ");
            String startDate = scanner.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = format.parse(startDate);
            java.sql.Date sqlStartDate = new java.sql.Date(parsedStartDate.getTime());
            obj.setReparationStartDate(sqlStartDate);

            System.out.print("Please insert reparation start date (yyyy-mm-dd): ");
            String endDate = scanner.nextLine();
            format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedEndDate = format.parse(endDate);
            java.sql.Date sqlEndDate = new java.sql.Date(parsedEndDate.getTime());
            obj.setReparationEndDate(sqlEndDate);

            System.out.print("Please insert reparation status: ");
            String reparationStatus = scanner.nextLine();
            obj.setReparationStatus(reparationStatus);

            System.out.print("Please insert reparation description: ");
            String reparationDescription = scanner.nextLine();
            obj.setReparationDescription(reparationDescription);

            System.out.print("Please insert images: ");
            String images = scanner.nextLine();
            obj.setImages(images);

            insert(obj);
        }catch (ParseException e){
                System.out.println("The update was failed");
                e.printStackTrace();
            }
    }




    private List<Reparation> getAll() {
        var result = new ArrayList<Reparation>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()){
            var resultSet = stmt.executeQuery(" SELECT \n" +
                    " customers.customer_id, \n" +
                    " mobile_devices.mobile_device_id, \n" +
                    "    customers.customer_name, \n" +
                    "    customers.customer_phone_number, \n" +
                    "    mobile_devices.imei_number, \n" +
                    "    reparations.employee_first_name, \n" +
                    "    reparations.employee_last_name, \n" +
                    "    reparations.employee_number, \n" +
                    "    reparations.reparation_start_date, \n" +
                    "    reparations.reparation_end_date, \n" +
                    "    reparations.repairation_status, \n" +
                    "    reparations.reparation_description,\n" +
                    "    reparations.images\n" +
                    "FROM \n" +
                    "    reparations\n" +
                    "JOIN \n" +
                    "    customers ON reparations.customer_id = customers.customer_id\n" +
                    "JOIN \n" +
                    "    mobile_devices ON reparations.mobile_device_id = mobile_devices.mobile_device_id;\n");

            while (resultSet.next()) {
                var obj = new Reparation();

                obj.setCustomerId(resultSet.getInt("customer_id"));
                obj.setMobileDeviceId(resultSet.getInt("mobile_device_id"));
                obj.setEmployeeFirstName(resultSet.getString("employee_first_name"));
                obj.setEmployeeLastName(resultSet.getString("employee_last_name"));
                obj.setEmployeeNumber(resultSet.getInt("employee_number"));
                obj.setReparationStartDate(resultSet.getDate("reparation_start_date"));
                obj.setReparationEndDate(resultSet.getDate("reparation_end_date"));
                obj.setReparationStatus(resultSet.getString("repairation_status"));
                obj.setReparationDescription(resultSet.getString("reparation_description"));
                obj.setImages(resultSet.getString("images"));

                result.add(obj);
            }
        } catch (SQLException e){
            System.out.println("Could not read");
            e.printStackTrace();
        }
        return result;
    }

    private void readDataFromTable() {
            List<Reparation> reparation = getAll();
            for (Reparation obj: reparation) {
                System.out.println(obj);
            }
        }

    private void updateDataIntoTable() {
        Reparation obj = new Reparation();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Chose reparation (id) to update: ");
        int id = scanner.nextInt();
        obj.setId(id);
        scanner.nextLine();

        System.out.print("Enter the new employee first name: ");
        String employeeFirstName = scanner.nextLine();
        obj.setEmployeeFirstName(employeeFirstName);

        System.out.print("Enter the new employee last name: ");
        String employeeLastName = scanner.nextLine();
        obj.setEmployeeLastName(employeeLastName);

        System.out.print("Please insert images: ");
        String images = scanner.nextLine();
        obj.setImages(images);

        String sql = "UPDATE reparations\n" +
                "SET employee_first_name = ?, employee_last_name = ?, images = ?" +
                "WHERE reparation_id = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getEmployeeFirstName());
            pstmt.setString(2, obj.getEmployeeLastName());
            pstmt.setString(3, obj.getImages());
            pstmt.setInt(4, obj.getId());

            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " rows updated. Update success!");
        } catch (SQLException e) {
            System.out.println("Could not update object!");
            e.printStackTrace();
        }
    }


    private void deleteDataFromTable() {
            Reparation obj = new Reparation();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Chose mobile reparation (id) to delete: ");
            int id = scanner.nextInt();
            obj.setId(id);
            String sql = "DELETE FROM mobile_devices WHERE mobile_device_id = ?;";
            try (Connection conn = getConnection();
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

