import addon.*;
import discount.*;
import java.util.*;
import menu.*;
import menuitem.*;
import order.*;
import payment.*;

public class Main {
  private static Scanner scanner = new Scanner(System.in);
  private static List<Restaurant> restaurants = new ArrayList<>();
  private static Restaurant currentRestaurant = null;

  public static void main(String[] args) {
    System.out.println("\n" + "=".repeat(70));
    System.out.println("RESTAURANT ORDERING & BILLING SYSTEM");
    System.out.println("Welcome to Our Restaurant Management System");
    System.out.println("=".repeat(70));

    // Setup restaurants and menus
    setupRestaurants();

    // Main menu loop
    boolean running = true;
    while (running) {
      System.out.println("\n" + "=".repeat(70));
      System.out.println("MAIN MENU");
      System.out.println("=".repeat(70));
      System.out.println("1. View Available Restaurants");
      System.out.println("2. Select Restaurant");
      System.out.println("3. View Menus");
      System.out.println("4. View Specific Menu");
      System.out.println("5. Place Order");
      System.out.println("0. Exit");
      System.out.print("\nEnter your choice: ");

      int choice = getIntInput();

      switch (choice) {
        case 1:
          viewRestaurants();
          break;
        case 2:
          selectRestaurant();
          break;
        case 3:
          viewMenus();
          break;
        case 4:
          viewSpecificMenu();
          break;
        case 5:
          placeOrder();
          break;
        case 0:
          running = false;
          System.out.println("\nThank you for using our system. Goodbye!");
          break;
        default:
          System.out.println("\nInvalid choice. Please try again.");
      }
    }

    scanner.close();
  }

