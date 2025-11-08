package com.restaurant.notification;

import java.util.List;

/**
 * Observer Pattern: Observer interface
 */
public interface Observer {
    void update(int orderId, List<String> items, double total);
}
