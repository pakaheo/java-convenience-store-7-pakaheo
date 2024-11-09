public class MemberShip {

    private static final double DISCOUNT_RATIO = 0.3;
    private static final int DISCOUNT_UPPER_BOUND = 8_000;

    public int apply(int price, int purchaseCount) {
        return Math.min(DISCOUNT_UPPER_BOUND, (int) (price * purchaseCount * DISCOUNT_RATIO));
    }
}
