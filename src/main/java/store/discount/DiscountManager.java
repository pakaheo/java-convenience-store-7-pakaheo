package store.discount;

import java.util.Map;
import store.product.Product;
import store.product.Products;

public class DiscountManager {

    private final Products products;

    public DiscountManager(final Products products) {
        this.products = products;
    }

    public int calculatePromotionDiscount(String productName, int promotionDeducted) {
        return products.getOrderedProduct(productName).stream()
                .mapToInt(product -> product.calculatePromotionDiscount(promotionDeducted))
                .sum();
    }

    public int calculateMemberShipDiscount(Map<Product, Integer> orders) {
        return orders.entrySet().stream()
                .mapToInt(entry -> entry.getKey().calculateMemberShipDiscount(entry.getValue())).sum();
    }
}
