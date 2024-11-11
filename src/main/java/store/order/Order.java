package store.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import store.discount.DiscountManager;
import store.option.MembershipOptionService;
import store.product.Product;
import store.product.Products;

public class Order {

    private final Products products;
    private final Map<String, Integer> orders;
    private final DiscountManager discountManager;
    private final MembershipOptionService memberOptionService;
    private final Receipt receipt = new Receipt();

    public Order(final Products products, final OrderDetails orderDetails, DiscountManager discountManager,
                 MembershipOptionService memberOptionService) {
        this.products = products;
        this.orders = orderDetails.getOrders();
        this.discountManager = discountManager;
        this.memberOptionService = memberOptionService;
    }

    public void progress() {
        Map<Product, Integer> membershipProduct = new HashMap<>();

        for (Entry<String, Integer> entry : orders.entrySet()) {
            purchaseProduct(entry.getKey(), entry.getValue(), membershipProduct);
        }
        applyMemberShip(membershipProduct);

        receipt.print();
    }

    private void purchaseProduct(String productName, int purchaseCount, Map<Product, Integer> membershipProduct) {
        int adjustedCount = products.calculateOptimizedCount(productName, purchaseCount);
        int promotionDecrease = products.adjustPurchaseCount(productName, adjustedCount);

        saveMembershipProduct(membershipProduct, productName, purchaseCount);
        receipt.addItem(productName, adjustedCount, products.findByName(productName).getPrice());

        calculatePayment(productName, adjustedCount, promotionDecrease);
    }

    private void applyMemberShip(Map<Product, Integer> membershipProduct) {
        if (memberOptionService.meet()) {
            int discount = discountManager.calculateMemberShipDiscount(membershipProduct);
            receipt.applyMembershipDiscount(discount);
        }
    }

    private void saveMembershipProduct(Map<Product, Integer> membershipProduct, String productName, int purchaseCount) {
        Product product = products.findByName(productName);
        if (isRegularProduct(product)) {
            membershipProduct.put(product, purchaseCount);
        }
    }

    private boolean isRegularProduct(Product product) {
        return !products.isPromotionProduct(product);
    }

    private void calculatePayment(String productName, int purchaseCount, int promotionDecrease) {
        int promotionDiscount = discountManager.calculatePromotionDiscount(productName, promotionDecrease);
        receipt.applyPromotionDiscount(promotionDiscount);
        if (promotionDiscount > 0) {
            receipt.addFreeItem(productName,
                    promotionDiscount / products.findByName(productName).getPrice());
        }
    }
}
