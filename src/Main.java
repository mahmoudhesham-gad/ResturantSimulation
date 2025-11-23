import addon.*;
import discount.*;
import java.util.*;
import menu.*;
import menuitem.*;
import order.*;
import payment.*;

public class Main {
  private static Scanner scanner;
  private static List<Restaurant> restaurants; 

  private static void setupRestaurants() {
    Restaurant restaurant1 = new Restaurant("Delicious Bites Restaurant");

    MenuBuilder vegetarianBuilder = new MenuBuilder("Vegetarian Menu");
    vegetarianBuilder.addItem(MenuItemFactory.createItem("pizza", "vegetarian"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("burger", "veggie"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("pasta", "marinara"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("salad", "caesar"));
    vegetarianBuilder.addItem(MenuItemFactory.createItem("salad", "greek"));
    restaurant1.addMenu(vegetarianBuilder.buildMenu());

    MenuBuilder nonVegBuilder = new MenuBuilder("Non-Vegetarian Menu");
    nonVegBuilder.addItem(MenuItemFactory.createItem("pizza", "pepperoni"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("pizza", "eastern"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("burger", "beef"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("pasta", "carbonara"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("pasta", "bolognese"));
    nonVegBuilder.addItem(MenuItemFactory.createItem("salad", "chicken"));
    restaurant1.addMenu(nonVegBuilder.buildMenu());

    MenuBuilder kidsBuilder = new MenuBuilder("Kids Menu");
    kidsBuilder.addItem(MenuItemFactory.createItem("pizza", "italian"));
    kidsBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    kidsBuilder.addItem(MenuItemFactory.createItem("pasta", "marinara"));
    kidsBuilder.addItem(MenuItemFactory.createItem("drink", "juice"));
    kidsBuilder.addItem(MenuItemFactory.createItem("drink", "water"));
    restaurant1.addMenu(kidsBuilder.buildMenu());

    MenuBuilder beveragesBuilder = new MenuBuilder("Beverages Menu");
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "coke"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "sprite"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "juice"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "coffee"));
    beveragesBuilder.addItem(MenuItemFactory.createItem("drink", "water"));
    restaurant1.addMenu(beveragesBuilder.buildMenu());

    restaurants.add(restaurant1);

    Restaurant restaurant2 = new Restaurant("Quick Eats Express");

    MenuBuilder quickNonVegBuilder = new MenuBuilder("Non-Vegetarian Menu");
    quickNonVegBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    quickNonVegBuilder.addItem(MenuItemFactory.createItem("burger", "beef"));
    quickNonVegBuilder.addItem(MenuItemFactory.createItem("pizza", "pepperoni"));
    restaurant2.addMenu(quickNonVegBuilder.buildMenu());

    MenuBuilder quickKidsBuilder = new MenuBuilder("Kids Menu");
    quickKidsBuilder.addItem(MenuItemFactory.createItem("burger", "chicken"));
    quickKidsBuilder.addItem(MenuItemFactory.createItem("drink", "juice"));
    restaurant2.addMenu(quickKidsBuilder.buildMenu());

    restaurants.add(restaurant2);

    System.out.println("\n✓ Restaurants and menus have been set up successfully!");
  }

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    restaurants = new ArrayList<>();
    setupRestaurants();
    Restaurant selectedRestaurant = selectRestaurant();
    Menu selectedMenu = selectMenu(selectedRestaurant);
     while (true) {
      boolean continueOrdering = orderFromMenu(selectedRestaurant, selectedMenu);
      if (!continueOrdering) break;
    }
    runMainMenu();
    scanner.close();
  }
  private static void runMainMenu() {
    while (true) {
      System.out.println("\n" + "=".repeat(70));
      System.out.println("MAIN MENU");
      System.out.println("=".repeat(70));
      System.out.println("1. Start New Order");
      System.out.println("0. Exit");
      System.out.print("\nEnter your choice: ");

      int choice = getIntInput();

      switch (choice) {
        case 1 -> startOrderFlow();
        case 0 -> {
          System.out.println("\nThank you for using our system. Goodbye!");
          return;
        }
        default -> System.out.println("\nInvalid choice. Please try again.");
      }
    }
  }

  private static Restaurant selectRestaurant() {
    while (true) {
      System.out.println("\n" + "=".repeat(70));
      System.out.println("LAYER 1: SELECT RESTAURANT");
      System.out.println("=".repeat(70));

      for (int i = 0; i < restaurants.size(); i++) {
        System.out.println((i + 1) + ". " + restaurants.get(i).getName());
      }
      System.out.println("0. Back to Main Menu");
      System.out.print("\nEnter your choice: ");

      int choice = getIntInput();

      if (choice == 0) {
        return null;
      } else if (choice > 0 && choice <= restaurants.size()) {
        Restaurant selected = restaurants.get(choice - 1);
        System.out.println("\n✓ Selected: " + selected.getName());
        return selected;
      } else {
        System.out.println("\nInvalid choice. Please try again.");
      }
    }
  }


  private static Menu selectMenu(Restaurant restaurant) {
    List<Menu> menus = restaurant.getMenus();
    
    while (true) {
      System.out.println("\n" + "=".repeat(70));
      System.out.println("LAYER 2: SELECT MENU");
      System.out.println("Restaurant: " + restaurant.getName());
      System.out.println("=".repeat(70));

      for (int i = 0; i < menus.size(); i++) {
        System.out.println((i + 1) + ". " + menus.get(i).getName());
      }
      System.out.println("0. Back to Restaurant Selection");
      System.out.print("\nEnter your choice: ");

      int choice = getIntInput();

      if (choice == 0) {
        return null;
      } else if (choice > 0 && choice <= menus.size()) {
        Menu selected = menus.get(choice - 1);
        System.out.println("\n" + "=".repeat(70));
        selected.printMenu();
        System.out.println("=".repeat(70));
        return selected;
      } else {
        System.out.println("\nInvalid choice. Please try again.");
      }
    }
  }

  private static boolean orderFromMenu(Restaurant restaurant, Menu menu) {
    String orderType = selectOrderType();
    if (orderType == null) return true;

    Order order = restaurant.createOrder(orderType);
    System.out.println("\n✓ Order #" + order.getOrderId() + " created (" + orderType + ")");

    boolean addingItems = addItemsToOrder(order, menu);
    if (!addingItems || order.getItems().isEmpty()) {
      System.out.println("\nOrder cancelled. Returning to menu selection...");
      return true;
    }

    applyDiscount(order);
    selectPaymentMethod(order);

    boolean confirmed = confirmOrder(order);
    
    if (confirmed) {
      System.out.println("\n✓ Order completed successfully!");
      System.out.print("\nWould you like to place another order? (y/n): ");
      scanner.nextLine();
      String another = scanner.nextLine();
      return another.equalsIgnoreCase("y");
    }
    return true;
  }

  private static String selectOrderType() {
    while (true) {
      System.out.println("\n" + "-".repeat(70));
      System.out.println("SELECT ORDER TYPE");
      System.out.println("-".repeat(70));
      System.out.println("1. Dine-in");
      System.out.println("2. Takeaway");
      System.out.println("3. Delivery");
      System.out.println("0. Back to Menu Selection");
      System.out.print("\nSelect order type: ");
      
      int typeChoice = getIntInput();

      switch (typeChoice) {
        case 1 -> { return "Dine-in"; }
        case 2 -> { return "Takeaway"; }
        case 3 -> { return "Delivery"; }
        case 0 -> { return null; }
        default -> System.out.println("\nInvalid choice. Please try again.");
      }
    }
  }

  private static boolean addItemsToOrder(Order order, Menu menu) {
    while (true) {
      System.out.println("\n" + "-".repeat(70));
      System.out.println("ADD ITEMS TO ORDER");
      System.out.println("Menu: " + menu.getName());
      System.out.println("-".repeat(70));
      System.out.println("1. Add item from this menu");
      System.out.println("2. Finish adding items");
      System.out.println("0. Cancel order and go back");
      System.out.print("\nEnter your choice: ");

      int choice = getIntInput();

      switch (choice) {
        case 1 -> {
          MenuItem item = selectItemFromMenu(menu);
          if (item != null) {
            item = addDecorators(item);
            order.addItem(item);
            System.out.println("\n✓ Added: " + item.getDescription());
            System.out.println("Current total: $" + String.format("%.2f", order.getTotal()));
          }
        }
        case 2 -> {
          if (order.getItems().isEmpty()) {
            System.out.println("\nNo items added yet. Please add at least one item.");
          } else {
            return true;
          }
        }
        case 0 -> { return false; }
        default -> System.out.println("\nInvalid choice. Please try again.");
      }
    }
  }

  private static MenuItem selectItemFromMenu(Menu menu) {
    Map<String, List<MenuItem>> itemsByCategory = menu.getItemsByCategory();
    List<MenuItem> allItems = new ArrayList<>();
    
    for (List<MenuItem> items : itemsByCategory.values()) {
      allItems.addAll(items);
    }

    if (allItems.isEmpty()) {
      System.out.println("\nNo items available in this menu.");
      return null;
    }

    System.out.println("\n" + "-".repeat(70));
    System.out.println("SELECT ITEM");
    System.out.println("-".repeat(70));
    
    for (int i = 0; i < allItems.size(); i++) {
      MenuItem item = allItems.get(i);
      System.out.println((i + 1) + ". " + item.getName() + " - $" + 
                         String.format("%.2f", item.getPrice()) + 
                         " (" + item.getCategory() + ")");
    }
    System.out.println("0. Back");
    System.out.print("\nSelect item: ");

    int choice = getIntInput();

    if (choice == 0) {
      return null;
    } else if (choice > 0 && choice <= allItems.size()) {
      return allItems.get(choice - 1);
    } else {
      System.out.println("\nInvalid choice.");
      return null;
    }
  }

  private static MenuItem addDecorators(MenuItem item) {
    while (true) {
      System.out.println("\n" + "-".repeat(70));
      System.out.println("ADD-ONS FOR: " + item.getDescription());
      System.out.println("Current price: $" + String.format("%.2f", item.getPrice()));
      System.out.println("-".repeat(70));
      System.out.println("1. Extra Cheese (+$1.50)");
      System.out.println("2. Sauce (+$0.75)");
      System.out.println("3. Topping (+$1.00)");
      System.out.println("4. Extra Meat (+$2.50)");
      System.out.println("5. Bacon (+$1.75)");
      System.out.println("0. Done with add-ons");
      System.out.print("\nSelect add-on: ");

      int choice = getIntInput();

      switch (choice) {
        case 1 -> {
          item = new ExtraCheeseDecorator(item);
          System.out.println("✓ Added Extra Cheese");
        }
        case 2 -> {
          System.out.print("Enter sauce type: ");
          scanner.nextLine();
          String sauce = scanner.nextLine();
          item = new SauceDecorator(item, sauce);
          System.out.println("✓ Added " + sauce + " Sauce");
        }
        case 3 -> {
          System.out.print("Enter topping: ");
          scanner.nextLine();
          String topping = scanner.nextLine();
          item = new ToppingDecorator(item, topping);
          System.out.println("✓ Added " + topping);
        }
        case 4 -> {
          item = new ExtraMeatDecorator(item);
          System.out.println("✓ Added Extra Meat");
        }
        case 5 -> {
          item = new BaconDecorator(item);
          System.out.println("✓ Added Bacon");
        }
        case 0 -> { return item; }
        default -> System.out.println("Invalid choice.");
      }
    }
  }

  private static void applyDiscount(Order order) {
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
      case 1 -> order.setDiscountStrategy(new MondayDiscount());
      case 2 -> order.setDiscountStrategy(new FridayDiscount());
      case 3 -> {
        System.out.print("Enter discount percentage: ");
        int percentage = getIntInput();
        System.out.print("Enter discount name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        order.setDiscountStrategy(new CustomDiscount(percentage, name));
      }
      case 4 -> order.setDiscountStrategy(null);
      default -> {
        System.out.println("Invalid choice. No discount applied.");
        order.setDiscountStrategy(null);
      }
    }
  }

  private static void selectPaymentMethod(Order order) {
    System.out.println("\n" + "-".repeat(70));
    System.out.println("PAYMENT METHOD");
    System.out.println("-".repeat(70));
    System.out.println("1. Cash");
    System.out.println("2. Credit Card");
    System.out.println("3. Mobile Wallet");
    System.out.print("\nSelect payment method: ");

    int paymentChoice = getIntInput();
    switch (paymentChoice) {
      case 1 -> order.setPaymentStrategy(new CashPayment());
      case 2 -> {
        System.out.print("Enter card number: ");
        scanner.nextLine();
        String cardNumber = scanner.nextLine();
        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();
        order.setPaymentStrategy(new CreditCardPayment(cardNumber, cvv));
      }
      case 3 -> {
        System.out.print("Enter wallet ID: ");
        scanner.nextLine();
        String walletId = scanner.nextLine();
        order.setPaymentStrategy(new MobileWalletPayment(walletId));
      }
      default -> {
        System.out.println("Invalid choice. Defaulting to Cash payment.");
        order.setPaymentStrategy(new CashPayment());
      }
    }
  }

  private static boolean confirmOrder(Order order) {
    System.out.println("\n" + "=".repeat(70));
    System.out.println("ORDER SUMMARY");
    System.out.println("=".repeat(70));
    order.displayOrder();
    System.out.println("=".repeat(70));

    System.out.print("\nConfirm and place order? (y/n): ");
    scanner.nextLine();
    String confirm = scanner.nextLine();

    if (confirm.equalsIgnoreCase("y")) {
      order.placeOrder();
      return true;
    } else {
      System.out.println("\n✗ Order cancelled.");
      return false;
    }
  }

  private static int getIntInput() {
    while (!scanner.hasNextInt()) {
      System.out.print("Please enter a valid number: ");
      scanner.next();
    }
    return scanner.nextInt();
  }
}
