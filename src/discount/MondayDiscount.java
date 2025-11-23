package discount;

public class MondayDiscount implements DiscountStrategy {
  private double fixedDiscount = 5;

  @Override
  public double calculateDiscount(double total) {
    return total * (this.fixedDiscount/100);
  }

  @Override
  public String getDiscountName() {
    return String.format("Monday Special ($%.2f%% off)", fixedDiscount);
  }
}
