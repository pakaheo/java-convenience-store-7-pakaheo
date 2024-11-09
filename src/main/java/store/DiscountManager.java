package store;

import java.util.Map;
import store.product.Products;

public class DiscountManager {

    private final Products products;
    private final Map<String, Integer> orders;

    public DiscountManager(final Products products, final OrderDetails orderDetails) {
        this.products = products;
        this.orders = orderDetails.getOrders();
    }

    public int calculateTotal() {
        return orders.entrySet().stream()
                .mapToInt(entry -> products
                        .findProducts(entry.getKey())
                        .calculateSubTotal(entry.getValue()))
                .sum();
    }
}
