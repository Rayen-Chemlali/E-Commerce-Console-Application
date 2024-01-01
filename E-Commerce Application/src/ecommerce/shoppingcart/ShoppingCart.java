package ecommerce.shoppingcart;

import ecommerce.authentification.Customer;
import ecommerce.productmanagement.*;

import java.util.HashMap;
import java.util.Scanner;

public class ShoppingCart {
    //key=product/value=quantity
    private HashMap<Product, Integer> listOfProducts;
    private Customer customer;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.listOfProducts = new HashMap<>();
    }

    public void addProduct(int productId, int quantity) {
        Product product = ProductManager.getProduct(productId);
        if ((product.isAvailability()) && (quantity <= product.getStockQuantity())) {
            listOfProducts.put(product, quantity);
        } else {
            System.out.println("Sorry,product out of stock.");
        }

    }

    public void removeProduct(int productId) {
        Product product = ProductManager.getProduct(productId);
        if (listOfProducts.containsKey(product)) {
            listOfProducts.remove(product);
        } else {
            System.out.println("Unavailable product in the shopping cart");
        }

    }

    public void updateQuantity(int productId, int newQuantity) {
        Product product = ProductManager.getProduct(productId);
        if (listOfProducts.containsKey(product)) {
            listOfProducts.put(product, newQuantity);
        } else {
            System.out.println("Unavailable product in the shopping cart");
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : listOfProducts.keySet()) {
            total += product.getPrice() * listOfProducts.get(product);
        }
        return total;
    }

    public void viewShoppingCart() {
        System.out.println("Shopping Cart");
        for (Product product : listOfProducts.keySet()) {
            System.out.println(product.getName() + "(" + listOfProducts.get(product) + ")");
        }
    }

    public HashMap<Product, Integer> getListOfProducts() {
        return listOfProducts;
    }

    public void menu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nShopping Cart Menu:");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. Update Quantity in Cart");
            System.out.println("4. View Shopping Cart");
            System.out.println("5. View Total Price");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProductToCart();
                    break;
                case 2:
                    removeProductFromCart();
                    break;
                case 3:
                    updateQuantityInCart();
                    break;
                case 4:
                    viewShoppingCart();
                    break;
                case 5:
                    viewTotalPrice();
                    break;
                case 6:
                    System.out.println("Exiting Shopping Cart Menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    public void setListOfProducts(HashMap<Product, Integer> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public void addProductToCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ProductID((the number just before the product name) to add: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        addProduct(productId, quantity);
        System.out.println("Product added to cart successfully.");
    }

    public void removeProductFromCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID(the number just before the product name) to remove: ");
        int productId = scanner.nextInt();
        removeProduct(productId);
        System.out.println("Product removed from cart successfully.");
    }

    public void updateQuantityInCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID(the number just before the product name) to update quantity: ");
        int productId = scanner.nextInt();
        System.out.print("Enter New Quantity: ");
        int newQuantity = scanner.nextInt();
        updateQuantity(productId, newQuantity);
        System.out.println("Quantity updated in cart successfully.");
    }

    public void viewTotalPrice() {
        double totalPrice = getTotalPrice();
        System.out.println("Total Price in Cart: " + totalPrice);
    }

    public boolean isEmpty() {
        return listOfProducts.isEmpty();
    }

}
