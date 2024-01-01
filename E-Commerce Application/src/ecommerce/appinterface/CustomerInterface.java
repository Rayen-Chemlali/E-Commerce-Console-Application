package ecommerce.appinterface;

import ecommerce.authentification.AuthenticationService;
import ecommerce.authentification.Customer;
import ecommerce.orderprocessing.*;
import ecommerce.productmanagement.ProductManager;
import ecommerce.searching.SearchingManager;


import java.util.Scanner;

public class CustomerInterface {
    public static void displayCustomerInterface() {
        Scanner scanner = new Scanner(System.in);

        Customer currentCustomer = (Customer) AuthenticationService.getLoggedUser();

        do {
            System.out.println("\n");
            System.out.println("1. View Shopping Cart Menu");
            System.out.println("2. View Wishlist Menu");
            System.out.println("3. View Order History");
            System.out.println("4. Pass New Order");
            System.out.println("5. Search");
            System.out.println("6. Give feedback about a product");
            System.out.println("7. See all product details");
            System.out.println("8. Logout");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentCustomer.shoppingCartMenu();
                    break;
                case 2:
                    currentCustomer.wishListMenu();
                    break;
                case 3:
                    System.out.println("Order History:");
                    for (Order order : currentCustomer.getOrderHistory()) {
                        order.showOrderDetails();
                    }
                    break;
                case 4:
                    currentCustomer.passOrder();
                    break;
                case 5:
                    SearchingManager.searchProducts();
                    break;
                case 6:
                    currentCustomer.giveFeedback();
                    break;
                case 7:
                    System.out.println("Give the productId (the number just before the product name)");
                    int productId = scanner.nextInt();
                    ProductManager.getProduct(productId).showAllProductDetailsForCustomer();
                    break;
                case 8:
                    AuthenticationService.logout();
                    // Go back to the home page
                    HomePage.displayHomePage();
                    break;
                case 9:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

}
