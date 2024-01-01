package ecommerce.productmanagement;
import java.util.*;

public class ProductCategory {
    private int maxSales = -1;
    private int idMaxSales = -1;
    private String categoryName;
    //key=productId/value=product
    private HashMap<Integer,Product> productsOfTheCaetgory;

    public ProductCategory()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the category: ");
        this.categoryName = scanner.nextLine();
        this.productsOfTheCaetgory=new HashMap<>();
    }
    public ProductCategory(String categoryName)
    {
        this.categoryName = categoryName;
        this.productsOfTheCaetgory=new HashMap<>();
    }

    public String getCategoryName() {
        return categoryName;
    }
    public HashMap<Integer,Product> getProductsOfTheCaetgory()
    {
        return productsOfTheCaetgory;
    }

    public void removeProduct(int idProduct)
    {
        productsOfTheCaetgory.remove(idProduct);
    }
    public void addProduct(Product product)
    {
        productsOfTheCaetgory.put(product.getProductId(),product);
    }
    public Product getProduct(int productId)
    {
        for(int id:productsOfTheCaetgory.keySet())
        {
            if(id==productId)
            {
                return productsOfTheCaetgory.get(id);
            }
        }
        return null;
    }
    public void viewAllCategoryProducts()
    {
        System.out.println("The Category of "+categoryName+" :");
        for (int id:productsOfTheCaetgory.keySet())
        {
            System.out.println(productsOfTheCaetgory.get(id).getNecessaryProductDetails());
        }
    }
    public void categoryPriceSearch(double maxPrice,double minPrice)
    {

        for (int id:productsOfTheCaetgory.keySet())
        {
            if((productsOfTheCaetgory.get(id).getPrice()<=maxPrice)&&(productsOfTheCaetgory.get(id).getPrice()>=minPrice))
                  System.out.println(productsOfTheCaetgory.get(id).getNecessaryProductDetails());
        }

    }
    public void categoryBrandSearch(String brand)
    {
        for (int id:productsOfTheCaetgory.keySet())
        {
            if((productsOfTheCaetgory.get(id).getBrand()==brand))
                System.out.println(productsOfTheCaetgory.get(id).getNecessaryProductDetails());
        }
    }
    public void categoryRatingSearch(float minRating)
    {
        for (int id:productsOfTheCaetgory.keySet())
        {
            if((productsOfTheCaetgory.get(id).getGeneralRating()>=minRating))
                System.out.println(productsOfTheCaetgory.get(id).getNecessaryProductDetails());
        }
    }
    public void categoryAvailabilitySearch()
    {
        for (int id:productsOfTheCaetgory.keySet())
        {
            if((productsOfTheCaetgory.get(id).isAvailability()))
                System.out.println(productsOfTheCaetgory.get(id).getNecessaryProductDetails());
        }
    }

    public void setBestSeller() {

        for (int id : productsOfTheCaetgory.keySet()) {
            int currentSales = productsOfTheCaetgory.get(id).getNumberOfSales();

            if (currentSales >= maxSales) {
                maxSales = currentSales;
                idMaxSales = id;
            }
        }
    }
    public void showBestSeller()
    {
        setBestSeller();
        System.out.println(categoryName+": Best Seller-");
        if(idMaxSales!=-1)
        System.out.println(productsOfTheCaetgory.get(idMaxSales).getNecessaryProductDetails());
    }


}
