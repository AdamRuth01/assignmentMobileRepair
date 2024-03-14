package org.example;

import org.example.manager.CustomerDemo;
import org.example.manager.CustomersMGR;
import org.example.manager.MobileDevicesMGR;
import org.example.manager.ReparationsMGR;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static CustomersMGR customersMGR = new CustomersMGR();
    static MobileDevicesMGR mobileDevisesMGR = new MobileDevicesMGR();
    static ReparationsMGR reparationsMGR = new ReparationsMGR();
    public static void main(String[] args) throws SQLException {
        menu();


    }//main
    public static void menu() throws SQLException {
        System.out.println("* mobile reparation system  *");
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                System.out.println("Please select a table!\n" +
                        "1: Customers\n" +
                        "2: Mobile Devises\n" +
                        "3: Reparations\n" +
                        "4: CustomerDemo\n" +
                        "0: Exit menu");
                int input = scanner.nextInt();
                menuSelection(input);
                if (input == 0){
                    return;
                }
            }catch (Exception e){
                throw e;

            }
        }
    }

    private static void menuSelection(int selection) throws SQLException {
        switch(selection){
            case 1:
                customersMGR.crudCustomersMenu();
                break;
            case 2:
                MobileDevicesMGR.crudMobileDevisesMenu();
                break;
            case 3: ReparationsMGR.crudReparationMenu();
                break;
            case 4:
                CustomerDemo.customerMenu();


        }
    }//menuSelection

}//Main class