package christmas.model;

import java.util.Map;

public class Order {

    private final Map<Menu, Integer> order;

    public Order(Map<Menu, Integer> order) {
        this.order = order;
    }

    public Map<Menu, Integer> getOrder() {
        return Map.copyOf(order);
    }

//    public int totalPrice() {
//        int totalPrice = 0;
//        for (Menu menu : order.keySet()) {
//            int menuPrice = menu.getPrice();
//            Integer amount = order.get(menu);
//            totalPrice += menuPrice * amount;
//        }
//        return totalPrice;
//    }
}
