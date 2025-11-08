package com.restaurant.menuitem;

public class Burger extends MenuItem {
    private String meatType;

    public Burger(String name, double price, String meatType) {
        super(name, price, "Burger");
        this.meatType = meatType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return String.format("%s %s - $%.2f", meatType, name, price);
    }
}
