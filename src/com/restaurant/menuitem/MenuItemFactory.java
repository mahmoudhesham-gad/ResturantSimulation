package com.restaurant.menuitem;

/**
 * Factory Method Pattern: Creates different types of menu items
 * Located with menu item classes for cohesion
 */
public class MenuItemFactory {
    
    public static MenuItem createPizza(String variant) {
        switch (variant.toLowerCase()) {
            case "italian":
                return new Pizza("Margherita Pizza", 12.99, "Italian");
            case "eastern":
                return new Pizza("Shawarma Pizza", 14.99, "Eastern");
            case "pepperoni":
                return new Pizza("Pepperoni Pizza", 13.99, "Classic");
            case "vegetarian":
                return new Pizza("Veggie Pizza", 11.99, "Vegetarian");
            default:
                return new Pizza("Classic Pizza", 10.99, "Classic");
        }
    }
    
    public static MenuItem createBurger(String variant) {
        switch (variant.toLowerCase()) {
            case "chicken":
                return new Burger("Chicken Burger", 8.99, "Chicken");
            case "beef":
                return new Burger("Beef Burger", 10.99, "Beef");
            case "veggie":
                return new Burger("Veggie Burger", 7.99, "Vegetable");
            case "classic":
                return new Burger("Classic Burger", 9.99, "Classic");
            default:
                return new Burger("Basic Burger", 7.99, "Basic");
        }
    }
    
    public static MenuItem createSalad(String variant) {
        switch (variant.toLowerCase()) {
            case "caesar":
                return new Salad("Caesar Salad", 6.99);
            case "greek":
                return new Salad("Greek Salad", 7.99);
            case "garden":
                return new Salad("Garden Salad", 5.99);
            case "chicken":
                return new Salad("Chicken Salad", 8.99);
            default:
                return new Salad("Mixed Salad", 5.99);
        }
    }
    
    public static MenuItem createPasta(String variant) {
        switch (variant.toLowerCase()) {
            case "carbonara":
                return new Pasta("Carbonara", 11.99, "Cream Sauce");
            case "bolognese":
                return new Pasta("Bolognese", 12.99, "Meat Sauce");
            case "marinara":
                return new Pasta("Marinara", 10.99, "Tomato Sauce");
            case "alfredo":
                return new Pasta("Alfredo", 11.99, "Alfredo Sauce");
            default:
                return new Pasta("Basic Pasta", 9.99, "Tomato Sauce");
        }
    }
    
    public static MenuItem createDrink(String variant) {
        switch (variant.toLowerCase()) {
            case "coke":
                return new Drink("Coca Cola", 2.99);
            case "sprite":
                return new Drink("Sprite", 2.99);
            case "water":
                return new Drink("Water", 1.99);
            case "juice":
                return new Drink("Orange Juice", 3.99);
            case "coffee":
                return new Drink("Coffee", 2.49);
            default:
                return new Drink("Soft Drink", 2.49);
        }
    }
    
    public static MenuItem createItem(String itemType, String variant) {
        switch (itemType.toLowerCase()) {
            case "pizza":
                return createPizza(variant);
            case "burger":
                return createBurger(variant);
            case "salad":
                return createSalad(variant);
            case "pasta":
                return createPasta(variant);
            case "drink":
                return createDrink(variant);
            default:
                throw new IllegalArgumentException("Unknown item type: " + itemType);
        }
    }
}
