package store.discount;

import store.product.Price;

public class MemberShip {

    private static final double DISCOUNT_RATIO = 0.3;
    private static final int DISCOUNT_UPPER_BOUND = 8_000;

    public static int apply(Price price, int purchaseCount) {
        return Math.min(DISCOUNT_UPPER_BOUND, (int) (price.multiply(purchaseCount) * DISCOUNT_RATIO));
    }
}
