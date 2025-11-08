package discount;

public interface DiscountStrategy {
  double calculateDiscount(double total);

  String getDiscountName();
}
