package com.restaurant.notification;

import java.util.List;

/**
 * Concrete Observer: Waiter receives order notifications
 */
public class Waiter implements Observer {
    private String name;

    public Waiter(String name) {
        this.name = name;
    }

    @Override
    public void update(int orderId, List<String> items, double total) {
        System.out.println("\n[WAITER NOTIFICATION - " + name + "]");
        System.out.println("Order #" + orderId + " placed!");
        System.out.println("Number of items: " + items.size());
        System.out.printf("Total amount: $%.2f\n", total);
        System.out.println("Please serve the customer when ready.");
    }

    public String getName() {
        return name;
    }
}
