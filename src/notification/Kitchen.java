package notification;

import java.util.List;

public class Kitchen implements Observer {
  private String name = "Kitchen";

  @Override
  public void update(int orderId, List<String> items, double total) {
    System.out.println("\n[KITCHEN NOTIFICATION]");
    System.out.println("New Order #" + orderId + " received!");
    System.out.println("Items to prepare:");
    for (String item : items) {
      System.out.println("  - " + item);
    }
    System.out.printf("Order Total: $%.2f\n", total);
  }

  public String getName() {
    return name;
  }
}
