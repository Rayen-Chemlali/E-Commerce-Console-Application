package ecommerce.paymentprocessing;
import java.util.Scanner;
public class CreditCard implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter credit card number: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Payment successful! Amount of " + amount + " DT has been successfully processed using Credit Card "+cardNumber);

        }
}
