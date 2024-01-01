package ecommerce.authentification;
import java.util.Scanner;
import java.util.HashMap;

public class AuthenticationService {
    //key=username/value=admin

    private static HashMap<String, Admin> adminUsers = new HashMap<>();
    //key=username/value=customer
    private static HashMap<String, Customer> customerUsers = new HashMap<>();
    private static User loggedUser;
    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        String confirmedPassword;
        String password;
        String username;

        System.out.println("Sign Up as an Admin(A) or Customer(C) ?");
        String role = scanner.nextLine().toUpperCase();
        while (true) {

            System.out.println("Enter username:");
            username = scanner.nextLine();
            System.out.println("Enter password:(at least 8 characters long)");
            password = scanner.nextLine();
            System.out.println("Confirm password:");
            confirmedPassword = scanner.nextLine();
            if(password.length()<8)
            {
                System.out.println("The password must be at least 8 characters long!");
            }
            else if(!confirmedPassword.equals(password))
            {
                System.out.println("Please enter the same password!");
            }
            else
            {
                break;
            }
        }

        switch (role) {
            case "A":
                if (!adminUsers.containsKey(username)) {
                    Admin newAdmin = new Admin();
                    newAdmin.setPassword(password);
                    newAdmin.setUserName(username);
                    adminUsers.put(username, newAdmin);
                    loggedUser = newAdmin;
                    System.out.println("Admin signup successful. Welcome, " + loggedUser.getUserName() + "!");
                } else {
                    System.out.println("Admin signup failed. Username already exists.");
                }
                break;
            case "C":
                if (!customerUsers.containsKey(username)) {
                    Customer newCustomer = new Customer();
                    newCustomer.setUserName(username);
                    newCustomer.setPassword(password);
                    customerUsers.put(username, newCustomer);
                    loggedUser = newCustomer;
                    System.out.println("Customer signup successful. Welcome, " + loggedUser.getUserName() + "!");
                } else {
                    System.out.println("Customer signup failed. Username already exists.");
                }
                break;
            default:
                System.out.println("Invalid role. Signup failed.");
        }
    }
    public static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login as an Admin(A) or Customer(C) ?");
        String role = scanner.nextLine().toUpperCase();

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        switch (role) {
            case "A":
                if (adminUsers.containsKey(username) && adminUsers.get(username).getPassword().equals(password)) {
                    loggedUser = adminUsers.get(username);
                    System.out.println("Admin login successful. Welcome, " + loggedUser.getUserName() + "!");
                } else {
                    System.out.println("Admin login failed. Incorrect username or password.");
                }
                break;
            case "C":
                if (customerUsers.containsKey(username) && customerUsers.get(username).getPassword().equals(password)) {
                    loggedUser = customerUsers.get(username);
                    System.out.println("Customer login successful. Welcome, " + loggedUser.getUserName()+ " !");
                } else {
                    System.out.println("Customer login failed. Incorrect username or password.");
                }
                break;
            default:
                System.out.println("Invalid role. Login failed.");
        }
    }

    public static void logout() {
        if (loggedUser != null) {
            System.out.println("Logging out user: " + loggedUser.getUserName());
            loggedUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
    public static User getLoggedUser() {
        return loggedUser;
    }
    public static void addCustomer()
    {
        Scanner scanner = new Scanner(System.in);
        Customer customer=new Customer();
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        customer.setPassword(password);
        customer.setUserName(username);
        customerUsers.put(customer.getUserName(),customer);
    }
    public static void addAdmin()
    {
        Scanner scanner = new Scanner(System.in);
        Admin admin=new Admin();
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        admin.setPassword(password);
        admin.setUserName(username);
        adminUsers.put(admin.getUserName(),admin);
    }
    public static void removeAdmin(String adminUserName)
    {
        adminUsers.remove(adminUserName);
    }
    public static void removeCustomer(String customerUserName)
    {
        customerUsers.remove(customerUserName);
    }
}
