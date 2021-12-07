package ibf2021.d1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println("Welcome to your shopping cart");

        Cart cart = new Cart();
        boolean exit = false;
        boolean loginStatus = false;
        String userName = "";
        
        while (!exit) {
           
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            String arguments = scanner.nextLine();
            

            if (command.equals("list")) {
                cart.list();
                
            } else if (command.equals("add")) {
                cart.add(arguments);

            } else if (command.equals("delete")) {
                cart.delete(arguments);

            } else if (command.equals("exit")) {
                exit = true;

            } else if (command.equals("login")) {
                userName = arguments.trim();
                File userFile = new File ("c:\\data\\" + userName + ".txt");

                if (!userFile.exists()) {
                    System.out.println(userName + " your cart is empty");
                    try {
                        userFile.createNewFile();
                        loginStatus = true;
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    System.out.println(userName + ", your cart contains the following items");
                    loginStatus = true;

                    // reading items in file and while loop to add into cart
                    BufferedReader br = new BufferedReader(new FileReader(userFile));
                    String itemsInFile;
                    while ((itemsInFile = br.readLine()) != null) {
                        cart.addWithoutPrint(itemsInFile);
                    }
                    cart.list();
                }
        
        
            } else if (command.equals("save")) {
                if (loginStatus == false) {
                    System.out.println("login first");
                } else {
                    try {
                        new FileWriter("c:\\data\\" + userName + ".txt", false).close();
                        BufferedWriter writer = new BufferedWriter(new FileWriter("c:\\data\\" + userName + ".txt", true));
                        // for loop to loop through cart items and write to file
                        List<String> itemList = cart.listItemsInCart();
                        for (int i = 0; i < itemList.size(); i++) {
                            writer.write(itemList.get(i));
                            writer.newLine();
                        }
                        writer.close();
                        System.out.println("Your cart has been saved. User logged out.");
                      } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                      }
                    cart.deleteItemsInCart();
                    loginStatus = false;
                }

            } else if (command.equals("users")) {
                //Creating a File object for directory
                File directoryPath = new File("c:\\data");
                //List of all files and directories
                File filesList[] = directoryPath.listFiles();
                System.out.println("The following users are registered");

                for (int i = 0; i < filesList.length; i++) {
                    System.out.println((i + 1) + ". " + filesList[i].getName());
                }
            
            } else if (!command.equals("add") || !command.equals("delete") || !command.equals("list") || !command.equals("exit") || !command.equals("login") || !command.equals("save") || !command.equals("users")) {
                System.out.println("Incorrect command");
            }
        }

    }
}
