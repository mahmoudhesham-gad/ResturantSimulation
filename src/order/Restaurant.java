package order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import notification.Kitchen;
import notification.Waiter;

/**
 * Main restaurant system coordinator
 */
public class Restaurant {
  private String name;
  private Kitchen kitchen;
  private List<Waiter> waiters;
  private Map<Integer, Order> currentOrders;

  public Restaurant(String name) {
    this.name = name;
    this.kitchen = new Kitchen();
    this.waiters = new ArrayList<>();
    this.currentOrders = new HashMap<>();
    setupStaff();
  }

  private void setupStaff() {
    waiters.add(new Waiter("John"));
    waiters.add(new Waiter("Sarah"));
  }

  public Order createOrder(String orderType) {
    Order order = new Order(orderType);

    // Attach kitchen and waiter as observers
    order.attach(kitchen);
    if (!waiters.isEmpty()) {
      order.attach(waiters.get(0)); // Assign first available waiter
    }

    currentOrders.put(order.getOrderId(), order);
    return order;
  }

  public void displayMenu() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("Welcome to " + name + "!");
    System.out.println("=".repeat(60));
    System.out.println("\n--- MENU ---\n");

    System.out.println("PIZZAS:");
    System.out.println("  1. Italian Margherita - $12.99");
    System.out.println("  2. Eastern Shawarma Pizza - $14.99");
    System.out.println("  3. Classic Pepperoni - $13.99");
    System.out.println("  4. Vegetarian Pizza - $11.99");

    System.out.println("\nBURGERS:");
    System.out.println("  5. Chicken Burger - $8.99");
    System.out.println("  6. Beef Burger - $10.99");
    System.out.println("  7. Veggie Burger - $7.99");

    System.out.println("\nPASTA:");
    System.out.println("  8. Carbonara - $11.99");
    System.out.println("  9. Bolognese - $12.99");
    System.out.println("  10. Marinara - $10.99");

    System.out.println("\nSALADS:");
    System.out.println("  11. Caesar Salad - $6.99");
    System.out.println("  12. Greek Salad - $7.99");
    System.out.println("  13. Chicken Salad - $8.99");

    System.out.println("\nDRINKS:");
    System.out.println("  14. Coca Cola - $2.99");
    System.out.println("  15. Orange Juice - $3.99");
    System.out.println("  16. Coffee - $2.49");

    System.out.println("\nADD-ONS:");
    System.out.println("  - Extra Cheese (+$1.50)");
    System.out.println("  - Sauce (+$0.75)");
    System.out.println("  - Toppings (+$1.00)");
    System.out.println("  - Extra Meat (+$2.50)");
    System.out.println("  - Bacon (+$1.75)");

    System.out.println("\n" + "=".repeat(60));
  }

  public Order getOrder(int orderId) {
    return currentOrders.get(orderId);
  }

  public String getName() {
    return name;
  }
}
