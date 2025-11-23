package menu;

import menuitem.MenuItem;

public class MenuBuilder {
  Menu menu;

  public MenuBuilder(String name) {
    menu = new Menu(name);
  }

  public Menu buildMenu() {
    return menu;
  };

  public void addItem(MenuItem item) {
    this.menu.addItem(item);
  };

  public void removeItem(MenuItem item) {
    this.menu.removeItem(item);
  };
}
