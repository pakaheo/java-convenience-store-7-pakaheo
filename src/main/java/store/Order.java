package store;

import java.util.Iterator;
import java.util.Map;
import store.product.Products;
import view.OptionView;

public class Order {

    private final Products products;
    private final OrderDetails orderDetails;
    private final OptionView option;

    public Order(final Products products, final OrderDetails orderDetails, OptionView option) {
        this.products = products;
        this.orderDetails = orderDetails;
        this.option = option;
    }

    public int progress() {
        Map<String, Integer> orders = orderDetails.getOrders();
        Iterator<String> iterator = orders.keySet().iterator();

        int payment = 0;

        while (iterator.hasNext()) {
            String productName = iterator.next();
            int purchaseCount = orders.get(productName);
            payment = calculateTotal(productName, purchaseCount) - calculateDiscount(productName, purchaseCount);
        }

        return payment;
    }

    public int calculateTotal(String productName, int purchaseCount) {
        return products.getOrderedProduct(productName).stream()
                .mapToInt(product -> product.calculateSubTotal(purchaseCount))
                .sum();
    }

    public int calculateDiscount(String productName, int purchaseCount) {
        int promotionDeducted = products.deductInventory(productName, purchaseCount);
        if (promotionDeducted > 0) {
            return calculatePromotionDiscount(productName, promotionDeducted);
        }

        if (option.membershipOption().equals("Y")) {
            return calculateMemberShipDiscount(productName, purchaseCount);
        }
        return 0;
    }

    public int calculatePromotionDiscount(String productName, int promotionDeducted) {
        return products.getOrderedProduct(productName).stream()
                .mapToInt(product -> product.calculatePromotionDiscount(promotionDeducted))
                .sum();
    }

    public int calculateMemberShipDiscount(String productName, int purchaseCount) {
        return products.getOrderedProduct(productName).stream()
                .mapToInt(product -> product.calculateMemberShipDiscount(purchaseCount))
                .sum();
    }
}
