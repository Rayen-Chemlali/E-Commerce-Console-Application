package ecommerce.paymentprocessing;

import java.util.Scanner;

public class CarteEDinar implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Carte E-Dinar number: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Payment successful! Amount of " + amount + " DT has been successfully processed using Carte E-Dinar "+cardNumber);

    }

}
