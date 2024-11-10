package store;

import constants.ErrorMessage;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import store.inventory.PromotionalInventory;
import store.inventory.RegularInventory;
import store.product.Product;
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
        for (Entry<String, Integer> entry : orders.entrySet()) {
            checkExistProduct(entry.getKey());
            checkQuantity(entry.getKey(), entry.getValue());
        }
    }

    public void checkExistProduct(String productName) {
        Product promotionProduct = PromotionalInventory.PROMOTIONAL_INVENTORY.findByName(productName);
        Product regularProduct = RegularInventory.REGULAR_INVENTORY.findByName(productName);

        if (promotionProduct == null && regularProduct == null) {
            throw new IllegalArgumentException(ErrorMessage.NOT_EXISTS_PRODUCT.valueOf());
        }
    }

    public void checkQuantity(String productName, int purchaseCount) {
        if (products.isExceedQuantity(productName, purchaseCount)) {
            throw new IllegalArgumentException(ErrorMessage.EXCEED_QUANTITY.valueOf());
        }
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
