package com.restaurant.discount;

import com.restaurant.menuitem.MenuItem;
import java.util.List;

/**
 * Concrete Strategy: 15% discount on chicken items
 */
public class ChickenDiscount implements DiscountStrategy {
    private double discountRate = 0.15;

    @Override
    public double calculateDiscount(List<MenuItem> items) {
        double discount = 0.0;
        for (MenuItem item : items) {
            String desc = item.getDescription();
            if (desc.contains("Chicken") || item.getCategory().equals("Chicken")) {
                discount += item.getPrice() * discountRate;
            }
        }
        return discount;
    }

    @Override
    public String getDiscountName() {
        return String.format("Chicken Special (%d%% off)", (int)(discountRate * 100));
    }
}
