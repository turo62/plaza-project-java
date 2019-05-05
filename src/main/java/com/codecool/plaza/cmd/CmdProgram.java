package com.codecool.plaza.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.codecool.plaza.api.PlazaImpl;
import com.codecool.plaza.api.Shop;
import com.codecool.plaza.api.ShopImpl;
import com.codecool.plaza.exceptions.*;
import com.codecool.plaza.product.*;

class CmdProgram {
    private List<Product> cart;
    private List<Float> prices;
    private Scanner sc = new Scanner(System.in);
    private PlazaImpl plaza = null;
    private List<Shop> shops = new ArrayList<>();
    
    private final String[] commands = new String[] {
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
    
    void run() throws PlazaIsClosedException, ShopAlreadyExistsException, NoSuchShopException {
        System.out.println("There is no plaza available yet! Press\n" +
                            "1) to open new plaza \n" +
                            "2) to enter existing plaza \n" +
                            "0) to exit");
    
        while (true) {
            try {
                System.out.println("Select option: \n");
                String input = sc.nextLine();
                int choice = Integer.parseInt(input);
                
                switch (choice) {
                    case 1:
                        System.out.println("Enter plaza name: \n");
                        String plazaName = sc.nextLine();
                        System.out.println("Enter owner's name: \n");
                        String ownerName = sc.nextLine();
    
                        PlazaImpl plaza = new PlazaImpl(plazaName, ownerName);
    
                        System.out.println("The " + plaza.getName() + " plaza was succesfully built. \n");
    
                        loadPlazaMenu(plaza);
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
    
    private void loadPlazaMenu(PlazaImpl plaza) throws PlazaIsClosedException, ShopAlreadyExistsException, NoSuchShopException{
    
        final String[] commands = new String[] {
            "list all shops",
            "add a new shop",
            "remove an existing shop",
            "enter a shop by name",
            "open the plaza",
            "close the plaza",
            "check if the plaza is open or not",
            "leave plaza"
        };
        
        System.out.println("Welcome to " + plaza.getName() + " plaza.\n");
        
        while (true) {
            printMenu(commands);
            System.out.println("\n Enter menu key. \n");
            String input = sc.nextLine();
            int choice = Integer.parseInt(input);
            
            try {
                switch (choice) {
                    case 1:
                        loadAllShops(plaza);
                        break;
                    case 2:
                        System.out.println("Enter name of shop to add: \n");
                        String name = sc.nextLine();
                        System.out.println("Enter owner of shop: \n");
                        String owner = sc.nextLine();
                        ShopImpl shop = new ShopImpl(name, owner);
                        plaza.addShop(shop);
                        System.out.println(shop.getName() + " is created.");
                        break;
                    case 3:
                        System.out.println("Enter name of shop you want to remove: \n");
                        String rmName = sc.nextLine();
                        System.out.println("Enter owner of that shop: \n");
                        String rmOwner = sc.nextLine();
                        ShopImpl rmShop = new ShopImpl(rmName, rmOwner);
                        plaza.removeShop(rmShop);
                        System.out.println("The shop is removed. \n");
                        break;
                    case 4:
                    case 5:
                        plaza.open();
                        System.out.println("\n" + plaza.getName() + " is open. \n");
                        break;
                    case 6:
                    case 7:
                    case 8:
                        //savePlaza();
                        System.exit(0);
                    default:
                        System.out.println("Please, enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid menu point.");
            }
        }
    }
    
    private void printMenu(String[] commands) {
        int count = 1;
        
        for (String command : commands) {
            System.out.println(count + ") " + command);
            count++;
        }
    }
    
    private void loadAllShops(PlazaImpl plaza) {
        try {
            List<Shop> shops = plaza.getShops();
            
            if (plaza.isOpen()) {
                System.out.println("Shops available in plaza: \n");
                for (Shop shop: shops) {
                    System.out.println(shop);
                }
            }
        } catch (PlazaIsClosedException e) {
            System.out.println("Plaza is still closed.");
        }
    }
    
    /*private void addShop(PlazaImpl plaza) {
        
        try {
            System.out.println(plaza.getShops().size());
            System.out.println("Enter name of shop: \n");
            String name = sc.nextLine();
            System.out.println("Enter owner of shop: \n");
            String owner = sc.nextLine();
            ShopImpl shop = new ShopImpl(name, owner);
            plaza.addShop(shop);
            System.out.println(shop.getName() + " is successfully created.");
            return;
        } catch (PlazaIsClosedException e) {
            System.out.println("Plaza closed. ");
        } catch (ShopAlreadyExistsException sa) {
            System.out.println("Shop already exists in plaza.");
        }
    }*/
    
    private void openPlaza(PlazaImpl plaza) {
        plaza.open();
        System.out.println(plaza.getName() + " is open now.");
    }
}
