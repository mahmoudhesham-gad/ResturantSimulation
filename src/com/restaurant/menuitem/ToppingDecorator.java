package com.restaurant.menuitem;

public class ToppingDecorator extends AddOnDecorator {
    private double addonPrice = 1.00;
    private String topping;

    public ToppingDecorator(MenuItem menuItem, String topping) {
        super(menuItem);
        this.topping = topping;
    }

    @Override
    public double getPrice() {
        return menuItem.getPrice() + addonPrice;
    }

    @Override
    public String getDescription() {
        return menuItem.getDescription() + String.format(" + %s (+$%.2f)", topping, addonPrice);
    }
}
