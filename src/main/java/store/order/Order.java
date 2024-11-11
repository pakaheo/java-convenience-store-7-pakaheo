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
            String productName = entry.getKey();
            int purchaseCount = entry.getValue();

            int promotionDiscount = PromotionalInventory.PROMOTIONAL_INVENTORY.calculatePromotionDiscount(productName,
                    purchaseCount);

            Product product = PromotionalInventory.PROMOTIONAL_INVENTORY.findByName(productName);
            if (purchaseCount < promotionDiscount && moreProductOptionService.meet(productName,
                    product.calculateFreeCount(purchaseCount) - purchaseCount)) {
                purchaseCount = product.calculateFreeCount(purchaseCount);
            }
            addFreeProducts(purchaseCount, productName, freeProducts);
        }
        return freeProducts;
    }

    private void addFreeProducts(int promotionDiscount, String productName, Map<String, Integer> freeProducts) {
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
