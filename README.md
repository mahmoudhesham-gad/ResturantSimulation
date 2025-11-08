# Restaurant Ordering & Billing System

A modular and extensible restaurant ordering system demonstrating **SOLID principles** and **Design Patterns** in Java.

## 🎯 Project Overview

This system simulates a complete restaurant workflow from ordering to billing, featuring:
- Menu browsing with multiple item types
- Customizable meals with add-ons
- Multiple payment methods
- Dynamic discount strategies
- Real-time notifications to kitchen and waiters

## 📁 Project Structure (Feature-Based Organization)

```
src/com/restaurant/
├── menuitem/              # Menu Item Component (Factory + Decorator Patterns)
│   ├── MenuItem.java     # Abstract base class
│   ├── Pizza.java, Burger.java, Salad.java, Pasta.java, Drink.java
│   ├── MenuItemFactory.java        # Factory Method Pattern
│   ├── AddOnDecorator.java         # Decorator Pattern (Abstract)
│   └── ExtraCheeseDecorator.java, SauceDecorator.java, etc.
│
├── order/                 # Order Management Component
│   ├── Order.java        # Order with payment and discount handling
│   └── Restaurant.java   # Restaurant coordinator
│
├── payment/               # Payment Component (Strategy Pattern)
│   ├── PaymentStrategy.java
│   └── CashPayment.java, CreditCardPayment.java, MobileWalletPayment.java
│
├── discount/              # Discount Component (Strategy Pattern)
│   ├── DiscountStrategy.java
│   └── ChickenDiscount.java, MeatDiscount.java, PizzaDiscount.java, etc.
│
├── notification/          # Notification Component (Observer Pattern)
│   ├── Observer.java, Subject.java
│   └── Kitchen.java, Waiter.java
│
└── Main.java             # Application entry point with demo scenarios
```

## 🎨 Design Patterns Implemented

### 1. **Factory Method Pattern** (`menuitem/`)
- **Purpose**: Create different menu items (Pizza, Burger, Salad, Pasta, Drink)
- **Benefit**: Encapsulates object creation, easy to add new items

### 2. **Decorator Pattern** (`menuitem/`)
- **Purpose**: Add customizations (extra cheese, sauces, toppings) dynamically
- **Benefit**: Flexible alternative to subclassing, chain multiple decorators

### 3. **Observer Pattern** (`notification/`)
- **Purpose**: Notify Kitchen and Waiters when orders are placed
- **Benefit**: Loose coupling between Order and notification recipients

### 4. **Strategy Pattern - Payment** (`payment/`)
- **Purpose**: Support multiple payment methods interchangeably
- **Benefit**: Easy to add new payment methods without changing Order class

### 5. **Strategy Pattern - Discount** (`discount/`)
- **Purpose**: Apply different discount algorithms based on item categories
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

## 🚀 How to Build & Run

### Compile:
```bash
cd src
javac com/restaurant/**/*.java com/restaurant/*.java
```

### Run:
```bash
java com.restaurant.Main
```

### Or using a single command:
```bash
cd src && javac com/restaurant/**/*.java com/restaurant/*.java && java com.restaurant.Main
```

## 📝 Test Scenarios Included

The `Main.java` demonstrates 5 complete scenarios:

### Scenario 1: Pizza Party
- 3 customized pizzas + drink
- **Discount**: 10% off all pizzas
- **Payment**: Credit Card

### Scenario 2: Chicken Special Day
- Chicken burger + chicken salad + drinks
- **Discount**: 15% off chicken items
- **Payment**: Mobile Wallet

### Scenario 3: Meat Monday
- Beef burger + bolognese pasta + salad
- **Discount**: 20% off meat/beef items
- **Payment**: Cash

### Scenario 4: Combo Deal
- Burger + pasta + salad + drink (4 items)
- **Discount**: $5 off for 3+ items
- **Payment**: Credit Card

### Scenario 5: Highly Customized Order
- Heavily decorated burger and pizza
- **Discount**: None
- **Payment**: Mobile Wallet

## 📊 Sample Output

```
============================================================
ORDER #1001 - Dine-in
============================================================

Items:
1. Italian Margherita Pizza - $12.99 + Extra Cheese (+$1.50) + Olives (+$1.00)
2. Classic Pepperoni Pizza - $13.99 + Extra Meat (+$2.50)
3. Eastern Shawarma Pizza - $14.99
4. Coca Cola - $2.99

------------------------------------------------------------
Subtotal:        $   49.96
Tax (10%):       $    5.00
Discount (Pizza Party (10% off)): -$    4.70
------------------------------------------------------------
TOTAL:           $   50.26
============================================================

Processing Credit Card Payment of $50.26
Card ending in: ****3456
Payment successful!

[KITCHEN NOTIFICATION]
New Order #1001 received!
Items to prepare:
  - Italian Margherita Pizza - $12.99 + Extra Cheese (+$1.50) + Olives (+$1.00)
  ...
```

## 🔄 Extensibility Examples

### Add New Menu Item:
```java
// 1. Create new class
public class Dessert extends MenuItem { ... }

// 2. Add to factory
public static MenuItem createDessert(String variant) { ... }

// No changes to existing code!
```

### Add New Decorator:
```java
// Create new decorator
public class SpiceDecorator extends AddOnDecorator {
    @Override
    public double getPrice() {
        return menuItem.getPrice() + 0.50;
    }
    ...
}
// Use it: item = new SpiceDecorator(item);
```

### Add New Payment Method:
```java
// Implement interface
public class CryptoPayment implements PaymentStrategy { ... }

// Use it
order.setPaymentStrategy(new CryptoPayment("wallet_address"));
```

### Add New Discount:
```java
// Implement interface
public class SeafoodDiscount implements DiscountStrategy { ... }

// Use it
order.setDiscountStrategy(new SeafoodDiscount());
```

## 📦 Dependencies

- Java 8 or higher
- No external libraries required

## 👥 Team Information

This project is designed for a team of 2 students as per assignment requirements.

## 📄 Deliverables

- ✅ Source Code (fully functional and commented)
- ✅ UML Class Diagram (see JAVA_STRUCTURE.md)
- ✅ Design Decisions Document (see JAVA_STRUCTURE.md)
- ✅ README with instructions and test cases

## 🏆 Evaluation Criteria Coverage

| Criteria | Coverage |
|----------|----------|
| **SOLID Principles (20%)** | All 5 principles demonstrated |
| **Design Patterns (50%)** | 4 patterns correctly implemented |
| **Code Structure (10%)** | Feature-based organization, clean code |
| **UML & Clarity (20%)** | Clear structure, well-documented |

## 📚 Additional Notes

- Code follows Java naming conventions
- All classes are properly documented
- Feature-based organization keeps related classes together
- Each component is independently testable
- Easy to extend without modifying existing code

## 🎓 Learning Outcomes

This project demonstrates:
- Proper application of design patterns
- SOLID principles in practice
- Clean code architecture
- Extensible and maintainable design
- Real-world software engineering practices
