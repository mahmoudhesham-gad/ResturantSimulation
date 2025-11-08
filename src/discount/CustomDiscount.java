package discount;

public class CustomDiscount implements DiscountStrategy {
  private double fixedDiscount;
  String name;

  public CustomDiscount(double fixedDiscount, String name) {
    this.fixedDiscount = fixedDiscount;
    this.name = name;
  }

  @Override
  public double calculateDiscount(double total) {
    return total / this.fixedDiscount;
  }

  @Override
  public String getDiscountName() {
    return String.format("%s (%.2f%% off)", this.name, this.fixedDiscount);
  }
}
