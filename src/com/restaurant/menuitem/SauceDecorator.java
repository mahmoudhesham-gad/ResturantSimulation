package com.restaurant.menuitem;

public class SauceDecorator extends AddOnDecorator {
    private double addonPrice = 0.75;
    private String sauceType;

    public SauceDecorator(MenuItem menuItem, String sauceType) {
        super(menuItem);
        this.sauceType = sauceType;
    }

    @Override
    public double getPrice() {
        return menuItem.getPrice() + addonPrice;
    }

    @Override
    public String getDescription() {
        return menuItem.getDescription() + String.format(" + %s Sauce (+$%.2f)", sauceType, addonPrice);
    }
}
