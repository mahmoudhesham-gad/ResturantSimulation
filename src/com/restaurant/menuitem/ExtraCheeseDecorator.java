package com.restaurant.menuitem;

public class ExtraCheeseDecorator extends AddOnDecorator {
    private double addonPrice = 1.50;

    public ExtraCheeseDecorator(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public double getPrice() {
        return menuItem.getPrice() + addonPrice;
    }

    @Override
    public String getDescription() {
        return menuItem.getDescription() + String.format(" + Extra Cheese (+$%.2f)", addonPrice);
    }
}
