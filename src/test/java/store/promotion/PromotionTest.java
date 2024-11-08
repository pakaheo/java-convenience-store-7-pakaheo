package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class PromotionTest {

    @Test
    void 프로모션_생성() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Promotion promotion = new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01", formatter),
                LocalDate.parse("2024-12-31", formatter));

        assertThat(promotion).isEqualTo(
                new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01", formatter),
                        LocalDate.parse("2024-12-31", formatter)));
    }
}
