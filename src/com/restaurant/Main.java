package com.restaurant;

import com.restaurant.order.Restaurant;
import com.restaurant.order.Order;
import com.restaurant.menuitem.*;
import com.restaurant.payment.*;
import com.restaurant.discount.*;

/**
 * Main application demonstrating all design patterns
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("RESTAURANT ORDERING & BILLING SYSTEM");
        System.out.println("Demonstrating Design Patterns Implementation");
        System.out.println("=".repeat(70));

        // Run all scenarios
        demoScenario1();  // Pizza discount
        demoScenario2();  // Chicken discount
        demoScenario3();  // Meat discount
        demoScenario4();  // Combo discount
        demoScenario5();  // No discount, heavy customization

        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("ALL SCENARIOS COMPLETED SUCCESSFULLY!");
        System.out.println("=".repeat(70));
    }

    /**
     * Scenario 1: Pizza Party with Multiple Pizzas
     */
    private static void demoScenario1() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("SCENARIO 1: Pizza Party with Multiple Pizzas");
        System.out.println("=".repeat(70));

        Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");
        restaurant.displayMenu();

        // Create order
        Order order = restaurant.createOrder("Dine-in");

        // Add pizzas with decorators
        MenuItem pizza1 = MenuItemFactory.createItem("pizza", "italian");
        pizza1 = new ExtraCheeseDecorator(pizza1);
        pizza1 = new ToppingDecorator(pizza1, "Olives");

        MenuItem pizza2 = MenuItemFactory.createItem("pizza", "pepperoni");
        pizza2 = new ExtraMeatDecorator(pizza2);

        MenuItem pizza3 = MenuItemFactory.createItem("pizza", "eastern");

        MenuItem drink = MenuItemFactory.createItem("drink", "coke");

        order.addItem(pizza1);
        order.addItem(pizza2);
        order.addItem(pizza3);
        order.addItem(drink);

        // Apply pizza discount
        order.setDiscountStrategy(new PizzaDiscount());

        // Set payment method
        order.setPaymentStrategy(new CreditCardPayment("1234567890123456", "123"));

        // Display and place order
        order.displayOrder();
        order.placeOrder();
    }

    /**
     * Scenario 2: Chicken Special Day
     */
    private static void demoScenario2() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("SCENARIO 2: Chicken Special Day");
        System.out.println("=".repeat(70));

        Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");

        Order order = restaurant.createOrder("Takeaway");

        // Add chicken items
        MenuItem chickenBurger = MenuItemFactory.createItem("burger", "chicken");
        chickenBurger = new BaconDecorator(chickenBurger);
        chickenBurger = new SauceDecorator(chickenBurger, "Honey Mustard");

        MenuItem chickenSalad = MenuItemFactory.createItem("salad", "chicken");

        MenuItem drink1 = MenuItemFactory.createItem("drink", "juice");
        MenuItem drink2 = MenuItemFactory.createItem("drink", "water");

        order.addItem(chickenBurger);
        order.addItem(chickenSalad);
        order.addItem(drink1);
        order.addItem(drink2);

        // Apply chicken discount
        order.setDiscountStrategy(new ChickenDiscount());

        // Set payment method
        order.setPaymentStrategy(new MobileWalletPayment("user@wallet.com"));

        order.displayOrder();
        order.placeOrder();
    }

    /**
     * Scenario 3: Meat Monday Special
     */
    private static void demoScenario3() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("SCENARIO 3: Meat Monday Special");
        System.out.println("=".repeat(70));

        Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");

        Order order = restaurant.createOrder("Delivery");

        // Add meat items
        MenuItem beefBurger = MenuItemFactory.createItem("burger", "beef");
        beefBurger = new ExtraCheeseDecorator(beefBurger);
        beefBurger = new ExtraMeatDecorator(beefBurger);

        MenuItem bolognese = MenuItemFactory.createItem("pasta", "bolognese");
        bolognese = new ToppingDecorator(bolognese, "Parmesan Cheese");

        MenuItem salad = MenuItemFactory.createItem("salad", "caesar");

        order.addItem(beefBurger);
        order.addItem(bolognese);
        order.addItem(salad);

        // Apply meat discount
        order.setDiscountStrategy(new MeatDiscount());

        // Set payment method
        order.setPaymentStrategy(new CashPayment());

        order.displayOrder();
        order.placeOrder();
    }

    /**
     * Scenario 4: Combo Deal (3+ items)
     */
    private static void demoScenario4() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("SCENARIO 4: Combo Deal (3+ items)");
        System.out.println("=".repeat(70));

        Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");

        Order order = restaurant.createOrder("Dine-in");

        // Add various items
        MenuItem burger = MenuItemFactory.createItem("burger", "classic");
        burger = new SauceDecorator(burger, "BBQ");

        MenuItem pasta = MenuItemFactory.createItem("pasta", "carbonara");

        MenuItem salad = MenuItemFactory.createItem("salad", "greek");

        MenuItem drink = MenuItemFactory.createItem("drink", "coffee");

        order.addItem(burger);
        order.addItem(pasta);
        order.addItem(salad);
        order.addItem(drink);

        // Apply combo discount
        order.setDiscountStrategy(new ComboDiscount());

        // Set payment method
        order.setPaymentStrategy(new CreditCardPayment("9876543210123456", "456"));

        order.displayOrder();
        order.placeOrder();
    }

    /**
     * Scenario 5: Highly Customized Order
     */
    private static void demoScenario5() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("SCENARIO 5: Highly Customized Order");
        System.out.println("=".repeat(70));

        Restaurant restaurant = new Restaurant("Delicious Bites Restaurant");

        Order order = restaurant.createOrder("Dine-in");

        // Create heavily customized burger
        MenuItem customBurger = MenuItemFactory.createItem("burger", "beef");
        customBurger = new ExtraCheeseDecorator(customBurger);
        customBurger = new BaconDecorator(customBurger);
        customBurger = new SauceDecorator(customBurger, "Spicy Mayo");
        customBurger = new ToppingDecorator(customBurger, "Grilled Onions");

        // Customized pizza
        MenuItem customPizza = MenuItemFactory.createItem("pizza", "vegetarian");
        customPizza = new ExtraCheeseDecorator(customPizza);
        customPizza = new ToppingDecorator(customPizza, "Mushrooms");

        MenuItem drink = MenuItemFactory.createItem("drink", "sprite");

        order.addItem(customBurger);
        order.addItem(customPizza);
        order.addItem(drink);

        // No discount
        order.setDiscountStrategy(new NoDiscount());

        // Set payment method
        order.setPaymentStrategy(new MobileWalletPayment("customer123@pay.com"));

        order.displayOrder();
        order.placeOrder();
    }
}
