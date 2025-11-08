package com.restaurant.discount;

import com.restaurant.menuitem.MenuItem;
import java.util.List;

/**
 * Concrete Strategy: 20% discount on meat items
 */
public class MeatDiscount implements DiscountStrategy {
    private double discountRate = 0.20;

    @Override
    public double calculateDiscount(List<MenuItem> items) {
        double discount = 0.0;
        for (MenuItem item : items) {
            String desc = item.getDescription().toLowerCase();
            if (desc.contains("beef") || desc.contains("meat")) {
                discount += item.getPrice() * discountRate;
            }
        }
        return discount;
    }

    @Override
    public String getDiscountName() {
        return String.format("Meat Monday (%d%% off)", (int)(discountRate * 100));
    }
}
