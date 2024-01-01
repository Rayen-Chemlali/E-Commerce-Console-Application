package ecommerce.searching;

import ecommerce.appinterface.*;
import ecommerce.productmanagement.*;
import ecommerce.authentification.*;

import java.util.Scanner;

public class SearchingManager {

    public static void searchProducts() {
        Scanner scanner = new Scanner(System.in);

        boolean continueSearch = true;

        while (continueSearch) {
            System.out.println("Search Menu:");
            System.out.println("1. Category");
            System.out.println("2. Price Range");
            System.out.println("3. Rating");
            System.out.println("4. Brand");
            System.out.println("5. Availability");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ProductManager.showCategoryNames();
                    System.out.print("Enter category name for search: ");
                    String categoryName = scanner.nextLine();
                    if (ProductManager.getCategory(categoryName) != null) {
                        ProductManager.getCategory(categoryName).viewAllCategoryProducts();
                        //go to more detailed search in the category itself
                        searchCategoryProducts(ProductManager.getCategory(categoryName));
                    } else {
                        System.out.println("Unavailable Category");
                    }
                    break;
                case 2:
                    System.out.print("Enter minimum price for search: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price for search: ");
                    double maxPrice = scanner.nextDouble();
                    ProductManager.priceSearch(maxPrice, minPrice);
                    break;
                case 3:
                    System.out.print("Enter minimum rating for search: ");
                    float minRating = scanner.nextFloat();
                    ProductManager.ratingSearch(minRating);
                    break;
                case 4:
                    System.out.print("Enter brand for search: ");
                    String brand = scanner.nextLine();
                    ProductManager.brandSearch(brand);
                    break;
                case 5:
                    ProductManager.availabilitySearch();
                    break;
                case 6:
                    continueSearch = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        //if the logged user is a customer show the customer interface after displaying the search result
        if (AuthenticationService.getLoggedUser() instanceof Customer) {
            CustomerInterface.displayCustomerInterface();
        }
    }

    public static void searchCategoryProducts(ProductCategory category) {
        boolean continueSearch = true;
        Scanner scanner = new Scanner(System.in);
        while (continueSearch) {


            System.out.println("Category Search Menu for " + category.getCategoryName() + ":");
            System.out.println("1. Price Range");
            System.out.println("2. Rating");
            System.out.println("3. Brand");
            System.out.println("4. Availability");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter minimum price for search: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price for search: ");
                    double maxPrice = scanner.nextDouble();
                    category.categoryPriceSearch(maxPrice, minPrice);
                    break;
                case 2:
                    System.out.print("Enter minimum rating for search: ");
                    float minRating = scanner.nextFloat();
                    category.categoryRatingSearch(minRating);
                    break;
                case 3:
                    System.out.print("Enter brand for search: ");
                    String brand = scanner.nextLine();
                    category.categoryBrandSearch(brand);
                    break;
                case 4:
                    category.categoryAvailabilitySearch();
                    break;
                case 5:
                    continueSearch = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }
}



