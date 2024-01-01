# E-Commerce Console Application

## Overview

This Java-based console application serves as the final project for the Object-Oriented Programming course, showcasing a comprehensive e-commerce system. The application encompasses various features that mirror real-world e-commerce functionalities.

## Project Structure

The project adopts a modular structure with packages like `appinterface`, `authentification`, `orderprocessing`, `paymentprocessing`, `productmanagement`, `promotionservice`, `searching`, 'shopping cart' and `wishlist`. Each package encapsulates specific functionalities, adhering to OOP principles.

## Key Features

### 1. Authentication Service

The `AuthenticationService` manages user authentication in the e-commerce system. Users can sign up as admins or customers. The user hierarchy includes an abstract `User` class, ensuring a standardized structure for both admins and customers. User data, including usernames and passwords, is securely stored using HashMaps (adminUsers and customerUsers). All methods are static for the authentication manager so we don't have to instantiate it every time we use it. For the customer class, it has all necessary attributes for a customer having compositions and aggregations with other classes like shopping cart, order, order processor, and wishlist.

- **User Registration:** Users can sign up by providing a username and password.
- **User Login:** The service allows users to log in based on their role.
- **User Logout:** Users can log out, terminating their current session.
- **User Management:** Admins can be added or removed, and similar functionality exists for customers.

### 2. Product Management

- Hierarchical structure products stored in product categories stored in the list of products existing in the product manager.
- Products stored in hashmap, and it includes an id generator for each new order. All methods are static for the product manager so we don't have to instantiate it every time we use it.

#### Product Class:

- **Attributes:** Encapsulates product details. Composition with product feedback class.
- **Methods:** Manages product information, stock, and user feedback.

#### ProductCategory Class:

- **Attributes:** Represents product categories.
- **Methods:** Handles product categorization and search.

#### ProductManager Class:

- **Static Methods:** Manages global product aspects (CRUD operations for the user).
- **Inventory Management:** Tracks stock levels, handles increases/decreases in stock quantity, and displays out-of-stock products.

### 3. Shopping Cart

- Facilitates the management of products within a user's cart.
- Includes functions such as adding, updating, and removing items, and viewing the cart and it has a menu.
- Composition with the user class.

### 4. Order Processing

- Orders stored in hashmap and an it includes an id generator for each new order.
- Users can cancel, pass a new order, or pay it (linked with the payment service), and we can ship orders via the `OrderProcessor` (this feature is only for admins).
- Order history for each user.
- Different order status.
- Once the order is successfully paid the inventory immediately changes.

### 5. Dynamic Product Search and Filtering

- Implements dynamic search functionality for efficient product discovery.
- Provides filtering options to refine search results by category, price, rating, brand, and availability.
- A searching menu for each category itself.

### 6. Payment Processing

- A payment service class that has a static method `pay` and a `PaymentStrategy` interface, so the strategy pattern is used. We can easily add a new payment method (for now we have credit card, carte E_Dinar, and PayPal).
- The use of the strategy pattern enhances the Close to Modification, Open for Extension principle.

### Optional Features Implemented

#### A. User Reviews and Ratings

#### Feedback Management

1. **ProductFeedback Class:**
   - **Attributes:** Captures user feedback.(The feedback includes a review and a rating on 10)
   - **Methods:** Facilitates feedback input and retrieval.

2. **AuthenticationService Integration:**
   - **User Identification:** Associates feedback with the loggedUser details.(The user has the freedom of giving the feedback anonymously or with his name)

3. **Product Class Integration:**
   - Enables products to collect and display user feedback.
   - Users can add and view feedback on purchased products.
   - Displays an aggregate of a genral rating for each product.

OOP principles ensure modularity and flexibility for both product and feedback components, contributing to a cohesive and scalable e-commerce system.

#### B. Discounts and Promotions

#### PromotionManager

**Description:**

The `PromotionManager` class facilitates product promotions in an e-commerce system, embracing key Object-Oriented Programming (OOP) principles. There are two hashmaps one storing products in promotion and one storing discount codes.

**Emphasis on OOP:**

1. **Encapsulation:**
   - Secures data using private fields, encapsulating product IDs and corresponding promotion percentages and discount codes.

2. **Integration with Product and ProductManager:**
   - Collaborates with `ProductManager` to dynamically adjust product prices during promotions.
   
3. **Integration with Order processor:**
   - Before paying any order the user is asked about discount codes, and if he gives an existing discount code, the amount to pay changes.

4. **Dynamic Linking:**
   - Leverages the `Product` class for real-time price updates, establishing dynamic links.

**Usage:**

- **Adding/Removing Promotions:** Utilizes `addProduct` and `removeProduct` for managing product promotions.
- **Checking Promotion Status:** Employs `isInPromotion` and `getPromotion` to verify and retrieve promotion details.

This design ensures simplicity, modularity, and collaboration, aligning with OOP principles.

#### C. Wishlist

- Facilitates the management of products within a user wishes to buy.
- Includes functions such as adding, updating, and removing items, and viewing the wishlist and it has a menu.
- Composition with the user class.
- Users can convert products to the shopping cart.

## Application Interfaces

### 1. HomePage

- **Description:**
  - The `HomePage` class provides the main interface for users, allowing sign-up, login, search, and exit.
  - It integrates with `ProductManager` to display best sellers.
  - Utilizes the `AuthenticationService` for user authentication.
  - Then depending on the logged user it takes him to the appropriate interface.

### 2. CustomerInterface

- **Description:**
  - The `CustomerInterface` class represents the customer-specific interface after login.
  - Displays options for managing shopping cart, wish list, order history, and more.
  - Utilizes `OrderProcessor` for order-related functionalities.
  - After the logout that takes you back to the Home page or you can directly exit.

### 3. AdminInterface

- **Description:**
  - The `AdminInterface` class provides an interface tailored for administrators.
  - Supports various admin actions such as order shipping, user management, product management, and promotions.
  - Collaborates with `ProductManager` and `OrderProcessor` for inventory and order processing.
  - Utilizes `PromotionManager` for managing product promotions.
  - Relies on `AuthenticationService` for admin and user management.
  - After the logout that takes you back to the Home page or you can directly exit.

## Design Decisions

- Embraced a modular architecture to ensure each functionality is encapsulated within its designated class/package.
- Applied OOP principles, including encapsulation, inheritance, and polymorphism, to enhance code readability and maintainability.
- Prioritized user experience by providing intuitive interfaces and clear prompts.
- Used hashmaps in storing data in the project because it provides a fast and easy access(O(1)).
- The design follows the Open/Closed Principle by allowing the addition of new payment methods without modifying existing code, enhancing the application's flexibility and extensibility.
- For the Managers ProductManager, AuthenticationService, OrderProcessor, PromotionManager, PaymentService, SearchingManager, and the interfaces I used static methods because these classes are usually used so we don't have to instantiate them every time.


## Conclusion

In summary, this e-commerce console application effectively applies Object-Oriented Programming principles, delivering a modular and scalable system. With its various features, the application strikes a balance between simplicity and functionality. The design promotes code reusability, making it a solid foundation for future enhancements and exemplifying the practical application of OOP in real-world scenarios.
