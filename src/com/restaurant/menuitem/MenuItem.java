package com.restaurant.menuitem;

/**
 * Abstract base class for all menu items
 * Follows Single Responsibility Principle - only manages menu item data
 */
public abstract class MenuItem {
    protected String name;
    protected double price;
    protected String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public abstract String getName();
    public abstract double getPrice();
    public abstract String getCategory();
    public abstract String getDescription();
}
