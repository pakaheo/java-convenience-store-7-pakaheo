package store.discount;

import java.util.Map;
import store.inventory.PromotionalInventory;
import store.product.Product;
import store.product.Products;

public class DiscountManager {

    private static final double DISCOUNT_RATIO = 0.3;
    private static final int DISCOUNT_UPPER_BOUND = 8_000;

    private final Products products;

    public DiscountManager(final Products products) {
        this.products = products;
    }

    public int calculateTotal(Map<String, Integer> orders) {
        return orders.entrySet().stream()
                .mapToInt(entry -> {
                    Product product = products.findByName(entry.getKey());
                    return product.calculateSubTotal(entry.getValue());
                }).sum();
    }

    public int calculatePromotionDiscount(Map<String, Integer> orders) {
        return orders.entrySet().stream()
                .mapToInt(entry -> PromotionalInventory.PROMOTIONAL_INVENTORY.calculatePromotionDiscount(entry.getKey(),
                        entry.getValue()))
                .sum();
    }

    public int calculateMemberShipDiscount(int amount, boolean hasMembership) {
        if (!hasMembership) {
            return 0;
        }
        return Math.min(DISCOUNT_UPPER_BOUND, (int) (amount * DISCOUNT_RATIO));
    }
}
