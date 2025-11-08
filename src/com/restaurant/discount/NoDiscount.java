package com.restaurant.discount;

import com.restaurant.menuitem.MenuItem;
import java.util.List;

/**
 * Concrete Strategy: No discount applied
 */
public class NoDiscount implements DiscountStrategy {

    @Override
    public double calculateDiscount(List<MenuItem> items) {
        return 0.0;
    }

    @Override
    public String getDiscountName() {
        return "No Discount";
    }
}
