package ecommerce.authentification;
import java.util.Scanner;

public abstract class User {
    private String userName;
    private String password;
    private String emailAdress;
    //constructor with user input
    public User()
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the email:");
        this.emailAdress = scanner.nextLine();
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getPassword() {
        return password;
    }
}
