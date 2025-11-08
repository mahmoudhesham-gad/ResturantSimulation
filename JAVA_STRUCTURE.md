# Restaurant Ordering & Billing System - Java Implementation Plan

## Project Structure (Organized by Feature/Component)

```
restaurant_soft/
├── src/
│   └── com/
│       └── restaurant/
│           ├── menuitem/                    # Menu Item Component with Factory Pattern
│           │   ├── MenuItem.java           # Abstract base class
│           │   ├── Pizza.java
│           │   ├── Burger.java
│           │   ├── Salad.java
│           │   ├── Pasta.java
│           │   ├── Drink.java
│           │   ├── MenuItemFactory.java    # Factory Pattern
│           │   ├── AddOnDecorator.java     # Decorator Pattern (Abstract)
│           │   ├── ExtraCheeseDecorator.java
│           │   ├── SauceDecorator.java
│           │   ├── ToppingDecorator.java
│           │   ├── ExtraMeatDecorator.java
│           │   └── BaconDecorator.java
│           │
│           ├── order/                       # Order Management Component
│           │   ├── Order.java              # Main order class
│           │   └── Restaurant.java         # Restaurant system coordinator
│           │
│           ├── payment/                     # Payment Component with Strategy Pattern
│           │   ├── PaymentStrategy.java    # Strategy Interface
│           │   ├── CashPayment.java
│           │   ├── CreditCardPayment.java
│           │   └── MobileWalletPayment.java
│           │
│           ├── discount/                    # Discount Component with Strategy Pattern
│           │   ├── DiscountStrategy.java   # Strategy Interface
│           │   ├── ChickenDiscount.java
│           │   ├── MeatDiscount.java
│           │   ├── PizzaDiscount.java
│           │   ├── ComboDiscount.java
│           │   └── NoDiscount.java
│           │
│           ├── notification/                # Notification Component with Observer Pattern
│           │   ├── Observer.java           # Observer Interface
│           │   ├── Subject.java            # Subject class
│           │   ├── Kitchen.java
│           │   └── Waiter.java
│           │
│           └── Main.java                    # Application entry point
│
├── README.md
├── DESIGN_DECISIONS.md
└── JAVA_STRUCTURE.md (this file)

```

## Design Patterns Applied (Organized by Component)

### 1. Factory Method Pattern
**Location:** `menuitem/MenuItemFactory.java`
**Component:** Menu Item Management
**Purpose:** Create different types of menu items (Pizza, Burger, Salad, Pasta, Drink) with various variants
**Rationale:** Encapsulates object creation logic, making it easy to add new menu items without modifying existing code

### 2. Decorator Pattern
**Location:** `menuitem/*Decorator.java`
**Component:** Menu Item Customization
**Purpose:** Dynamically add customizations (extra cheese, sauces, toppings) to menu items
**Rationale:** Provides flexible alternative to subclassing for extending functionality. Each decorator adds cost and modifies description

### 3. Observer Pattern
**Location:** `notification/` package
**Component:** Order Notification System
**Purpose:** Notify Kitchen and Waiters when orders are placed
**Rationale:** Establishes one-to-many dependency so multiple observers are automatically notified when order state changes

### 4. Strategy Pattern (Payment)
**Location:** `payment/` package
**Component:** Payment Processing
**Purpose:** Support multiple payment methods (Cash, Credit Card, Mobile Wallet)
**Rationale:** Encapsulates payment algorithms, making them interchangeable at runtime

### 5. Strategy Pattern (Discount)
**Location:** `discount/` package
**Component:** Discount Calculation
**Purpose:** Apply different discount strategies based on item categories
**Rationale:** Defines family of algorithms for discount calculation, each encapsulated and interchangeable

## SOLID Principles Implementation

### Single Responsibility Principle (SRP)
- Each class has one reason to change
- `MenuItem`: Only manages item data
- `Order`: Only manages order operations
- `MenuItemFactory`: Only creates menu items
- Each decorator: Only adds one type of addon
- Each strategy: Only implements one payment/discount method

