package ecommerce.appinterface;
import ecommerce.*;
import ecommerce.authentification.*;
import ecommerce.productmanagement.ProductManager;
import ecommerce.searching.SearchingManager;

import java.util.Scanner;

public class HomePage {
    public static void displayHomePage()
    {
        System.out.println("\t\t\t\t\t\tWelcome to RC E-Commerce Application!");
        ProductManager.showBestSellers();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Home Page!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Search");
            System.out.println("4. Exit");

            System.out.print("Please choose an option (1-4): ");
            int choice = scanner.nextInt();
            User loggedUser;
            switch (choice) {
                case 1:
                    AuthenticationService.signUp();
                    //depending on the logged user display the convenient interface
                    loggedUser=AuthenticationService.getLoggedUser();
                    if (( loggedUser instanceof Admin)&&(loggedUser!=null)) {
                        AdminInterface.displayAdminInterface();
                    } else if (( loggedUser instanceof Customer)&&(loggedUser!=null)) {
                        ProductManager.showBestSellers();
                        CustomerInterface.displayCustomerInterface();
                    }
                    break;
                case 2:
                    AuthenticationService.login();
                    //depending on the logged user display the convenient interface
                    loggedUser=AuthenticationService.getLoggedUser();
                    if (( loggedUser instanceof Admin)&&(loggedUser!=null)) {
                        AdminInterface.displayAdminInterface();
                    } else if (( loggedUser instanceof Customer)&&(loggedUser!=null)) {
                        ProductManager.showBestSellers();
                        CustomerInterface.displayCustomerInterface();
                    }
                    break;
                case 3:
                    SearchingManager.searchProducts();
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
