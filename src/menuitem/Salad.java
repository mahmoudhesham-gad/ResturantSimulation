package menuitem;

public class Salad extends MenuItem {

  public Salad(String name, double price) {
    super(name, price, "Salad");
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
    return String.format("%s - $%.2f", name, price);
  }
}
