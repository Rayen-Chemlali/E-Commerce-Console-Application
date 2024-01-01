package ecommerce.paymentprocessing;
import java.util.Scanner;
public class PaymentService {
    private static PaymentStrategy paymentStrategy=null;
    public static void pay(double amount) {
        choosePaymentMethod();

        if (paymentStrategy!=null){
            paymentStrategy.pay(amount);
        }
        else{
            System.out.println("Payment failed!!");
        }
    }

    private static void choosePaymentMethod() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How would you like to pay? (Credit Card/PayPal/Carte Edinar)");
        String paymentMethod = scanner.nextLine().toLowerCase();

        switch (paymentMethod) {
            case "credit card":
                paymentStrategy = new CreditCard();
                break;
            case "paypal":
                paymentStrategy = new PayPal();
                break;
            case "carte edinar":
                paymentStrategy = new CarteEDinar();
                break;
            default:
                System.out.println("Invalid payment method");
        }
    }
}