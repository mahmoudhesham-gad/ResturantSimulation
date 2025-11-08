package com.restaurant.payment;

/**
 * Concrete Strategy: Cash payment
 */
public class CashPayment implements PaymentStrategy {

    @Override
    public boolean pay(double amount) {
        System.out.printf("\nProcessing Cash Payment of $%.2f\n", amount);
        System.out.println("Payment successful! Please collect your change.");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "Cash";
    }
}
