package ecommerce.productmanagement;
import java.util.HashMap;
import java.util.Scanner;
import ecommerce.authentification.AuthenticationService;
public class ProductManager {
    private static int productIdCounter = 1;
    //key=category name/value=productcategory(two categories can't have the same name)
    private static HashMap<String, ProductCategory> productCategories = new HashMap<>();
    //key=productId/value=product
    private static HashMap<Integer, Product> outOfStockProducts = new HashMap<>();
    public static ProductCategory getCategory(String categoryName)
    {
        return productCategories.get(categoryName);
    }
    //Initialize some products and product categories
    static
    {
        addNewCategory("Electronics");
        addNewCategory("Clothing");
        addNewCategory("Books");
        addNewCategory("Home Appliances");


        addNewProduct("Laptop", "Powerful laptop for work", "Electronics", 1200.0, 50, "BrandX", true);
        addNewProduct("Smartphone", "Latest smartphone model", "Electronics", 800.0, 100, "BrandY", true);
        addNewProduct("Headphones", "High-quality headphones", "Electronics", 150.0, 200, "BrandZ", true);

        addNewProduct("T-Shirt", "Comfortable cotton t-shirt", "Clothing", 25.0, 300, "FashionBrand", true);
        addNewProduct("Jeans", "Classic denim jeans", "Clothing", 50.0, 150, "DenimCo", true);
        addNewProduct("Sneakers", "Stylish sneakers", "Clothing", 80.0, 100, "Footwear", true);

        addNewProduct("Java Programming", "Learn Java programming", "Books", 40.0, 50, "TechBooks", true);
        addNewProduct("Fiction Novel", "Exciting fiction novel", "Books", 20.0, 80, "BookCo", true);
        addNewProduct("Cookbook", "Delicious recipes for cooking", "Books", 30.0, 40, "ChefEdition", true);

        addNewProduct("Coffee Maker", "Automatic coffee maker", "Home Appliances", 100.0, 30, "KitchenTech", true);
        addNewProduct("Vacuum Cleaner", "Powerful vacuum cleaner", "Home Appliances", 120.0, 20, "CleanTech", true);
        addNewProduct("Toaster", "Quick toaster for breakfast", "Home Appliances", 40.0, 50, "KitchenTech", true);

    }
    //With user input
    public static void addNewCategory()
    {
        ProductCategory category=new ProductCategory();
        productCategories.put(category.getCategoryName(),category);
    }
    public static void addNewCategory(String categoryName)
    {
        ProductCategory category=new ProductCategory(categoryName);
        productCategories.put(category.getCategoryName(),category);
    }
    public static void removeCategory(String categoryName)
    {
        productCategories.remove(categoryName);
    }
    public static void updateOutOfStockProducts()
    {
        for(String categoryName:productCategories.keySet())
        {
            HashMap<Integer,Product> productsOfTheCategory=productCategories.get(categoryName).getProductsOfTheCaetgory();
            for(int productId:productsOfTheCategory.keySet())
            {
                Product product=productsOfTheCategory.get(productId);
                if (product.isAvailability()==false)
                {
                    outOfStockProducts.put(productId,product);
                }
            }

        }
    }
    public static void addNewProduct()
    {
        int productId=productIdCounter++;
        Product newProduct=new Product(productId);
    }
    public static void addNewProduct(String name,String description,String categoryName,double price,int stockQuantity,String brand, boolean availability)
    {
        int productId=productIdCounter++;
        Product newProduct=new Product( productId,name, description, categoryName, price, stockQuantity,brand,availability);
    }
    public static void removeProduct(int productId)
    {
        for (String categorieName : productCategories.keySet()) {
            if (productCategories.get(categorieName).getProduct(productId)!=null)
            {
                productCategories.get(categorieName).removeProduct(productId);
            }
        }
    }
    public static void updateProductQuantityInStock(int productId,int additionnalQuantity)
    {
        Product product=null;
        for (String categorieName : productCategories.keySet()) {
            if (productCategories.get(categorieName).getProduct(productId)!=null)
            {
                product=productCategories.get(categorieName).getProduct(productId);
            }
        }
        if (product!=null) {
            product.setStockQuantity(product.getStockQuantity() + additionnalQuantity);
        }
        else{
            System.out.println("Non existing product");
        }
    }
    public static boolean isCategoryExistant(String categoryName)
    {
        return productCategories.containsKey(categoryName);
    }
    public static void updateProduct(int productId)
    {
        Product product=null;
        for (String categorieName : productCategories.keySet()) {
            if (productCategories.get(categorieName).getProduct(productId)!=null)
            {
                product=productCategories.get(categorieName).getProduct(productId);
            }
        }
        if (product!=null) {
            product.showAllProductDetails();
            product.updateProduct();
        }
        else{
            System.out.println("Non existing product");
        }
    }
    public static void showAllOutOfStockProducts()
    {
            for(int id:outOfStockProducts.keySet())
            {
                outOfStockProducts.get(id).showAllProductDetails();
            }

    }
    public static Product getProduct(int productId)
    {
        Product product=null;
        for (String categorieName : productCategories.keySet()) {
            if (productCategories.get(categorieName).getProduct(productId)!=null)
            {
                product=productCategories.get(categorieName).getProduct(productId);
            }
        }
        return product;
    }
    public static void showAllProducts()
    {
        for(String categoryName:productCategories.keySet())
        {
            productCategories.get(categoryName).viewAllCategoryProducts();
        }
    }
    public static void priceSearch(double minPrice,double maxPrice)
    {
        for (String categorieName : productCategories.keySet()) {
            productCategories.get(categorieName).categoryPriceSearch(maxPrice,minPrice);
        }
    }
    public static void brandSearch(String brand)
    {
        for (String categorieName : productCategories.keySet()) {
            productCategories.get(categorieName).categoryBrandSearch(brand);
        }
    }
    public static void availabilitySearch()
    {
        for (String categorieName : productCategories.keySet()) {
            productCategories.get(categorieName).categoryAvailabilitySearch();
        }
    }
    public static void ratingSearch(float minRating)
    {
        for (String categorieName : productCategories.keySet()) {
            productCategories.get(categorieName).categoryRatingSearch(minRating);
        }
    }
    public static void showBestSellers()
    {
        for (String categorieName : productCategories.keySet()) {
            productCategories.get(categorieName).showBestSeller();
        }
    }
    public static void showCategoryNames()
    {
        for (String categorieName : productCategories.keySet()) {
            System.out.println(categorieName);
        }
    }
}
