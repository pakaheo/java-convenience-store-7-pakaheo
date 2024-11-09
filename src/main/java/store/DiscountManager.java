package store;

import java.util.Iterator;
import java.util.Map;
import store.product.Products;

public class DiscountManager {

    private final Products products;
    private final OrderDetails orderDetails;

    public DiscountManager(final Products products, final OrderDetails orderDetails) {
        this.products = products;
        this.orderDetails = orderDetails;
    }

    public int calculateTotal() {
        int total = 0;
        Map<String, Integer> orders = orderDetails.getOrders();
        Iterator<String> productNames = orders.keySet().iterator();

        while (productNames.hasNext()) {
            String productName = productNames.next();
            total += products.findByName(productName).calculateSubTotal(orders.get(productName));
        }

        return total;
    }
}
