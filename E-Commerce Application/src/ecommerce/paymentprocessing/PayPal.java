package ecommerce.paymentprocessing;
import java.util.Scanner;
public class PayPal implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PayPal email: ");
        String payPalEmail = scanner.nextLine();
        System.out.println("Payment successful! Amount of " + amount + " DT has been successfully processed using PayPal account"+payPalEmail);

    }
}
