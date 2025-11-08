import menuitem.*;
import addon.*;
import order.*;
import payment.*;
import discount.*;

public class Main {

  public static void main(String[] args) {
    System.out.println("\n" + "=".repeat(70));
    System.out.println("RESTAURANT ORDERING & BILLING SYSTEM");
    System.out.println("Demonstrating Design Patterns Implementation");
    System.out.println("=".repeat(70));

    // Run all scenarios
    demoScenario1(); // Monday discount
    demoScenario2(); // Friday discount
    demoScenario3(); // Custom discount
    demoScenario4(); // No discount

    System.out.println("\n\n" + "=".repeat(70));
    System.out.println("ALL SCENARIOS COMPLETED SUCCESSFULLY!");
    System.out.println("=".repeat(70));
  }

  /**
   * Scenario 1: Monday Special Discount
   */
  private static void demoScenario1() {
    System.out.println("\n" + "=".repeat(70));
    System.out.println("SCENARIO 1: Monday Special ($5 off)");
    System.out.println("=".repeat(70));

    Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");
    restaurant.displayMenu();

    // Create order
    Order order = restaurant.createOrder("Dine-in");

    // Add various items
    MenuItem pizza = MenuItemFactory.createItem("pizza", "italian");
    pizza = new ExtraCheeseDecorator(pizza);
    pizza = new ToppingDecorator(pizza, "Olives");

    MenuItem burger = MenuItemFactory.createItem("burger", "chicken");
    burger = new BaconDecorator(burger);

    MenuItem drink = MenuItemFactory.createItem("drink", "coke");

    order.addItem(pizza);
    order.addItem(burger);
    order.addItem(drink);

    // Apply Monday discount
    order.setDiscountStrategy(new MondayDiscount());

    // Set payment method
    order.setPaymentStrategy(new CreditCardPayment("1234567890123456", "123"));

    // Display and place order
    order.displayOrder();
    order.placeOrder();
  }

  /**
   * Scenario 2: Friday Special Discount
   */
  private static void demoScenario2() {
    System.out.println("\n\n" + "=".repeat(70));
    System.out.println("SCENARIO 2: Friday Special ($10 off)");
    System.out.println("=".repeat(70));

    Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");

    Order order = restaurant.createOrder("Takeaway");

    // Add items for a larger order
    MenuItem pizza1 = MenuItemFactory.createItem("pizza", "pepperoni");
    pizza1 = new ExtraMeatDecorator(pizza1);

    MenuItem burger = MenuItemFactory.createItem("burger", "beef");
    burger = new ExtraCheeseDecorator(burger);
    burger = new SauceDecorator(burger, "Honey Mustard");

    MenuItem pasta = MenuItemFactory.createItem("pasta", "bolognese");

    MenuItem salad = MenuItemFactory.createItem("salad", "caesar");

    MenuItem drink = MenuItemFactory.createItem("drink", "juice");

    order.addItem(pizza1);
    order.addItem(burger);
    order.addItem(pasta);
    order.addItem(salad);
    order.addItem(drink);

    // Apply Friday discount
    order.setDiscountStrategy(new FridayDiscount());

    // Set payment method
    order.setPaymentStrategy(new MobileWalletPayment("user@wallet.com"));

    order.displayOrder();
    order.placeOrder();
  }

  /**
   * Scenario 3: Custom Discount (Manager's Special)
   */
  private static void demoScenario3() {
    System.out.println("\n\n" + "=".repeat(70));
    System.out.println("SCENARIO 3: Custom Discount - Manager's Special");
    System.out.println("=".repeat(70));

    Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");

    Order order = restaurant.createOrder("Delivery");

    // Add various items
    MenuItem burger = MenuItemFactory.createItem("burger", "beef");
    burger = new ExtraCheeseDecorator(burger);
    burger = new ExtraMeatDecorator(burger);

    MenuItem pasta = MenuItemFactory.createItem("pasta", "carbonara");
    pasta = new ToppingDecorator(pasta, "Parmesan Cheese");

    MenuItem pizza = MenuItemFactory.createItem("pizza", "vegetarian");
    pizza = new ExtraCheeseDecorator(pizza);

    MenuItem drink = MenuItemFactory.createItem("drink", "coffee");

    order.addItem(burger);
    order.addItem(pasta);
    order.addItem(pizza);
    order.addItem(drink);

    // Apply custom 20% discount
    order.setDiscountStrategy(new CustomDiscount(20, "Manager's Special"));

    // Set payment method
    order.setPaymentStrategy(new CashPayment());

    order.displayOrder();
    order.placeOrder();
  }

  /**
   * Scenario 4: Highly Customized Order (No Discount)
   */
  private static void demoScenario4() {
    System.out.println("\n\n" + "=".repeat(70));
    System.out.println("SCENARIO 4: Highly Customized Order (No Discount)");
    System.out.println("=".repeat(70));

    Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");

    Order order = restaurant.createOrder("Dine-in");

    // Create heavily customized burger
    MenuItem customBurger = MenuItemFactory.createItem("burger", "beef");
    customBurger = new ExtraCheeseDecorator(customBurger);
    customBurger = new BaconDecorator(customBurger);
    customBurger = new SauceDecorator(customBurger, "Spicy Mayo");
    customBurger = new ToppingDecorator(customBurger, "Grilled Onions");

    // Customized pizza
    MenuItem customPizza = MenuItemFactory.createItem("pizza", "eastern");
    customPizza = new ExtraCheeseDecorator(customPizza);
    customPizza = new ToppingDecorator(customPizza, "Mushrooms");
    customPizza = new ToppingDecorator(customPizza, "Bell Peppers");

    MenuItem drink = MenuItemFactory.createItem("drink", "sprite");

    order.addItem(customBurger);
    order.addItem(customPizza);
    order.addItem(drink);

    // No discount
    order.setDiscountStrategy(null);

    // Set payment method
    order.setPaymentStrategy(new MobileWalletPayment("customer123@pay.com"));

    order.displayOrder();
    order.placeOrder();
  }
}
