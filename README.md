# Restaurant Ordering & Billing System

A modular and extensible restaurant ordering system demonstrating **SOLID principles** and **Design Patterns** in Java.

## 🎯 Project Overview

This system simulates a complete restaurant workflow from ordering to billing, featuring:

- Interactive menu browsing with multiple categories
- Customizable meals with add-ons (decorators)
- Multiple payment methods
- Dynamic discount strategies
- Real-time notifications to kitchen and waiters
- Menu builder pattern for organizing items

## 📁 Project Structure (Feature-Based Organization)

```
src/
├── menuitem/              # Menu Item Component (Factory Pattern)
│   ├── MenuItem.java      # Abstract base class
│   ├── Pizza.java, Burger.java, Salad.java, Pasta.java, Drink.java
│   └── MenuItemFactory.java        # Factory Method Pattern
│
├── addon/                 # Add-on Decorators (Decorator Pattern)
│   ├── AddOnDecorator.java         # Abstract decorator
│   └── ExtraCheeseDecorator.java, SauceDecorator.java,
│       BaconDecorator.java, ToppingDecorator.java, ExtraMeatDecorator.java
│
├── menu/                  # Menu Management (Builder Pattern)
│   ├── Menu.java          # Menu container
│   └── MenuBuilder.java   # Builder for creating menus
│
├── order/                 # Order Management Component
│   ├── Order.java         # Order with payment and discount handling
│   └── Restaurant.java    # Restaurant coordinator
│
├── payment/               # Payment Component (Strategy Pattern)
│   ├── PaymentStrategy.java
│   └── CashPayment.java, CreditCardPayment.java, MobileWalletPayment.java
│
├── discount/              # Discount Component (Strategy Pattern)
│   ├── DiscountStrategy.java
│   └── MondayDiscount.java, FridayDiscount.java, CustomDiscount.java
│
├── notification/          # Notification Component (Observer Pattern)
│   ├── Observer.java, Subject.java
│   └── Kitchen.java, Waiter.java
│
└── Main.java              # Interactive application entry point
```

## 🎨 Design Patterns Implemented

### 1. **Factory Method Pattern** (`menuitem/`)

- **Purpose**: Create different menu items (Pizza, Burger, Salad, Pasta, Drink)
- **Benefit**: Encapsulates object creation, easy to add new items

### 2. **Decorator Pattern** (`addon/`)

- **Purpose**: Add customizations (extra cheese, sauces, toppings, bacon, extra meat) dynamically
- **Benefit**: Flexible alternative to subclassing, chain multiple decorators

### 3. **Builder Pattern** (`menu/`)

- **Purpose**: Construct menu objects with multiple items in a step-by-step manner
- **Benefit**: Separates menu construction from representation, creates different menu types

### 4. **Observer Pattern** (`notification/`)

- **Purpose**: Notify Kitchen and Waiters when orders are placed
- **Benefit**: Loose coupling between Order and notification recipients

### 5. **Strategy Pattern - Payment** (`payment/`)

- **Purpose**: Support multiple payment methods interchangeably
- **Benefit**: Easy to add new payment methods without changing Order class

### 6. **Strategy Pattern - Discount** (`discount/`)

- **Purpose**: Apply different discount algorithms (Monday specials, Friday deals, custom discounts)
- **Benefit**: Each discount logic is encapsulated and swappable

## 🔧 SOLID Principles

### Single Responsibility Principle (SRP)

- Each class has one reason to change
- `MenuItem`: Manages item data only
- `MenuItemFactory`: Creates menu items only
- Each decorator: Adds one type of customization
- Each strategy: Implements one payment/discount method

### Open/Closed Principle (OCP)

- Open for extension, closed for modification
- Add new menu items by extending `MenuItem`
- Add new decorators without changing existing ones
- Add new payment/discount strategies without modifying `Order`

### Liskov Substitution Principle (LSP)

- All `MenuItem` subclasses interchangeable
- All decorators can wrap any `MenuItem`
- All strategies swappable in their contexts

### Interface Segregation Principle (ISP)

- Small, focused interfaces
- `Observer`: Only `update()` method
- `PaymentStrategy`: Only `pay()` and `getPaymentType()`
- `DiscountStrategy`: Only `calculateDiscount()` and `getDiscountName()`

### Dependency Inversion Principle (DIP)

- Depend on abstractions, not concrete classes
- `Order` depends on `PaymentStrategy` interface, not concrete classes
- `Subject` depends on `Observer` interface

## 🚀 How to Run the Project

### Prerequisites

- Java 8 or higher installed on your system
- Terminal/Command prompt

### Compile the Project

Navigate to the `src` directory and compile all Java files:

```bash
cd src
javac *.java addon/*.java discount/*.java menu/*.java menuitem/*.java notification/*.java order/*.java payment/*.java
```

