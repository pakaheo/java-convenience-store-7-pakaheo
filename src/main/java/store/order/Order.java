package store.order;

import java.util.HashMap;
import java.util.Map;
import store.discount.DiscountManager;
import store.inventory.PromotionalInventory;
import store.inventory.RegularInventory;
import store.option.MoreProductOptionService;
import store.product.Product;
import store.product.Products;

public class Order {

    private final Map<String, Integer> orders;
    private final Products products;
    private final DiscountManager discountManager;
    private final MoreProductOptionService moreProductOptionService;

    public Order(final OrderDetails orderDetails, Products products, DiscountManager discountManager,
                 MoreProductOptionService moreProductOptionService) {
        this.orders = orderDetails.getOrders();
        this.products = products;
        this.discountManager = discountManager;
        this.moreProductOptionService = moreProductOptionService;
    }

    public Receipt progress(boolean hasMembership) {
        Map<String, Integer> freeProducts = calculateFreeProducts(orders);
        int total = discountManager.calculateTotal(orders);
        int promotionDiscount = discountManager.calculatePromotionDiscount(orders);
        int membershipDiscount = discountManager.calculateMemberShipDiscount(total - promotionDiscount, hasMembership);

        updateInventory(orders, freeProducts);

        return new Receipt(convertToProduct(orders), convertToProduct(freeProducts), total, promotionDiscount,
                membershipDiscount);
    }

    private Map<String, Integer> calculateFreeProducts(Map<String, Integer> orders) {
        Map<String, Integer> freeProducts = new HashMap<>();
        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            if (PromotionalInventory.PROMOTIONAL_INVENTORY.findByName(entry.getKey()) == null) {
                return freeProducts;
            }
            moreProductCase(entry.getKey(), entry.getValue());
            addFreeProducts(entry.getKey(), entry.getValue(), freeProducts);
        }
        return freeProducts;
    }

    private void moreProductCase(String productName, int purchaseCount) {
        Product product = PromotionalInventory.PROMOTIONAL_INVENTORY.findByName(productName);
        int requiredForPromotion = product.getRequiredPromotion();

        if (purchaseCount < requiredForPromotion) {
            int additionalCount = requiredForPromotion - purchaseCount;
            if (moreProductOptionService.meet(productName, additionalCount)) {
                purchaseCount = requiredForPromotion;
                orders.put(productName, purchaseCount);
            }
        }
    }

    private void addFreeProducts(String productName, int purchaseCount, Map<String, Integer> freeProducts) {
        int promotionDiscount = PromotionalInventory.PROMOTIONAL_INVENTORY.calculatePromotionDiscount(productName,
                purchaseCount);
        if (promotionDiscount > 0) {
            Product product = PromotionalInventory.PROMOTIONAL_INVENTORY.findByName(productName);
            freeProducts.put(productName, promotionDiscount / product.getPrice());
        }
    }

    private void updateInventory(Map<String, Integer> orders, Map<String, Integer> freeProducts) {
        orders.forEach(RegularInventory.REGULAR_INVENTORY::deduct);
        freeProducts.forEach(PromotionalInventory.PROMOTIONAL_INVENTORY::deduct);
    }

    private Map<Product, Integer> convertToProduct(Map<String, Integer> orders) {
        Map<Product, Integer> items = new HashMap<>();
        orders.forEach((key, value) -> items.put(products.findByName(key), value));
        return items;
    }
}
