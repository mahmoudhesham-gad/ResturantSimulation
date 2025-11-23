package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menuitem.MenuItem;

public class Menu {
  Map<String, List<MenuItem>> itemsByCategory;
  String name;

  Menu(String name) {
    this.name = name;
    this.itemsByCategory = new HashMap<>();
  }

  public void addItem(MenuItem item) {
    List<MenuItem> items = itemsByCategory.get(item.getCategory());
    if (items == null) {
      items = new ArrayList<>();
      items.add(item);
      itemsByCategory.put(item.getCategory(), items);
    } else {
      items.add(item);
    }
  }

  public void removeItem(MenuItem item) {
    List<MenuItem> items = itemsByCategory.get(item.getCategory());
    if (items != null) {
      items.remove(item);
      if (items.isEmpty()) {
        itemsByCategory.remove(item.getCategory());
      }
    }
  }

  public void printMenu() {
    System.out.println("Menu: " + this.name);
    for (String category : itemsByCategory.keySet()) {
      System.out.println("\n-- " + category + " --");
      List<MenuItem> items = itemsByCategory.get(category);
      for (MenuItem item : items) {
        System.out.println(item.getName() + " - $" + String.format("%.2f", item.getPrice()));
      }
    }
  }

  public String getName() {
    return this.name;
  }

  public Map<String, List<MenuItem>> getItemsByCategory() {
    return this.itemsByCategory;
  }
}
