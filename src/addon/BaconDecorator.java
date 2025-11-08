package addon;

import menuitem.MenuItem;

public class BaconDecorator extends AddOnDecorator {
  private double addonPrice = 1.75;

  public BaconDecorator(MenuItem menuItem) {
    super(menuItem);
  }

  @Override
  public double getPrice() {
    return menuItem.getPrice() + addonPrice;
  }

  @Override
  public String getDescription() {
    return menuItem.getDescription() + String.format(" + Bacon (+$%.2f)", addonPrice);
  }
}
