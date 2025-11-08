package payment;

public class CreditCardPayment implements PaymentStrategy {
  private String cardNumber;
  private String cvv;

  public CreditCardPayment(String cardNumber, String cvv) {
    this.cardNumber = cardNumber.substring(cardNumber.length() - 4); // Store only last 4 digits
    this.cvv = cvv;
  }

  @Override
  public boolean pay(double amount) {
    System.out.printf("\nProcessing Credit Card Payment of $%.2f\n", amount);
    System.out.println("Card ending in: ****" + cardNumber);
    System.out.println("Authorizing...");
    System.out.println("Payment successful!");
    return true;
  }

  @Override
  public String getPaymentType() {
    return "Credit Card";
  }
}
