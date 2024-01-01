package ecommerce.appinterface;
import ecommerce.promotionservice.*;
import ecommerce.authentification.*;
import ecommerce.orderprocessing.*;
import ecommerce.productmanagement.*;
import ecommerce.searching.*;
import java.util.Scanner;
public class AdminInterface {
    private static PromtionManager PromotionManager;

    public static void displayAdminInterface()
    {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nAdmin Interface:");
            System.out.println("1. Ship order");
            System.out.println("2. Show all orders");
            System.out.println("3. Add new customer");
            System.out.println("4. Remove customer");
            System.out.println("5. Add new admin");
            System.out.println("6. Remove admin");
            System.out.println("7. Add new product category");
            System.out.println("8. Remove product category");
            System.out.println("9. Add new product");
            System.out.println("10. Remove product");
            System.out.println("11. Update product quantity in stock");
            System.out.println("12. Update product details");
            System.out.println("13. Show all out-of-stock products");
            System.out.println("14. Add a promotion or dicount code");
            System.out.println("15. Remove a promotion or dicount code");
            System.out.println("16. Logout");
            System.out.println("17.Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter order ID: ");
                    int orderId = scanner.nextInt();
                    System.out.print("Enter shipping date (yyyy-MM-dd): ");
                    String shippingDate = scanner.nextLine();
                    OrderProcessor.shipOrder(orderId, shippingDate);
                    break;
                case 2:
                    OrderProcessor.showAllOrders();
                    break;
                case 3:
                    AuthenticationService.addCustomer();
                    break;
                case 4:
                    System.out.print("Enter customer username to remove: ");
                    String customerUsernameToRemove = scanner.next();
                    AuthenticationService.removeCustomer(customerUsernameToRemove);
                    break;
                case 5:
                    AuthenticationService.addAdmin();
                    break;
                case 6:
                    System.out.print("Enter admin username to remove: ");
                    String adminUsernameToRemove = scanner.next();
                    AuthenticationService.removeAdmin(adminUsernameToRemove);
                    break;
                case 7:
                    ProductManager.addNewCategory();
                    break;
                case 8:
                    System.out.print("Enter category name to remove: ");
                    String categoryNameToRemove = scanner.next();
                    ProductManager.removeCategory(categoryNameToRemove);
                    break;
                case 9:
                    ProductManager.addNewProduct();
                    break;
                case 10:
                    System.out.print("Enter product ID to remove: ");
                    int productIdToRemove = scanner.nextInt();
                    ProductManager.removeProduct(productIdToRemove);
                    break;
                case 11:
                    System.out.print("Enter product ID: ");
                    int productIdToUpdate = scanner.nextInt();
                    System.out.print("Enter additional quantity: ");
                    int additionalQuantity = scanner.nextInt();
                    ProductManager.updateProductQuantityInStock(productIdToUpdate, additionalQuantity);
                    break;
                case 12:
                    System.out.print("Enter product ID to update: ");
                    int productIdToUpdateDetails = scanner.nextInt();
                    ProductManager.updateProduct(productIdToUpdateDetails);
                    break;
                case 13:
                    ProductManager.showAllOutOfStockProducts();
                    break;
                case 14:
                    PromotionManager.addPromotionOrDiscountCode();
                    break;
                case 15:
                    PromotionManager.removePromotionOrDicountCode();
                    break;
                case 16:
                    AuthenticationService.logout();
                    //go back to the home page
                    HomePage.displayHomePage();
                    break;
                case 17:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }
}
