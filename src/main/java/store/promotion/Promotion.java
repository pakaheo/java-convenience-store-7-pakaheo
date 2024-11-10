package store.promotion;

import java.time.LocalDate;
import java.util.Objects;

public class Promotion {

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(final String name, final int buy, final int get, final LocalDate startDate,
                     final LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isActive(LocalDate now) {
        return now.isAfter(startDate) && now.isBefore(endDate);
    }

    public int calculateDiscount(int purchaseCount) {
        return purchaseCount / (buy + get) * get;
    }

    public int getTotal() {
        return buy + get;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Promotion promotion)) {
            return false;
        }
        return buy == promotion.buy && get == promotion.get && Objects.equals(name, promotion.name)
                && Objects.equals(startDate, promotion.startDate) && Objects.equals(endDate,
                promotion.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, buy, get, startDate, endDate);
    }
}
