package com.restaurant.menuitem;

public class Pizza extends MenuItem {
    private String pizzaType;

    public Pizza(String name, double price, String pizzaType) {
        super(name, price, "Pizza");
        this.pizzaType = pizzaType;
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
        return String.format("%s %s - $%.2f", pizzaType, name, price);
    }
}
