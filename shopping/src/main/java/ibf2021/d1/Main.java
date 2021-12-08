package ibf2021.d1;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to your shopping cart");
        Cart cart = new Cart();
        ShoppingCartDB shoppingCartDB = new ShoppingCartDB();
        if (args.length == 0){
            shoppingCartDB.setDefaultFileDirectory();
            shoppingCartDB.checkDirectory();
        } else {
            shoppingCartDB.setFileDirectory(args[0]);
            shoppingCartDB.checkDirectory();
        }
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
                loginStatus = shoppingCartDB.login(userName, loginStatus, cart);
        
            } else if (command.equals("save")) {
                loginStatus = shoppingCartDB.save(loginStatus, userName, cart);

            } else if (command.equals("users")) {
               shoppingCartDB.users();
            
            } else if (!command.equals("add") || !command.equals("delete") || !command.equals("list") || !command.equals("exit") || !command.equals("login") || !command.equals("save") || !command.equals("users")) {
                System.out.println("Incorrect command");
            }
        }

    }
}
