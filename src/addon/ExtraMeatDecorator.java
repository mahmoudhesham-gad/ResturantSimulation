package addon;

import menuitem.MenuItem;

public class ExtraMeatDecorator extends AddOnDecorator {
  private double addonPrice = 2.50;

  public ExtraMeatDecorator(MenuItem menuItem) {
    super(menuItem);
  }

  @Override
  public double getPrice() {
    return menuItem.getPrice() + addonPrice;
  }

  @Override
  public String getDescription() {
    return menuItem.getDescription() + String.format(" + Extra Meat (+$%.2f)", addonPrice);
  }
}
