package ecommerce.promotionservice;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import ecommerce.productmanagement.*;

public class PromtionManager {
    //key=product id/value=the promotion percentage
    private static HashMap<Integer, Double> listOfProductsInPromotion = new HashMap<>();
    //key=the dicount code/value=the promotion percentage
    private static HashMap<String, Double> listOfDicountCodes = new HashMap<>();

    public static void addProductPromotion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter promotion percentage: ");
        double promotion = scanner.nextDouble();
        Product product = ProductManager.getProduct(productId);
        //update the price
        product.setPrice(product.getPrice() * promotion / 100);
        listOfProductsInPromotion.put(product.getProductId(), promotion);
        System.out.println("Promotion Added");
    }

    public static void addDicountCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter discount code: ");
        String discountCode = scanner.next();
        System.out.print("Enter promotion percentage: ");
        double promotion = scanner.nextDouble();
        listOfDicountCodes.put(discountCode, promotion);
        System.out.println("Discount Code Added");
    }

    public static void addPromotionOrDiscountCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the type of code to add:");
        System.out.println("1. Product Promotion");
        System.out.println("2. Discount Code");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addProductPromotion();
                break;
            case 2:
                addDicountCode();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        Product product = ProductManager.getProduct(productId);
        //update the price to the price out of promotion
        product.setPrice(product.getPriceOutOfPromotion());
        listOfProductsInPromotion.remove(productId);
    }

    public static void removePromotionOrDicountCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the type of code to remove:");
        System.out.println("1. Product Promotion");
        System.out.println("2. Discount Code");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                removeProduct();
                break;
            case 2:
                removeDicountCode();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void removeDicountCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter discount code to remove: ");
        String discountCode = scanner.next();
        listOfDicountCodes.remove(discountCode);
        System.out.println("Discount Code Removed");
    }

    public static boolean isInPromtion(int productId) {
        return listOfProductsInPromotion.containsKey(productId);
    }

    public static double getPromtion(int productId) {
        return listOfProductsInPromotion.get(productId);
    }

    public static double verifyDiscountCode(double amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter discount code: ");
        String discountCode = scanner.next();

        // check if the discount code exists
        if (listOfDicountCodes.containsKey(discountCode)) {
            double discountPercentage = listOfDicountCodes.get(discountCode);
            // apply the discount to the amount
            double discountedAmount = amount - (amount * discountPercentage / 100);
            System.out.println("Discount applied successfully!");
            return discountedAmount;
        } else {
            // return the original amount if the discount code is not found
            System.out.println("Invalid discount code. No discount applied.");
            return amount;
        }
    }
}
