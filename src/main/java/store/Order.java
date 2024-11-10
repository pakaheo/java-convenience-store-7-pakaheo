package store;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import store.product.Products;
import view.InputView;
import view.OptionView;
import view.OutputView;

public class Order {

    private final Products products;
    private final Map<String, Integer> orders;
    private final OptionView option = new OptionView(new InputView(), new OutputView());
    private final DiscountManager discountManager;

    public Order(final Products products, final OrderDetails orderDetails, DiscountManager discountManager) {
        this.products = products;
        this.orders = orderDetails.getOrders();
        this.discountManager = discountManager;
    }

    public int progress() {
        // 멤버십 적용할 상품 Map 정의
        Map<String, Integer> membershipProduct = new HashMap<>();

        int payment = 0;
        // 주문 진행
        for (Entry<String, Integer> entry : orders.entrySet()) {
            String productName = entry.getKey();
            int purchaseCount = entry.getValue();

            // 재고를 줄이고 프로모션 상품 차감 개수를 전달 받는다
            int decrease = products.deductInventory(productName, purchaseCount);

            // 상품을 더 받기로 했다면 구매 개수를 늘린다
            purchaseCount = Math.max(purchaseCount, decrease);

            // 총 비용과 프로모션 할인 금액을 구한다
            int total = discountManager.calculateTotal(productName, purchaseCount);
            int promotionDiscount = discountManager.calculatePromotionDiscount(productName, decrease);

            // 결제 금액을 구하고 프로모션 상품이 아니면 멤버십 목록에 추가한다
            payment += total - promotionDiscount;
            if (!products.isPromotionProduct(productName)) {
                membershipProduct.put(productName, purchaseCount);
            }
        }
        // 멤버십 할인을 적용한다
        if (option.membershipOption().equals("Y")) {
            payment -= discountManager.calculateMemberShipDiscount(membershipProduct);
        }

        return payment;
    }
}
