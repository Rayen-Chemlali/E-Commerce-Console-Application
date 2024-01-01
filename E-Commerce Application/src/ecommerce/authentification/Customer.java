package ecommerce.authentification;
import ecommerce.orderprocessing.*;
import ecommerce.paymentprocessing.PaymentService;
import ecommerce.productmanagement.ProductManager;
import ecommerce.shoppingcart.*;
import ecommerce.wishlist.Wishlist;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    ShoppingCart shoppingCart;
    Wishlist wishlist;
    String phoneNumber;

    String shippingAddress;
    private List<Order> orderHistory;
    //constructor with user input
    public Customer()
    {
        super();
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter  phone number: ");
        this.phoneNumber=scanner.nextLine();
        System.out.print("Enter  shipping address: ");
        this.shippingAddress=scanner.nextLine();
        orderHistory=new ArrayList<>();
        shoppingCart=new ShoppingCart(this);
        wishlist=new Wishlist(this);
    }
    public void passOrder()
    {
        OrderProcessor.passNewOrder(this);
    }
    public void addToWishlist(int productId,int quantity)
    {
        wishlist.addProduct(productId,quantity);
    }
    public void removeFromWishlist(int productId)
    {
        wishlist.removeProduct(productId);

    }
    public void updateProductInWishList(int productId,int newQuantity)
    {
        wishlist.updateQuantity(productId,newQuantity);
    }
    public void moveFromWishlistToShoppingCart()
    {
        wishlist.moveToShoppingCart();
    }
    public void viewWishlist()
    {
        wishlist.viewWishlist();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }
    public void wishListMenu()
    {
        wishlist.menu();
    }
    public void shoppingCartMenu()
    {
        shoppingCart.menu();
    }
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
    public void giveFeedback()
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Give the productId(the number just before the product name)");
        int productId=scanner.nextInt();
        ProductManager.getProduct(productId).addFeedbacks();
    }
}
