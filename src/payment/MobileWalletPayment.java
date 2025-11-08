package payment;

public class MobileWalletPayment implements PaymentStrategy {
  private String walletId;

  public MobileWalletPayment(String walletId) {
    this.walletId = walletId;
  }

  @Override
  public boolean pay(double amount) {
    System.out.printf("\nProcessing Mobile Wallet Payment of $%.2f\n", amount);
    System.out.println("Wallet ID: " + walletId);
    System.out.println("Connecting to wallet service...");
    System.out.println("Payment successful!");
    return true;
  }

  @Override
  public String getPaymentType() {
    return "Mobile Wallet";
  }
}
