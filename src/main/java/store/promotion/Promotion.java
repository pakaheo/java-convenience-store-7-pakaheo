package store.promotion;

import java.time.LocalDate;
import java.util.Objects;

public class Promotion {

    private final Name name;
    private final BuyNGetOneFree buyNGetOneFree;
    private final PromotionTerm promotionTerm;

    public Promotion(final String name, final int buy, final int get, final LocalDate startDate,
                     final LocalDate endDate) {
        this(new Name(name), new BuyNGetOneFree(buy, get), new PromotionTerm(startDate, endDate));
    }

    public Promotion(final Name name, final BuyNGetOneFree buyNGetOneFree, final PromotionTerm promotionTerm) {
        this.name = name;
        this.buyNGetOneFree = buyNGetOneFree;
        this.promotionTerm = promotionTerm;
    }

    public boolean isActive(LocalDate now) {
        return promotionTerm.isValid(now);
    }

    public int freeGet() {
        return buyNGetOneFree.freeCount();
    }

    public int getTotal() {
        return buyNGetOneFree.sum();
    }

    public String getName() {
        return name.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Promotion promotion)) {
            return false;
        }
        return Objects.equals(name, promotion.name) && Objects.equals(buyNGetOneFree,
                promotion.buyNGetOneFree) && Objects.equals(promotionTerm, promotion.promotionTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, buyNGetOneFree, promotionTerm);
    }
}
