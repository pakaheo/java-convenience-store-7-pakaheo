package store.product;

import java.text.DecimalFormat;
import java.util.Objects;

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
    private final String promotionName;

    public Product(final String name, final int price, int quantity, final String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    private String currentQuantity() {
        if (quantity == 0) {
            return NO_QUANTITY;
        }
        return quantity + QUANTITY_UNIT;
    }

    private String currentPromotion() {
        if (promotionName == null) {
            return NO_WORD;
        }
        return SPACE + promotionName;
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
        return promotionName != null;
    }

    public int currentQuantity(int purchaseCount) {
        return Math.min(this.quantity, purchaseCount);
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
                && Objects.equals(promotionName, product.promotionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, promotionName);
    }

    @Override
    public String toString() {
        return DASH + SPACE + name + SPACE + formalize(price) + PRICE_UNIT + SPACE + currentQuantity()
                + currentPromotion();
    }
}
