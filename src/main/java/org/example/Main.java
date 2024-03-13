package org.example;

import org.example.manager.CustomersMGR;
import org.example.manager.MobileDevisesMGR;
import org.example.manager.ReparationsMGR;

import java.util.Scanner;

public class Main {
    CustomersMGR customersMGR;
    MobileDevisesMGR mobileDevisesMGR;
    public Main() {
        customersMGR = new CustomersMGR();
    }

    public static void main(String[] args){
        Main runner = new Main();
        runner.menu();

    }//main
    public void menu(){
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
                System.out.println("Wrong input... \n" +
                        "Error please try again");
            }
        }
    }

    private void menuSelection(int selection) {
        switch(selection){
            case 1:
                customersMGR.crudCustomersMenu();
                break;
            case 2:
                MobileDevisesMGR.crudMobileDevisesMenu();
                break;
            case 6: ReparationsMGR.crudReparationMenu();
                break;

        }
    }//menuSelection

}//Main class