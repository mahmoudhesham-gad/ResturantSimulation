package com.restaurant.discount;

import com.restaurant.menuitem.MenuItem;
import java.util.List;

/**
 * Concrete Strategy: 10% discount on pizza items
 */
public class PizzaDiscount implements DiscountStrategy {
    private double discountRate = 0.10;

    @Override
    public double calculateDiscount(List<MenuItem> items) {
        double discount = 0.0;
        for (MenuItem item : items) {
            if (item.getCategory().equals("Pizza")) {
                discount += item.getPrice() * discountRate;
            }
        }
        return discount;
    }

    @Override
    public String getDiscountName() {
        return String.format("Pizza Party (%d%% off)", (int)(discountRate * 100));
    }
}
