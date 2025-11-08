package com.restaurant.discount;

import com.restaurant.menuitem.MenuItem;
import java.util.List;

/**
 * Concrete Strategy: Fixed discount for combo orders (3+ items)
 */
public class ComboDiscount implements DiscountStrategy {
    private int minItems = 3;
    private double discountAmount = 5.00;

    @Override
    public double calculateDiscount(List<MenuItem> items) {
        if (items.size() >= minItems) {
            return discountAmount;
        }
        return 0.0;
    }

    @Override
    public String getDiscountName() {
        return String.format("Combo Deal ($%.2f off for %d+ items)", discountAmount, minItems);
    }
}
