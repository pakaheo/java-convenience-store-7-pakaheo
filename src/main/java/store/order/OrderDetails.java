package store.order;

import constants.ErrorMessage;
import java.util.Map;
import java.util.Objects;
import store.product.Products;

public class OrderDetails {

    private final Map<String, Integer> orders;
    private final Products products;

    public OrderDetails(String input, Products products) {
        this.orders = OrderDetailsParser.parse(input);
        this.products = products;
        validateInput(orders);
    }

    private void validateInput(Map<String, Integer> orders) {
        orders.forEach(this::checkExist);
        orders.forEach(this::checkQuantity);
    }

    private void checkExist(String productName, int purchaseCount) {
        products.findByName(productName);
    }

    public void checkQuantity(String productName, int purchaseCount) {
        if (isExceedQuantity(productName, purchaseCount)) {
            throw new IllegalArgumentException(ErrorMessage.EXCEED_QUANTITY.valueOf());
        }
    }

    private boolean isExceedQuantity(String productName, int purchaseCount) {
        return calculateActualQuantity(productName, purchaseCount) < purchaseCount;
    }

    private int calculateActualQuantity(String productName, int purchaseCount) {
        return products.getOrderedProduct(productName).stream()
                .mapToInt(product -> product.compareQuantity(purchaseCount))
                .sum();
    }

    public Map<String, Integer> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof OrderDetails that)) {
            return false;
        }
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }
}
