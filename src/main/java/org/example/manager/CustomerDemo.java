package org.example.manager;

import org.example.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDemo {

        public static void customerMenu() throws SQLException {
            System.out.println("Menu selection CRUD for Customers!");
            Scanner scanner = new Scanner(System.in);
            CustomerDemo demo = new CustomerDemo(); // Create an instance of CustomerDemo
            while(true){
                System.out.println("Existing customers\n");
                // readDataFromTable();
                System.out.println();
                System.out.println(
                        "1: Create Table\n" +
                                "2: Insert Into Table\n" +
                                "3: Update From Table \n" +
                                "4: Delete Data From Table\n" +
                                "0: Exit Customers (Back to [main menu] !!! )");
                int customerInputSelection = scanner.nextInt();
                demo.customerSelection(customerInputSelection); // Call customerSelection on the instance
                if(customerInputSelection == 0){
                    Main.main(null);
                }
            }
        }

        private void customerSelection(int customerInputSelection) throws SQLException {

            switch (customerInputSelection){
                case 1:
                    createTables();
                    break;
                case 2:
                    //insertIntoTable();
                    break;
                case 3:
                    //updateDataIntoTable();
                    break;
                case 4:
                    // deleteDataFromTable();
                    break;
            }
        }



    private Connection conn;
    static final String url = "jdbc:mysql://localhost:3306/assignment_mobile_reparation";
    static final String username = "root";
    static final String password = "root";

    public CustomerDemo() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        createTables();



    }

    private void createTables() throws SQLException {
        String sql = "CREATE TABLE customers(" +
                "id int AUTO_INCREMENT PRIMARY KEY," +
                "customer_name VARCHAR(255) NOT NULL," +
                "customer_phone_number VARCHAR(255) NOT NULL," +
                "customer_adress VARCHAR(255));";
        System.out.println(sql);
        getConnection().createStatement().execute(sql);

    }

    protected Connection getConnection(){
        return conn;
    }
}
