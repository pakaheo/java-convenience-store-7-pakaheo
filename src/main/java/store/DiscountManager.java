package store;

import java.util.Map;
import store.product.Product;
import store.product.Products;

public class DiscountManager {

    private final Products products;

    public DiscountManager(final Products products) {
        this.products = products;
    }

    public int calculateTotal(String productName, int purchaseCount) {
        Product wow = products.getOrderedProduct(productName)
                .filter(product -> product.hasName(productName))
                .findFirst()
                .orElse(null);

        return wow.calculateSubTotal(purchaseCount);
    }

    public int calculatePromotionDiscount(String productName, int promotionDeducted) {
        return products.getOrderedProduct(productName)
                .mapToInt(product -> product.calculatePromotionDiscount(promotionDeducted))
                .sum();
    }

    public int calculateMemberShipDiscount(Map<String, Integer> orders) {
        return orders.entrySet().stream()
                .mapToInt(entry -> products.getOrderedProduct(entry.getKey())
                        .mapToInt(product -> product.calculateMemberShipDiscount(entry.getValue()))
                        .sum())
                .sum();
    }
}