### Open/Closed Principle (OCP)
- Classes open for extension, closed for modification
- New menu items: Extend MenuItem without changing factory
- New decorators: Add new decorator class without modifying existing ones
- New payment methods: Add new strategy without changing Order class
- New discounts: Add new discount strategy without modifying discount logic

### Liskov Substitution Principle (LSP)
- Derived classes substitutable for base classes
- All MenuItem subclasses can be used wherever MenuItem is expected
- All decorators can wrap any MenuItem
- All payment strategies interchangeable in Order
- All discount strategies interchangeable in Order

### Interface Segregation Principle (ISP)
- Clients not forced to depend on unused interfaces
- Observer interface: Only update() method needed
- PaymentStrategy interface: Only pay() and getPaymentType()
- DiscountStrategy interface: Only calculateDiscount() and getDiscountName()

### Dependency Inversion Principle (DIP)
- Depend on abstractions, not concrete classes
- Order depends on PaymentStrategy interface, not concrete payment classes
- Order depends on DiscountStrategy interface, not concrete discount classes
- Subject depends on Observer interface, not concrete Kitchen/Waiter
- Decorators depend on MenuItem abstraction

## Class Relationships

### Inheritance Hierarchy
```
MenuItem (Abstract)
├── Pizza
├── Burger
├── Salad
├── Pasta
└── Drink

AddOnDecorator (extends MenuItem)
├── ExtraCheeseDecorator
├── SauceDecorator
├── ToppingDecorator
├── ExtraMeatDecorator
└── BaconDecorator
```

### Interface Implementation
```
Observer (Interface)
├── Kitchen
└── Waiter

PaymentStrategy (Interface)
├── CashPayment
├── CreditCardPayment
└── MobileWalletPayment

DiscountStrategy (Interface)
├── ChickenDiscount
├── MeatDiscount
├── PizzaDiscount
├── ComboDiscount
└── NoDiscount
```

### Composition
```
Restaurant
├── Kitchen (1)
├── List<Waiter> (*)
└── Map<Integer, Order> (*)

Order extends Subject
├── List<MenuItem> (*)
├── PaymentStrategy (1)
└── DiscountStrategy (1)
```

## Key Features

1. **Factory Method**: Creates menu items with variants
2. **Decorator Pattern**: Chains multiple add-ons to customize items
3. **Observer Pattern**: Auto-notifies kitchen and waiters when order placed
4. **Strategy Pattern**: Swappable payment and discount algorithms
5. **Order Management**: Calculate subtotal, tax, discount, and total
6. **Receipt Generation**: Display formatted order details

## Test Scenarios

1. **Pizza Discount**: Multiple pizzas with 10% discount
2. **Chicken Discount**: Chicken items with 15% discount
3. **Meat Discount**: Beef/meat items with 20% discount
4. **Combo Discount**: 3+ items get $5 off
5. **Heavy Customization**: Multiple decorators on single item

## Build & Run Instructions

### Compile:
```bash
javac -d bin src/com/restaurant/**/*.java src/com/restaurant/*.java
```

### Run:
```bash
java -cp bin com.restaurant.Main
```

### Or compile and run in one step:
```bash
cd src
javac com/restaurant/**/*.java com/restaurant/*.java
java com.restaurant.Main
```

## Extensibility Examples

### Adding New Menu Item:
1. Create new class extending MenuItem
2. Add creation method in MenuItemFactory
3. No changes to existing code

### Adding New Decorator:
1. Create new class extending AddOnDecorator
2. Override get_price() and get_description()
3. No changes to existing decorators

### Adding New Payment Method:
1. Create new class implementing PaymentStrategy
2. Implement pay() and getPaymentType()
3. Use in Order without modifying Order class

### Adding New Discount:
1. Create new class implementing DiscountStrategy
2. Implement calculateDiscount() and getDiscountName()
3. Use in Order without modifying Order class
