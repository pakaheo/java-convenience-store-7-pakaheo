package store.promotion;

import java.time.LocalDate;
import java.util.Objects;

public class PromotionTerm {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public PromotionTerm(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isValid(LocalDate now) {
        return now.isAfter(startDate) && now.isBefore(endDate);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PromotionTerm that)) {
            return false;
        }
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
