package store.product;

import java.text.DecimalFormat;
import java.util.Objects;

public class Price {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");

    private final int number;

    public Price(final int number) {
        this.number = number;
    }

    public int multiply(int purchaseCount) {
        return number * purchaseCount;
    }

    public String formalize() {
        return PRICE_FORMAT.format(number);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Price price)) {
            return false;
        }
        return number == price.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getNumber() {
        return number;
    }
}
