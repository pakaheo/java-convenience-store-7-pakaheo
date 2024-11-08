package store.promotion;

import java.util.List;
import java.util.Objects;

public class Promotions {

    private final List<Promotion> promotionGroup;

    public Promotions(final List<String> promotionContents) {
        this.promotionGroup = PromotionParser.parse(promotionContents);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Promotions that)) {
            return false;
        }
        return Objects.equals(promotionGroup, that.promotionGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionGroup);
    }
}
