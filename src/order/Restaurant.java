package order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import notification.Kitchen;
import notification.Waiter;
import menu.Menu;

/**
 * Main restaurant system coordinator
 */
public class Restaurant {
  private String name;
  private Kitchen kitchen;
  private List<Waiter> waiters;
  private Map<Integer, Order> currentOrders;
  private List<Menu> menus;

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

  public void addMenu(Menu menu) {
    this.menus.add(menu);
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

  public void listMenus() {
    for (Menu menu : this.menus) {
      System.out.println(menu.getName());
    }
  }

  public void displayMenu(int menuId) {
    this.menus.get(menuId).printMenu();
  }

  public Order getOrder(int orderId) {
    return currentOrders.get(orderId);
  }

  public String getName() {
    return name;
  }
}
