package com.restaurant.menuitem;

/**
 * Decorator Pattern: Base decorator for menu item add-ons
 * Located with menu items as it decorates them
 */
public abstract class AddOnDecorator extends MenuItem {
    protected MenuItem menuItem;

    public AddOnDecorator(MenuItem menuItem) {
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getCategory());
        this.menuItem = menuItem;
    }

    @Override
    public String getName() {
        return menuItem.getName();
    }

    @Override
    public double getPrice() {
        return menuItem.getPrice();
    }

    @Override
    public String getCategory() {
        return menuItem.getCategory();
    }

    @Override
    public String getDescription() {
        return menuItem.getDescription();
    }
}
