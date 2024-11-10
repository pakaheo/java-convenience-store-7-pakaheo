package store.product;

import camp.nextstep.edu.missionutils.DateTimes;
import java.text.DecimalFormat;
import java.util.Objects;
import store.MemberShip;
import store.promotion.Promotion;

public class Product {

    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");
    private static final String NO_QUANTITY = "재고 없음";
    private static final String PRICE_UNIT = "원";
    private static final String QUANTITY_UNIT = "개";
    private static final String NO_WORD = "";

    private final String name;
    private final int price;
    private int quantity;
    private final Promotion promotion;

    public Product(final String name, final int price, int quantity, final Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    private String currentQuantity() {
        if (quantity == 0) {
            return NO_QUANTITY;
        }
        return quantity + QUANTITY_UNIT;
    }

    private String currentPromotion() {
        if (promotion == null) {
            return NO_WORD;
        }
        return SPACE + promotion.getName();
    }

    private String formalize(int price) {
        return PRICE_FORMAT.format(price);
    }

    public boolean hasName(String productName) {
        return name.equals(productName);
    }

    public int calculateSubTotal(int purchaseCount) {
        return price * purchaseCount;
    }

    public boolean isPromotional() {
        return promotion != null && promotion.isActive(DateTimes.now().toLocalDate());
    }

    public int currentQuantity(int purchaseCount) {
        return Math.min(this.quantity, purchaseCount);
    }

    public int deduct(int count) {
        int decrease = Math.min(quantity, count);
        quantity -= decrease;
        return decrease;
    }

    public int calculatePromotionDiscount(int purchaseCount) {
        if (isPromotional()) {
            return price * promotion.calculateDiscount(purchaseCount);
        }
        return 0;
    }

    public int calculateMemberShipDiscount(int purchaseCount) {
        if (!isPromotional()) {
            return MemberShip.apply(price, purchaseCount);
        }
        return 0;
    }

    public int neededCount() {
        if (promotion == null) {
            return 0;
        }
        return promotion.getTotal();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Product product)) {
            return false;
        }
        return price == product.price && quantity == product.quantity && Objects.equals(name, product.name)
                && Objects.equals(promotion, product.promotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, promotion);
    }

    @Override
    public String toString() {
        return DASH + SPACE + name + SPACE + formalize(price) + PRICE_UNIT + SPACE + currentQuantity()
                + currentPromotion();
    }
}
