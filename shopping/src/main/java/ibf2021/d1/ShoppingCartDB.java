package ibf2021.d1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ShoppingCartDB {

    public boolean save (boolean loginStatus, String userName, Cart cart) {
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
        return false;
    }

    public void users () {
         //Creating a File object for directory
         File directoryPath = new File("c:\\data");
         //List of all files in directories
         File filesList[] = directoryPath.listFiles();
         System.out.println("The following users are registered");

         for (int i = 0; i < filesList.length; i++) {
             System.out.println((i + 1) + ". " + filesList[i].getName());
         }
    }

    public boolean login (String userName, boolean loginStatus, Cart cart) throws IOException {
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
        return loginStatus;
    }
}