### Run the Application

After compilation, run the main program:

```bash
java Main
```

### Using a Single Command

You can compile and run in one step:

```bash
cd src && javac *.java addon/*.java discount/*.java menu/*.java menuitem/*.java notification/*.java order/*.java payment/*.java && java Main
```

## 🧪 Test Case Example

The application provides an interactive ordering system. Here's a sample test scenario:

### Test Scenario: Customer Orders a Customized Meal

**Steps:**

1. Run the application: `java Main`
2. Select a restaurant from the list
3. Choose "Place a new order"
4. Select a menu category (e.g., "Non-Vegetarian Menu")
5. Add items to your order:
   - Select a Pepperoni Pizza
   - Apply decorators: Add Extra Cheese (+$1.50) and Extra Meat (+$2.50)
   - Select a Coke drink
6. Finish adding items
7. Apply a discount (e.g., Monday Discount for 15% off)
8. Choose payment method (e.g., Credit Card)
9. Complete the order

**Expected Output:**

```
============================================================
ORDER #1001 - Dine-in
============================================================

Items:
1. Classic Pepperoni Pizza - $13.99 + Extra Cheese (+$1.50) + Extra Meat (+$2.50)
2. Coca Cola - $2.99

------------------------------------------------------------
Subtotal:        $   20.98
Tax (10%):       $    2.10
Discount (Monday Discount (15% off)): -$    3.15
------------------------------------------------------------
TOTAL:           $   19.93
============================================================

Processing Credit Card Payment of $19.93
Card ending in: ****1234
Payment successful!

[KITCHEN NOTIFICATION]
New Order #1001 received!
Items to prepare:
  - Classic Pepperoni Pizza - $13.99 + Extra Cheese (+$1.50) + Extra Meat (+$2.50)
  - Coca Cola - $2.99

[WAITER NOTIFICATION]
New Order #1001 for Table service!
Total: $19.93
```

**Verification Points:**

- ✓ Factory pattern creates the correct menu items
- ✓ Decorator pattern applies add-ons with correct pricing
- ✓ Builder pattern organizes menu categories
- ✓ Strategy pattern applies the correct discount algorithm
- ✓ Strategy pattern processes payment successfully
- ✓ Observer pattern notifies Kitchen and Waiter
- ✓ Final price calculation includes all add-ons, tax, and discount

## 🔄 Extensibility Examples

### Add New Menu Item:

```java
// 1. Create new class extending MenuItem
public class Dessert extends MenuItem {
    public Dessert(String name, String variant, double price) {
        super(name, variant, price, "Dessert");
    }
}

// 2. Add to MenuItemFactory
case "dessert":
    return createDessert(variant);

// No changes to existing code!
```

### Add New Decorator:

```java
// Create new decorator extending AddOnDecorator
public class SpiceDecorator extends AddOnDecorator {
    public SpiceDecorator(MenuItem menuItem) {
        super(menuItem, "Spicy Sauce", 0.50);
    }
}
// Use it: item = new SpiceDecorator(item);
```

### Add New Payment Method:

```java
// Implement PaymentStrategy interface
public class CryptoPayment implements PaymentStrategy {
    private String walletAddress;

    @Override
    public boolean pay(double amount) {
        // Implementation
    }

    @Override
    public String getPaymentType() {
        return "Cryptocurrency";
    }
}
```

### Add New Discount:

```java
// Implement DiscountStrategy interface
public class WeekendDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * 0.20; // 20% off
    }

    @Override
    public String getDiscountName() {
        return "Weekend Special (20% off)";
    }
}
```

## 📦 Dependencies

- Java 8 or higher
- No external libraries required

## 👥 Team Information

This project is designed as an ASE (Advanced Software Engineering) assignment demonstrating best practices in object-oriented design.

## 📄 Key Features

- ✅ Interactive console-based ordering system
- ✅ Multiple restaurant support with different menus
- ✅ Menu organization using Builder pattern
- ✅ Dynamic item customization with Decorator pattern
- ✅ Flexible payment and discount strategies
- ✅ Real-time notifications to kitchen and service staff
- ✅ Clean architecture following SOLID principles

## 📚 Additional Notes

- Code follows Java naming conventions
- All classes are properly documented with comments
- Feature-based organization keeps related classes together
- Each component is independently testable and maintainable
- Easy to extend without modifying existing code (Open/Closed Principle)
- Interactive menu system provides user-friendly experience

## 🎓 Learning Outcomes

This project demonstrates:

- Proper application of design patterns in real-world scenarios
- SOLID principles in practice
- Clean code architecture and separation of concerns
- Extensible and maintainable design
- Professional software engineering practices
- Interactive application development in Java