  private static void setupRestaurants() {
    // Create Restaurant 1: Delicious Bites
    Restaurant restaurant1 = new Restaurant("Delicious Bites Restaurant");

    // Create Vegetarian Menu using Builder
    MenuBuilder vegetarianBuilder = new MenuBuilder("Vegetarian Menu");
    vegetarianBuilder.addItem(MenuItemFactory.createItem("pizza", "vegetarian"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("burger", "veggie"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("pasta", "marinara"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("salad", "caesar"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("salad", "greek"));
    restaurant1.addMenu(vegetarianBuilder.buildMenu());

    // Create Non-Vegetarian Menu using Builder
    MenuBuilder nonVegBuilder = new MenuBuilder("Non-Vegetarian Menu");
    nonVegBuilder.addItem(MenuItemFactory.createItem("pizza", "pepperoni"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("pizza", "eastern"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("burger", "beef"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("pasta", "carbonara"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("pasta", "bolognese"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("salad", "chicken"));
    restaurant1.addMenu(nonVegBuilder.buildMenu());

    // Create Kids Menu using Builder
    MenuBuilder kidsBuilder = new MenuBuilder("Kids Menu");
    kidsBuilder.addItem(MenuItemFactory.createItem("pizza", "italian"));
    kidsBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    kidsBuilder.addItem(MenuItemFactory.createItem("pasta", "marinara"));
    kidsBuilder.addItem(MenuItemFactory.createItem("drink", "juice"));
    kidsBuilder.addItem(MenuItemFactory.createItem("drink", "water"));
    restaurant1.addMenu(kidsBuilder.buildMenu());

    // Create Beverages Menu using Builder
    MenuBuilder beveragesBuilder = new MenuBuilder("Beverages Menu");
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "coke"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "sprite"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "juice"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "coffee"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "water"));
    restaurant1.addMenu(beveragesBuilder.buildMenu());

    restaurants.add(restaurant1);

    // Create Restaurant 2: Quick Eats
    Restaurant restaurant2 = new Restaurant("Quick Eats Express");

    // Create Non-Vegetarian Menu for Restaurant 2
    MenuBuilder quickNonVegBuilder = new MenuBuilder("Non-Vegetarian Menu");
    quickNonVegBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    quickNonVegBuilder.addItem(MenuItemFactory.createItem("burger", "beef"));
    quickNonVegBuilder.addItem(MenuItemFactory.createItem("pizza", "pepperoni"));
    restaurant2.addMenu(quickNonVegBuilder.buildMenu());

    // Create Kids Menu for Restaurant 2
    MenuBuilder quickKidsBuilder = new MenuBuilder("Kids Menu");
    quickKidsBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    quickKidsBuilder.addItem(MenuItemFactory.createItem("drink", "juice"));
    restaurant2.addMenu(quickKidsBuilder.buildMenu());

    restaurants.add(restaurant2);

    System.out.println("\n✓ Restaurants and menus have been set up successfully!");
  }

  private static void viewRestaurants() {
    System.out.println("\n" + "=".repeat(70));
    System.out.println("AVAILABLE RESTAURANTS");
    System.out.println("=".repeat(70));

    for (int i = 0; i < restaurants.size(); i++) {
      Restaurant r = restaurants.get(i);
      String selected = (r == currentRestaurant) ? " [SELECTED]" : "";
      System.out.println((i + 1) + ". " + r.getName() + selected);
    }
  }

  private static void selectRestaurant() {
    if (restaurants.isEmpty()) {
      System.out.println("\nNo restaurants available.");
      return;
    }

    viewRestaurants();
    System.out.print("\nEnter restaurant number to select: ");
    int choice = getIntInput();

    if (choice > 0 && choice <= restaurants.size()) {
      currentRestaurant = restaurants.get(choice - 1);
      System.out.println("\n✓ Selected: " + currentRestaurant.getName());
    } else {
      System.out.println("\nInvalid restaurant number.");
    }
  }

  private static void viewMenus() {
    if (currentRestaurant == null) {
      System.out.println("\nPlease select a restaurant first.");
      return;
    }

    System.out.println("\n" + "=".repeat(70));
    System.out.println("AVAILABLE MENUS AT " + currentRestaurant.getName().toUpperCase());
    System.out.println("=".repeat(70));
    currentRestaurant.listMenus();
  }

  private static void viewSpecificMenu() {
    if (currentRestaurant == null) {
      System.out.println("\nPlease select a restaurant first.");
      return;
    }

    List<Menu> menus = currentRestaurant.getMenus();
    if (menus.isEmpty()) {
      System.out.println("\nNo menus available.");
      return;
    }

    System.out.println("\n" + "=".repeat(70));
    System.out.println("SELECT A MENU TO VIEW");
    System.out.println("=".repeat(70));
    for (int i = 0; i < menus.size(); i++) {
      System.out.println((i + 1) + ". " + menus.get(i).getName());
    }

    System.out.print("\nEnter menu number: ");
    int choice = getIntInput();

    if (choice > 0 && choice <= menus.size()) {
      Menu selectedMenu = menus.get(choice - 1);
      System.out.println("\n" + "=".repeat(70));
      selectedMenu.printMenu();
      System.out.println("=".repeat(70));
      System.out.println("\nADD-ONS AVAILABLE:");
      System.out.println("  - Extra Cheese (+$1.50)");
      System.out.println("  - Sauce (+$0.75)");
      System.out.println("  - Toppings (+$1.00)");
      System.out.println("  - Extra Meat (+$2.50)");
      System.out.println("  - Bacon (+$1.75)");
    } else {
      System.out.println("\nInvalid menu number.");
    }
  }

  private static void placeOrder() {
    if (currentRestaurant == null) {
      System.out.println("\nPlease select a restaurant first.");
      return;
    }

    System.out.println("\n" + "=".repeat(70));
    System.out.println("PLACE NEW ORDER");
    System.out.println("=".repeat(70));

    // Select order type
    System.out.println("\nOrder Type:");
    System.out.println("1. Dine-in");
    System.out.println("2. Takeaway");
    System.out.println("3. Delivery");
    System.out.print("\nSelect order type: ");
    int typeChoice = getIntInput();

    String orderType = "Dine-in";
    switch (typeChoice) {
      case 1:
        orderType = "Dine-in";
        break;
      case 2:
        orderType = "Takeaway";
        break;
      case 3:
        orderType = "Delivery";
        break;
    }

    Order order = currentRestaurant.createOrder(orderType);
    System.out.println("\n✓ Order #" + order.getOrderId() + " created (" + orderType + ")");

    // Add items to order
    boolean addingItems = true;
    while (addingItems) {
      System.out.println("\n" + "-".repeat(70));
      System.out.println("ADD ITEMS TO ORDER");
      System.out.println("-".repeat(70));
      System.out.println("Item Categories:");
      System.out.println("1. Pizza");
      System.out.println("2. Burger");
      System.out.println("3. Pasta");
      System.out.println("4. Salad");
      System.out.println("5. Drink");
      System.out.println("0. Finish adding items");
      System.out.print("\nSelect category: ");

      int category = getIntInput();
      if (category == 0) {
        addingItems = false;
        continue;
      }

      MenuItem item = selectMenuItem(category);
      if (item != null) {
        // Ask for add-ons
        item = addDecorators(item);
        order.addItem(item);
        System.out.println("\n✓ Added: " + item.getDescription());
      }
    }

    if (order.getItems().isEmpty()) {
      System.out.println("\nNo items added. Order cancelled.");
      return;
    }

    // Apply discount
    System.out.println("\n" + "-".repeat(70));
    System.out.println("APPLY DISCOUNT");
    System.out.println("-".repeat(70));
    System.out.println("1. Monday Special ($5 off)");
    System.out.println("2. Friday Special ($10 off)");
    System.out.println("3. Custom Discount");
    System.out.println("4. No Discount");
    System.out.print("\nSelect discount: ");

    int discountChoice = getIntInput();
    switch (discountChoice) {
      case 1:
        order.setDiscountStrategy(new MondayDiscount());
        break;
      case 2:
        order.setDiscountStrategy(new FridayDiscount());
        break;
      case 3:
        System.out.print("Enter discount percentage: ");
        int percentage = getIntInput();
        System.out.print("Enter discount name: ");
        scanner.nextLine(); // consume newline
        String name = scanner.nextLine();
        order.setDiscountStrategy(new CustomDiscount(percentage, name));
        break;
      case 4:
        order.setDiscountStrategy(null);
        break;
    }

    // Select payment method
    System.out.println("\n" + "-".repeat(70));
    System.out.println("PAYMENT METHOD");
    System.out.println("-".repeat(70));
    System.out.println("1. Cash");
    System.out.println("2. Credit Card");
    System.out.println("3. Mobile Wallet");
    System.out.print("\nSelect payment method: ");

    int paymentChoice = getIntInput();
    switch (paymentChoice) {
      case 1:
        order.setPaymentStrategy(new CashPayment());
        break;
      case 2:
        System.out.print("Enter card number: ");
        scanner.nextLine(); // consume newline
        String cardNumber = scanner.nextLine();
        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();
        order.setPaymentStrategy(new CreditCardPayment(cardNumber, cvv));
        break;
      case 3:
        System.out.print("Enter wallet ID: ");
        scanner.nextLine(); // consume newline
        String walletId = scanner.nextLine();
        order.setPaymentStrategy(new MobileWalletPayment(walletId));
        break;
      default:
        order.setPaymentStrategy(new CashPayment());
    }

    // Display and place order
    System.out.println("\n" + "=".repeat(70));
    order.displayOrder();
    System.out.println("=".repeat(70));

    System.out.print("\nConfirm order? (y/n): ");
    scanner.nextLine(); // consume newline
    String confirm = scanner.nextLine();

    if (confirm.equalsIgnoreCase("y")) {
      order.placeOrder();
      System.out.println("\n✓ Order placed successfully!");
    } else {
      System.out.println("\n✗ Order cancelled.");
    }
  }

  private static MenuItem selectMenuItem(int category) {
    String itemType = "";
    String[] variants = {};

    switch (category) {
      case 1: // Pizza
        itemType = "pizza";
        variants = new String[] { "italian", "pepperoni", "eastern", "vegetarian" };
        System.out.println("\n1. Italian Margherita");
        System.out.println("2. Pepperoni");
        System.out.println("3. Eastern Shawarma");
        System.out.println("4. Vegetarian");
        break;
      case 2: // Burger
        itemType = "burger";
        variants = new String[] { "chicken", "beef", "veggie" };
        System.out.println("\n1. Chicken Burger");
        System.out.println("2. Beef Burger");
        System.out.println("3. Veggie Burger");
        break;
      case 3: // Pasta
        itemType = "pasta";
        variants = new String[] { "carbonara", "bolognese", "marinara" };
        System.out.println("\n1. Carbonara");
        System.out.println("2. Bolognese");
        System.out.println("3. Marinara");
        break;
      case 4: // Salad
        itemType = "salad";
        variants = new String[] { "caesar", "greek", "chicken" };
        System.out.println("\n1. Caesar Salad");
        System.out.println("2. Greek Salad");
        System.out.println("3. Chicken Salad");
        break;
      case 5: // Drink
        itemType = "drink";
        variants = new String[] { "coke", "sprite", "juice", "coffee", "water" };
        System.out.println("\n1. Coca Cola");
        System.out.println("2. Sprite");
        System.out.println("3. Orange Juice");
        System.out.println("4. Coffee");
        System.out.println("5. Water");
        break;
      default:
        System.out.println("\nInvalid category.");
        return null;
    }

    System.out.print("\nSelect item: ");
    int choice = getIntInput();

    if (choice > 0 && choice <= variants.length) {
      return MenuItemFactory.createItem(itemType, variants[choice - 1]);
    } else {
      System.out.println("\nInvalid item number.");
      return null;
    }
  }

  private static MenuItem addDecorators(MenuItem item) {
    boolean addingDecorators = true;

    while (addingDecorators) {
      System.out.println("\nAdd-ons (enter 0 to finish):");
      System.out.println("1. Extra Cheese (+$1.50)");
      System.out.println("2. Sauce (+$0.75)");
      System.out.println("3. Topping (+$1.00)");
      System.out.println("4. Extra Meat (+$2.50)");
      System.out.println("5. Bacon (+$1.75)");
      System.out.println("0. No more add-ons");
      System.out.print("\nSelect add-on: ");

      int choice = getIntInput();

      switch (choice) {
        case 1:
          item = new ExtraCheeseDecorator(item);
          System.out.println("✓ Added Extra Cheese");
          break;
        case 2:
          System.out.print("Enter sauce type: ");
          scanner.nextLine(); // consume newline
          String sauce = scanner.nextLine();
          item = new SauceDecorator(item, sauce);
          System.out.println("✓ Added " + sauce + " Sauce");
          break;
        case 3:
          System.out.print("Enter topping: ");
          scanner.nextLine(); // consume newline
          String topping = scanner.nextLine();
          item = new ToppingDecorator(item, topping);
          System.out.println("✓ Added " + topping);
          break;
        case 4:
          item = new ExtraMeatDecorator(item);
          System.out.println("✓ Added Extra Meat");
          break;
        case 5:
          item = new BaconDecorator(item);
          System.out.println("✓ Added Bacon");
          break;
        case 0:
          addingDecorators = false;
          break;
        default:
          System.out.println("Invalid choice.");
      }
    }

    return item;
  }

  private static int getIntInput() {
    while (!scanner.hasNextInt()) {
      System.out.print("Please enter a valid number: ");
      scanner.next();
    }
    int input = scanner.nextInt();
    return input;
  }
}
