package ibf2021.d1;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        List<String> shoppingCart = new ArrayList<String>();
        System.out.println("Welcome to your shopping cart");

        boolean exit = false;
        Console cons = System.console();
        
        while (!exit) {
           
            String userInput = cons.readLine();

            if (userInput.startsWith("list")) {
                if (shoppingCart.size() <= 0) {
                    System.out.println("Your cart is empty");
                } else {
                    for (int i = 0; i < shoppingCart.size(); i++) {
                        System.out.println((i + 1) + ". " + shoppingCart.get(i));
                    }
                }
            } else if (userInput.startsWith("add")) {
                String str = userInput.substring(3, userInput.length());
                String[] items = str.trim().split(", ");

                for (int i = 0; i < items.length; i++) {
                
                    if (shoppingCart.contains(items[i])) {
                        System.out.println("You have " + items[i] + " in your cart"); 
                    } else {
                        System.out.println(items[i] + " added to cart");
                        shoppingCart.add(items[i]);
                    }
                }

            } else if (userInput.startsWith("delete")) {
                String[] items = userInput.split(" ");
                int deleteIndex = Integer.parseInt(items[1]) - 1;
                //System.out.println(shoppingCart.size());
                if (deleteIndex < 0 || deleteIndex >= shoppingCart.size()) {
                    System.out.println("Incorrect item index");
                } else {
                    System.out.println(shoppingCart.get(deleteIndex) + " removed from cart");
                    shoppingCart.remove(deleteIndex);
                }

            } else if (userInput.startsWith("exit")) {
                exit = true;
            } else if (!userInput.startsWith("add") || !userInput.startsWith("delete") || !userInput.startsWith("list") || !userInput.startsWith("exit")){
                System.out.println("Incorrect command");
            }
        }

    }
}
