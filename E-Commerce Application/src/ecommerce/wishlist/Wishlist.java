package ecommerce.wishlist;
import java.util.Scanner;
import ecommerce.authentification.Customer;
import ecommerce.productmanagement.*;
import java.util.HashMap;

public class Wishlist {
    //key=product/value=quantity
    private HashMap<Product, Integer> listOfProducts;
    private Customer customer;

    public Wishlist(Customer customer) {
        this.customer = customer;
        this.listOfProducts = new HashMap<>();
    }

    public void addProduct(int productId, int quantity) {
        Product product = ProductManager.getProduct(productId);
        if ((product.isAvailability()) && (quantity <= product.getStockQuantity())) {
            listOfProducts.put(product, quantity);
        } else {
            System.out.println("Sorry, the product is out of stock.");
        }
    }

    public void removeProduct(int productId) {
        Product product = ProductManager.getProduct(productId);
        if (listOfProducts.containsKey(product)) {
            listOfProducts.remove(product);
        } else {
            System.out.println("Unavailable product in the wishlist.");
        }
    }

    public void updateQuantity(int productId, int newQuantity) {
        Product product = ProductManager.getProduct(productId);
        if (listOfProducts.containsKey(product)) {
            listOfProducts.put(product, newQuantity);
        } else {
            System.out.println("Unavailable product in the wishlist.");
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : listOfProducts.keySet()) {
            total += product.getPrice() * listOfProducts.get(product);
        }
        return total;
    }

    public void viewWishlist() {
        System.out.println("Wishlist");
        for (Product product : listOfProducts.keySet()) {
            System.out.println(product.getName() + "(" + listOfProducts.get(product) + ")");
        }
    }

    public HashMap<Product, Integer> getListOfProducts() {
        return listOfProducts;
    }
    public void  moveToShoppingCart()
    {
        customer.getShoppingCart().setListOfProducts(listOfProducts);
        System.out.println("Wishlist products move to the shopping cart");
    }
    public void menu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nWishlist Menu:");
            System.out.println("1. Add Product to Wishlist");
            System.out.println("2. Remove Product from Wishlist");
            System.out.println("3. Update Quantity in Wishlist");
            System.out.println("4. View Wishlist");
            System.out.println("5. Move Wishlist to Shopping Cart");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProductToWishlist();
                    break;
                case 2:
                    removeProductFromWishlist();
                    break;
                case 3:
                    updateQuantityInWishlist();
                    break;
                case 4:
                    viewWishlist();
                    break;
                case 5:
                    this.moveToShoppingCart();
                    break;
                case 6:
                    System.out.println("Exiting Wishlist Menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    private void addProductToWishlist() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID(the number just before the product name) to add: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        addProduct(productId, quantity);
        System.out.println("Product added to wishlist successfully.");
    }

    private void removeProductFromWishlist() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID(the number just before the product name) to remove: ");
        int productId = scanner.nextInt();
        removeProduct(productId);
        System.out.println("Product removed from wishlist successfully.");
    }

    private void updateQuantityInWishlist() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID(the number just before the product name) to update quantity: ");
        int productId = scanner.nextInt();
        System.out.print("Enter New Quantity: ");
        int newQuantity = scanner.nextInt();
        updateQuantity(productId, newQuantity);
        System.out.println("Quantity updated in wishlist successfully.");
    }
}
