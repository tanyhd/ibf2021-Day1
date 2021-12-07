package ibf2021.d1;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private List<String> shoppingCart = new ArrayList<String>();

    public void list () {
        if (this.shoppingCart.size() <= 0) {
            System.out.println("Your cart is empty");
        } else {
            for (int i = 0; i < this.shoppingCart.size(); i++) {
                System.out.println((i + 1) + ". " + this.shoppingCart.get(i));
            }
        }
    }

    public void add (String arguments) {
        String[] items = arguments.trim().split(", ");

                for (int i = 0; i < items.length; i++) {
                
                    if (this.shoppingCart.contains(items[i])) {
                        System.out.println("You have " + items[i] + " in your cart"); 
                    } else {
                        System.out.println(items[i] + " added to cart");
                        this.shoppingCart.add(items[i]);
                    }
                }
    }

    public void delete (String arguments) {
        int deleteIndex = Integer.parseInt(arguments.trim()) - 1;
        //System.out.println(shoppingCart.size());
        if (deleteIndex < 0 || deleteIndex >= shoppingCart.size()) {
        System.out.println("Incorrect item index");
        } else {
        System.out.println(shoppingCart.get(deleteIndex) + " removed from cart");
        shoppingCart.remove(deleteIndex);
        }
    }

}
