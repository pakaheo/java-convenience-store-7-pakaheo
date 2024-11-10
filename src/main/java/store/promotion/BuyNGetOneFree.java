package store.promotion;

import java.util.Objects;

public class BuyNGetOneFree {

    private final int buy;
    private final int get;

    public BuyNGetOneFree(final int buy, final int get) {
        this.buy = buy;
        this.get = get;
    }

    public int discount(int purchaseCount) {
        return purchaseCount / (buy + get) * get;
    }

    public int sum() {
        return buy + get;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BuyNGetOneFree that)) {
            return false;
        }
        return buy == that.buy && get == that.get;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buy, get);
    }
}