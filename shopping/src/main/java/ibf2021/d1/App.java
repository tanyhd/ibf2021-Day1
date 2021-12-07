package ibf2021.d1;

import java.io.BufferedReader;
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
                File userFile = new File ("c:\\data\\" + arguments + ".txt");

                if (!userFile.exists()) {
                    System.out.println("User" + arguments + " does not exist, new user created");
                    //File newFile = new File("c:\\data\\newFile.txt");
                    try {
                        userFile.createNewFile();
                        loginStatus = true;
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    System.out.println("Found user file");
                    loginStatus = true;
                    BufferedReader br = new BufferedReader(new FileReader(userFile));
                    String st;
        
                    while ((st=br.readLine()) != null) {
                        System.out.println(st);
                    }
                }
        
        
            } else if (command.equals("save")) {
                if (loginStatus == false) {
                    System.out.println("login first");
                } else {
                    try {
                        FileWriter myWriter = new FileWriter("c:\\data\\newFile.txt");
                        myWriter.write("Files in Java might be tricky, but it is fun enough!");
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                      } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                      }
                }

            } else if (!command.equals("add") || !command.equals("delete") || !command.equals("list") || !command.equals("exit") || !command.equals("login") || !command.equals("save")) {
                System.out.println("Incorrect command");
            } 
        }

    }
}
