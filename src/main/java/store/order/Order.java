package store.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import store.MemberOptionService;
import store.Receipt;
import store.discount.DiscountManager;
import store.product.Product;
import store.product.Products;

public class Order {

    private final Products products;
    private final Map<String, Integer> orders;
    private final DiscountManager discountManager;
    private final MemberOptionService memberOptionService;
    private final Receipt receipt = new Receipt();

    public Order(final Products products, final OrderDetails orderDetails, DiscountManager discountManager,
                 MemberOptionService memberOptionService) {
        this.products = products;
        this.orders = orderDetails.getOrders();
        this.discountManager = discountManager;
        this.memberOptionService = memberOptionService;
    }

    public void progress() {
        Map<Product, Integer> membershipProduct = new HashMap<>();
        int payment = 0;

        for (Entry<String, Integer> entry : orders.entrySet()) {
            payment += purchaseProduct(entry.getKey(), entry.getValue(), membershipProduct);
        }

        payment = applyMemberShip(payment, membershipProduct);

        receipt.print();
    }

    private int purchaseProduct(String productName, int purchaseCount, Map<Product, Integer> membershipProduct) {
        purchaseCount = products.optimize(productName, purchaseCount);
        int promotionDecrease = getPromotionDecrease(productName, purchaseCount);
        saveMembershipProduct(membershipProduct, productName, purchaseCount);
        receipt.addItem(productName, purchaseCount, products.findByName(productName).getPrice());
        return calculatePayment(productName, purchaseCount, promotionDecrease);
    }

    private int applyMemberShip(int payment, Map<Product, Integer> membershipProduct) {
        if (memberOptionService.noParameter()) {
            int discount = discountManager.calculateMemberShipDiscount(membershipProduct);
            receipt.applyMembershipDiscount(discount);
            return payment - discount;
        }
        return payment;
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

    private int calculatePayment(String productName, int purchaseCount, int promotionDecrease) {
        int total = discountManager.calculateTotal(productName, purchaseCount);
        int promotionDiscount = discountManager.calculatePromotionDiscount(productName, promotionDecrease);
        if (promotionDiscount > 0) {
            receipt.addFreeItem(productName,
                    promotionDiscount / products.findByName(productName).getPrice());
        }
        receipt.applyPromotionDiscount(promotionDiscount);
        return total - promotionDiscount;
    }

    private int getPromotionDecrease(String productName, int purchaseCount) {
        return products.deductInventory(productName, purchaseCount);
    }
}
