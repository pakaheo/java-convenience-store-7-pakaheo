package store.product;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.Objects;
import store.discount.MemberShip;
import store.promotion.Promotion;

public class Product {

    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final String PRICE_UNIT = "원";
    private static final String NO_WORD = "";

    private final Name name;
    private final Price price;
    private Quantity quantity;
    private final Promotion promotion;

    public Product(final String name, final int price, int quantity, final Promotion promotion) {
        this(new Name(name), new Price(price), new Quantity(quantity), promotion);
    }

    public Product(final Name name, final Price price, Quantity quantity, final Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public int compareQuantity(int purchaseCount) {
        return quantity.calculateMinimum(purchaseCount);
    }

    public boolean hasName(String productName) {
        return name.match(productName);
    }

    public int calculateSubTotal(int purchaseCount) {
        return price.multiply(purchaseCount);
    }

    public boolean isPromotional() {
        return promotion != null;
    }

    public boolean isProgressingPromotion(LocalDate now) {
        return promotion.isActive(now);
    }

    public int deduct(int count) {
        int decrease = quantity.calculateMinimum(count);
        quantity = quantity.reduce(decrease);
        return decrease;
    }

    public int calculatePromotionDiscount(int purchaseCount) {
        if (isPromotional() && isProgressingPromotion(DateTimes.now().toLocalDate())) {
            return price.multiply(promotion.freeGet(purchaseCount));
        }
        return 0;
    }

    public int calculateMemberShipDiscount(int purchaseCount) {
        if (!isPromotional()) {
            return MemberShip.apply(price, purchaseCount);
        }
        return 0;
    }

    public int getPromotionEligibleCount() {
        if (promotion == null) {
            return 0;
        }
        return promotion.getTotal();
    }

    public String getName() {
        return name.toString();
    }

    public int getPrice() {
        return price.getNumber();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Product product)) {
            return false;
        }
        return Objects.equals(name, product.name) && Objects.equals(price, product.price)
                && Objects.equals(quantity, product.quantity) && Objects.equals(promotion,
                product.promotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, promotion);
    }

    @Override
    public String toString() {
        return DASH + SPACE + name.toString() + SPACE + price.formalize() + PRICE_UNIT + SPACE + quantity.toString()
                + displayPromotion();
    }

    private String displayPromotion() {
        if (promotion == null) {
            return NO_WORD;
        }
        return SPACE + promotion.getName();
    }
}
