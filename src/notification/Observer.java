package notification;

import java.util.List;

public interface Observer {
  void update(int orderId, List<String> items, double total);
}
