package discount;

public class FridayDiscount implements DiscountStrategy {
  private double fixedDiscount = 10.0;

  @Override
  public double calculateDiscount(double total) {
    return total / this.fixedDiscount;
  }

  @Override
  public String getDiscountName() {
    return String.format("Friday Special ($%.2f off)", fixedDiscount);
  }
}
