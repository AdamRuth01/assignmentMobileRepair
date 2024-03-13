package org.example;

import org.example.manager.CustomersMGR;
import org.example.manager.MobileDevicesMGR;
import org.example.manager.ReparationsMGR;

import java.util.Scanner;

public class Main {
    static CustomersMGR customersMGR = new CustomersMGR();
    static MobileDevicesMGR mobileDevisesMGR = new MobileDevicesMGR();
    public static void main(String[] args){
        menu();


    }//main
    public static void menu(){
        System.out.println("* mobile reparation system  *");
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                System.out.println("Pleas select a table!\n" +
                        "1: Customers\n" +
                        "2: Mobile Devises\n" +
                        "3: Reparations\n" +
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

    private static void menuSelection(int selection) {
        switch(selection){
            case 1:
                customersMGR.crudCustomersMenu();
                break;
            case 2:
                MobileDevicesMGR.crudMobileDevisesMenu();
                break;

            case 6: ReparationsMGR.crudReparationMenu();
                break;

        }
    }//menuSelection

}//Main class