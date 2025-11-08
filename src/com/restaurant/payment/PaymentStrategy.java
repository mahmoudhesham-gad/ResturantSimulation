package com.restaurant.payment;

/**
 * Strategy Pattern: Payment strategy interface
 */
public interface PaymentStrategy {
    boolean pay(double amount);
    String getPaymentType();
}
