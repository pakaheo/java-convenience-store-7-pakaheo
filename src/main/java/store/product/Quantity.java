package store.product;

import java.util.Objects;

public class Quantity {

    private static final String NO_QUANTITY = "재고 없음";
    private static final String QUANTITY_UNIT = "개";

    private int number;

    public Quantity(int number) {
        this.number = number;
    }

    public int calculateMinimum(int purchaseCount) {
        return Math.min(number, purchaseCount);
    }

    public Quantity reduce(int decrease) {
        number -= decrease;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Quantity quantity)) {
            return false;
        }
        return number == quantity.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        if (number == 0) {
            return NO_QUANTITY;
        }
        return number + QUANTITY_UNIT;
    }
}
