package com.restaurant.discount;

import com.restaurant.menuitem.MenuItem;
import java.util.List;

/**
 * Strategy Pattern: Discount strategy interface
 */
public interface DiscountStrategy {
    double calculateDiscount(List<MenuItem> items);
    String getDiscountName();
}
