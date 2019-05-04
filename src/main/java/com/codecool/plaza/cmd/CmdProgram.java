package com.codecool.plaza.cmd;

import java.util.List;
import java.util.Scanner;

import com.codecool.plaza.api.PlazaImpl;
import com.codecool.plaza.product.*;

class CmdProgram {
    private List<Product> cart;
    private List<Float> prices;
    private Scanner sc = new Scanner(System.in);
    private final String[] plazaMenu = new String[] {
        "list all shops",
        "add a new shop",
        "remove an existing shop",
        "enter a shop by name",
        "open the plaza",
        "close the plaza",
        "check if the plaza is open or not",
        "leave plaza"
    };
    
    private final String[] shopmenu = new String[] {
        "list available products",
        "find products by name",
        "display the shop's owner",
        "open the shop",
        "close the shop",
        "add new product to the shop",
        "add existing products to the shop",
        "buy a product by barcode",
        "buy multiple products to cart",
        "check price by barcode",
        "show stock by barcode",
        " leave shop to plaza"
    };

    CmdProgram(String[] args) {
    }
    
    void run() {
        System.out.println("There is no plaza opened yet! Press\n" +
                            "1) to open new plaza \n" +
                            "2) to enter existing plaza \n" +
                            "0) to exit");
    
        while (true) {
            try {
                System.out.println("Select option: \n");
                String input = sc.nextLine();
                int choice = Integer.parseInt(input);
                
                switch ((choice)) {
                    case 1:
                        createNewPlaza();
                        break;
                    case 2:
                    case 0:
                        System.out.println("See you again!");
                        System.exit(0);
                    default:
                        System.out.println("Please, enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid menu point.");
            }
        }
    }
    
    private void createNewPlaza() {
        System.out.println("Enter plaza name: \n");
        String plazaName = sc.nextLine();
        System.out.println("Enter owner's name: \n");
        String ownerName = sc.nextLine();
    
        PlazaImpl plaza = new PlazaImpl(plazaName, ownerName);
    
        System.out.println("Welcome to " + plaza.getName() + ". Press \n");
    }
}
