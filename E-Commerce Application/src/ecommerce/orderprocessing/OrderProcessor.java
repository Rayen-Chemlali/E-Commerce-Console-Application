package ecommerce.orderprocessing;
import java.util.Date;
import java.util.HashMap;
import ecommerce.authentification.Customer;
import ecommerce.paymentprocessing.*;
import ecommerce.productmanagement.*;
import ecommerce.promotionservice.PromtionManager;

import java.util.Scanner;

public class OrderProcessor {
    //key=orderId/value=order
    private static HashMap<Integer,Order> listOfOrders=new HashMap<>();
    private static int orderId=1;
    public static void passNewOrder(Customer customer) {
        if (!customer.getShoppingCart().isEmpty()) {
            Order order = new Order(customer, orderId++);
            // Add the order to the customer's order history
            customer.getOrderHistory().add(order);
            listOfOrders.put(order.getOrderId(), order);
            order.setOrderStatus("New");
            System.out.println("Order passed successfully. Now you can manage it:");

            Scanner scanner = new Scanner(System.in);
            boolean validChoice = false;

            while (!validChoice) {
                System.out.println("\n1. Pay for the Order");
                System.out.println("2. Cancel the Order");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        payForOrder(order);
                        validChoice = true;
                        break;
                    case 2:
                        cancelOrder(order);
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void payForOrder(Order order) {
        double totalPrice = order.getTotalAmount();
        // Ask the user if they want to enter a discount code
        totalPrice = PromtionManager.verifyDiscountCode(totalPrice);
        System.out.println("Total Price: " + totalPrice + " DT (Every product is shipped for 2 DT)");

        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;

        while (!validChoice) {
            System.out.print("Do you want to confirm payment? (yes/no): ");
            String userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("yes")) {
                PaymentService.pay(totalPrice);
                order.setOrderStatus("Order paid successfully");
                for (Product product : order.getListOfProducts().keySet()) {
                    product.decreaseInStock(order.getListOfProducts().get(product));
                    product.increaseNumberOfSales(product.getNumberOfSales() + order.getListOfProducts().get(product));
                }
                validChoice = true;
            } else if (userChoice.equals("no")) {
                order.setOrderStatus("Order cancelled");
                System.out.println("Order cancelled!");
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            }
        }
    }

    private static void cancelOrder(Order order) {
        order.setOrderStatus("Order cancelled");
        System.out.println("Order cancelled!");
    }

    public static void shipOrder(int orderId,String shippingDate)
    {
        //here the admin himself will put the orderId for the shipped order
        Order order=listOfOrders.get(orderId);
        order.setShippingDate(shippingDate);
        order.setOrderStatus("Shipped");
        System.out.println("Order shipped successfully!");
    }
    public static void showAllOrders()
    {
        for (int orderId:listOfOrders.keySet())
        {
            listOfOrders.get(orderId).showOrderDetails();
        }
    }

}
