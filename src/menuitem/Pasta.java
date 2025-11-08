package menuitem;

public class Pasta extends MenuItem {
  private String sauceType;

  public Pasta(String name, double price, String sauceType) {
    super(name, price, "Pasta");
    this.sauceType = sauceType;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public String getDescription() {
    return String.format("%s with %s - $%.2f", name, sauceType, price);
  }
}
