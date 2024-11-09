package constants;

import java.time.LocalDate;
import store.promotion.Promotion;

public class Constants {

    public static final Promotion TWO_PLUS_ONE_PROMOTION = new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31"));
}
