package order;

import java.util.ArrayList;
import java.util.List;
import menuitem.MenuItem;
import payment.PaymentStrategy;
import discount.DiscountStrategy;
import notification.Subject;

/**
 * Order class manages order items and handles payment/discounts
 * Extends Subject to support Observer pattern
 */
public class Order extends Subject {
  private static int orderCounter = 1000;

  private int orderId;
  private String orderType;
  private List<MenuItem> items;
  private double taxRate = 0.10; // 10% tax
  private PaymentStrategy paymentStrategy;
  private DiscountStrategy discountStrategy;

  public Order(String orderType) {
    this.orderId = ++orderCounter;
    this.orderType = orderType;
    this.items = new ArrayList<>();
  }

  public void addItem(MenuItem item) {
    items.add(item);
  }

  public void removeItem(int index) {
    if (index >= 0 && index < items.size()) {
      items.remove(index);
    }
  }

  public double getSubtotal() {
    double subtotal = 0.0;
    for (MenuItem item : items) {
      subtotal += item.getPrice();
    }
    return subtotal;
  }

  public double getTax() {
    return getSubtotal() * taxRate;
  }

  public double getDiscount() {
    if (this.discountStrategy == null) {
      return 0.0;
    }
    return discountStrategy.calculateDiscount(getSubtotal());
  }

  public double getTotal() {
    return getSubtotal() + getTax() - getDiscount();
  }

  public void setPaymentStrategy(PaymentStrategy strategy) {
    this.paymentStrategy = strategy;
  }

  public void setDiscountStrategy(DiscountStrategy strategy) {
    this.discountStrategy = strategy;
  }

  public boolean placeOrder() {
    if (items.isEmpty()) {
      System.out.println("Cannot place empty order!");
      return false;
    }

    if (paymentStrategy == null) {
      System.out.println("Please select a payment method!");
      return false;
    }

    // Process payment
    double total = getTotal();
    boolean paymentSuccess = paymentStrategy.pay(total);

    if (paymentSuccess) {
      // Notify kitchen and waiters
      List<String> itemDescriptions = new ArrayList<>();
      for (MenuItem item : items) {
        itemDescriptions.add(item.getDescription());
      }
      notifyObservers(orderId, itemDescriptions, total);
      return true;
    }

    return false;
  }

  public void displayOrder() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("ORDER #" + orderId + " - " + orderType);
    System.out.println("=".repeat(60));

    if (items.isEmpty()) {
      System.out.println("No items in order");
      return;
    }

    System.out.println("\nItems:");
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getDescription());
    }

    System.out.println("\n" + "-".repeat(60));
    System.out.printf("Subtotal:        $%8.2f\n", getSubtotal());
    System.out.printf("Tax (10%%):       $%8.2f\n", getTax());

    double discount = getDiscount();
    if (discount > 0) {
      System.out.printf("Discount (%s): -$%8.2f\n",
          discountStrategy.getDiscountName(), discount);
    }

    System.out.println("-".repeat(60));
    System.out.printf("TOTAL:           $%8.2f\n", getTotal());
    System.out.println("=".repeat(60));
  }

  public int getOrderId() {
    return orderId;
  }
}
