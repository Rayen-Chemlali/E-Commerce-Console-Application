package ecommerce.productmanagement;
import java.util.Scanner;
import ecommerce.authentification.AuthenticationService;
public class ProductFeedback {
    private String username;
    private String review;
    private float rating;
    //constructor with user input

    public ProductFeedback() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to provide your name for the review? (yes/no): ");
        String choice = scanner.nextLine().toLowerCase();

        if ("yes".equals(choice)) {
            this.username = AuthenticationService.getLoggedUser().getUserName();
        } else {
            this.username = "Anonymous";
        }

        System.out.print("Enter your review: ");
        this.review = scanner.nextLine();

        System.out.print("Enter your rating(on 10): ");
        this.rating = scanner.nextFloat();
    }
    //another constructor
    public ProductFeedback(String username,String review, float rating)
    {
        this.rating=rating;
        this.review=review;
        this.username=username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
