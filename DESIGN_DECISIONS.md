# Design Decisions Document

## Restaurant Ordering & Billing System

### Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [Design Pattern Choices](#design-pattern-choices)
3. [SOLID Principles Application](#solid-principles-application)
4. [Package Organization](#package-organization)
5. [Key Design Trade-offs](#key-design-trade-offs)

---

## Architecture Overview

### Feature-Based Package Structure

We chose a **feature-based** (vertical slicing) organization rather than a traditional layered approach:

**Rationale:**
- **High Cohesion**: Related classes stay together (e.g., all menu item logic in `menuitem/`)
- **Low Coupling**: Each package is self-contained with its patterns
- **Better Navigation**: Developers can find all related code in one place
- **Easier Testing**: Each feature package can be tested independently
- **Scalability**: Easy to add new features without affecting existing ones

### Package Breakdown

```
menuitem/     → Menu items + Factory + Decorator patterns
order/        → Order management logic
payment/      → Payment strategies
discount/     → Discount strategies
notification/ → Observer pattern for notifications
```

---

## Design Pattern Choices

### 1. Factory Method Pattern (menuitem/)

**Decision**: Use Factory Method for creating menu items

**Rationale:**
- **Requirement**: "Prepare specific menu items dynamically based on the order request"
- **Benefits**:
  - Encapsulates object creation
  - Easy to add new menu items without modifying existing code (OCP)
  - Centralizes item creation logic
  - Supports variants (Italian Pizza, Eastern Pizza, etc.)

**Alternative Considered**: Direct instantiation
- **Rejected**: Would scatter creation logic throughout the application

**Implementation Details:**
- Single factory class with static methods for simplicity
- Switch statements for variant selection (easily replaceable with map-based approach)
- Returns MenuItem interface type for flexibility

---

### 2. Decorator Pattern (menuitem/)

**Decision**: Use Decorator for add-ons/customizations

**Rationale:**
- **Requirement**: "Allow customers to customize their meals with add-ons"
- **Benefits**:
  - Dynamic addition of features at runtime
  - Can chain multiple decorators (e.g., extra cheese + bacon + sauce)
  - Avoids class explosion (no need for CheeseAndBaconBurger class)
  - Each decorator adds specific cost and description
  - Follows Single Responsibility (each decorator handles one add-on)

**Alternative Considered**: Subclassing
- **Rejected**: Would require exponential number of subclasses for all combinations

**Implementation Details:**
- `AddOnDecorator` extends `MenuItem` and wraps another `MenuItem`
- Each concrete decorator overrides `getPrice()` and `getDescription()`
- Decorators can be chained: `new Cheese(new Bacon(new Sauce(burger)))`

---

### 3. Observer Pattern (notification/)

**Decision**: Use Observer for kitchen and waiter notifications

**Rationale:**
- **Requirement**: "Kitchen and waiter systems should be acknowledged with the new order"
- **Benefits**:
  - Loose coupling between Order and observers
  - Easy to add more observers (e.g., delivery service, billing)
  - Order doesn't need to know about observer implementations
  - Supports multiple simultaneous observers
  - Automatic notification on order placement

**Alternative Considered**: Direct method calls
- **Rejected**: Would create tight coupling and violate DIP

**Implementation Details:**
- `Subject` class manages observer list
- `Order` extends `Subject` to inherit notification capability
- `Observer` interface with single `update()` method (ISP)
- Kitchen and Waiter implement Observer

---

### 4. Strategy Pattern - Payment (payment/)

**Decision**: Use Strategy for payment methods

**Rationale:**
- **Requirement**: "Support multiple payment methods such as Cash, Credit Card, and Mobile Wallets"
- **Benefits**:
  - Swappable algorithms at runtime
  - Each payment method encapsulated in its own class (SRP)
  - Easy to add new payment methods without modifying Order (OCP)
  - Order depends on PaymentStrategy interface, not concrete classes (DIP)

**Alternative Considered**: If-else or switch statements
- **Rejected**: Would violate OCP and create monolithic payment logic

**Implementation Details:**
- `PaymentStrategy` interface with `pay()` and `getPaymentType()`
- Each concrete strategy implements its own payment logic
- Order holds a PaymentStrategy reference

---

### 5. Strategy Pattern - Discount (discount/)

**Decision**: Use Strategy for discount calculations

**Rationale:**
- **Requirement**: "Create various discount strategies based on item categories"
- **Benefits**:
  - Each discount logic encapsulated (SRP)
  - Easily add new discount types (OCP)
  - Discounts interchangeable at runtime
  - Complex discount calculations isolated

**Alternative Considered**: Multiple discount methods in Order class
- **Rejected**: Would bloat Order class and violate SRP

**Implementation Details:**
- `DiscountStrategy` interface with `calculateDiscount()` and `getDiscountName()`
- Category-based (Pizza, Chicken, Meat) and rule-based (Combo) strategies
- Each strategy iterates items and applies its own logic

---

## SOLID Principles Application

### Single Responsibility Principle (SRP)

**Applied In:**
- `MenuItem`: Only manages item data and description
- `MenuItemFactory`: Only creates menu items
- `Order`: Only manages order items and workflow
- Each decorator: Only adds one type of customization
- Each payment strategy: Only handles one payment method
- Each discount strategy: Only calculates one discount type

**Example:**
```java
// Before (violates SRP): Order handles everything
class Order {
    void processPayment() { /* payment logic */ }
    double calculateDiscount() { /* discount logic */ }
    void notifyKitchen() { /* notification logic */ }
}

// After (follows SRP): Separated responsibilities
class Order {
    PaymentStrategy payment;    // Delegated
    DiscountStrategy discount;  // Delegated
    extends Subject             // Delegated
}
```

---

### Open/Closed Principle (OCP)

**Applied In:**
- **Menu Items**: Add new items by extending MenuItem, no factory changes needed
- **Decorators**: Add new decorators without modifying existing ones
- **Payment**: Add new payment methods without changing Order
- **Discount**: Add new discount types without changing Order

**Example:**
```java
// Adding new payment method (OPEN for extension)
public class CryptoPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) { /* crypto logic */ }
}

// No changes to Order class (CLOSED for modification)
order.setPaymentStrategy(new CryptoPayment());
```

---

### Liskov Substitution Principle (LSP)

**Applied In:**
- All `MenuItem` subclasses can substitute the base class
- All decorators can wrap any `MenuItem`
- All payment strategies interchangeable
- All discount strategies interchangeable

**Example:**
```java
// Any MenuItem can be used
MenuItem item = new Pizza(...);  // OR
MenuItem item = new Burger(...); // Both work identically

// Any decorator can wrap any item
item = new ExtraCheeseDecorator(item);
item = new BaconDecorator(item);
```

---

### Interface Segregation Principle (ISP)

**Applied In:**
- `Observer`: Only `update()` method (observers don't need attach/detach)
- `PaymentStrategy`: Only `pay()` and `getPaymentType()`
- `DiscountStrategy`: Only `calculateDiscount()` and `getDiscountName()`

**Example:**
```java
// Small, focused interfaces
interface Observer {
    void update(int orderId, List<String> items, double total);
}

// Instead of fat interface:
// interface OrderListener {
//     void onOrderPlaced();
//     void onOrderCancelled();
//     void onOrderModified();
//     void onPaymentProcessed();
//     void onRefund();
// }
```

---

### Dependency Inversion Principle (DIP)

**Applied In:**
- `Order` depends on `PaymentStrategy` interface, not concrete classes
- `Order` depends on `DiscountStrategy` interface, not concrete classes
- `Subject` depends on `Observer` interface, not Kitchen/Waiter
- `AddOnDecorator` depends on `MenuItem` abstraction

**Example:**
```java
// High-level module (Order) depends on abstraction
class Order {
    private PaymentStrategy paymentStrategy;  // Interface
    private DiscountStrategy discountStrategy; // Interface
}

// Low-level modules implement abstractions
class CashPayment implements PaymentStrategy { }
class CreditCardPayment implements PaymentStrategy { }
```

---

## Package Organization

### Decision: Feature-Based vs Layered

**Chosen**: Feature-Based (Vertical Slicing)

```
✅ Feature-Based:
menuitem/
  - MenuItem.java
  - MenuItemFactory.java (Factory pattern with items)
  - AddOnDecorator.java (Decorator pattern with items)
  
payment/
  - PaymentStrategy.java
  - CashPayment.java
  - CreditCardPayment.java
  
❌ Layered (Not chosen):
models/
  - MenuItem.java
  - Order.java
  
patterns/
  - factory/MenuItemFactory.java
  - decorator/AddOnDecorator.java
  - strategy/PaymentStrategy.java
```

**Rationale:**
1. **Cohesion**: Related classes stay together
2. **Understanding**: "I need menu logic" → go to `menuitem/`
3. **Changes**: Modify menu feature without touching other packages
4. **Testing**: Test entire menu feature in isolation

---

## Key Design Trade-offs

### 1. Single Factory vs Multiple Factories

**Decision**: Single `MenuItemFactory` with multiple creation methods

**Trade-off:**
- ✅ **Pro**: Simpler, all item creation in one place
- ❌ **Con**: Factory grows as more items added
- **Mitigation**: Can easily refactor to separate factories if needed

---

### 2. Abstract Class vs Interface for Decorator

**Decision**: `AddOnDecorator` extends `MenuItem` (abstract class)

**Trade-off:**
- ✅ **Pro**: Inherits MenuItem behavior, less code duplication
- ✅ **Pro**: Can provide default implementations
- ❌ **Con**: Java single inheritance limitation
- **Justification**: Decorators ARE menu items (is-a relationship)

---

### 3. Order extends Subject vs Composition

**Decision**: `Order extends Subject`

**Trade-off:**
- ✅ **Pro**: Cleaner syntax, Order IS a subject
- ✅ **Pro**: Notification methods directly available
- ❌ **Con**: Uses inheritance instead of composition
- **Justification**: Order inherently needs to notify observers, strong is-a relationship

---

### 4. Static Factory Methods

**Decision**: Static methods in `MenuItemFactory`

**Trade-off:**
- ✅ **Pro**: Simple to use, no instantiation needed
- ✅ **Pro**: Stateless, thread-safe
- ❌ **Con**: Cannot be overridden, harder to mock
- **Justification**: Factory doesn't need state, simplicity preferred for this use case

---

## Extensibility Scenarios

### Adding New Feature: Loyalty Program

```java
// 1. Create new package
loyalty/
  - LoyaltyStrategy.java
  - PointsCalculator.java
  - MemberDiscount.java

// 2. Integrate with Order
class Order {
    private LoyaltyStrategy loyalty;
    
    public void applyLoyaltyPoints() {
        double points = loyalty.calculatePoints(items);
        // Award points
    }
}
```

### Adding New Feature: Table Reservation

```java
// 1. Create new package
reservation/
  - Table.java
  - ReservationManager.java

// 2. Link to Order
class Order {
    private Table assignedTable;
}
```

---

## Conclusion

The design emphasizes:
- ✅ **Maintainability**: Easy to understand and modify
- ✅ **Extensibility**: Simple to add new features
- ✅ **Testability**: Each component independently testable
- ✅ **SOLID Compliance**: All principles demonstrated
- ✅ **Pattern Usage**: Appropriate patterns for requirements
- ✅ **Clean Architecture**: Clear separation of concerns

The feature-based organization and careful pattern selection create a system that is both powerful and easy to extend, meeting all assignment requirements while maintaining code quality and design principles.
