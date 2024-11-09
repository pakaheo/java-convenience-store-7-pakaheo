package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.DateTimes;
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

    @Test
    void 프로모션은_현재가_프로모션_기간_내일_때만_적용할_수_있다() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Promotion promotion = new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01", formatter),
                LocalDate.parse("2024-12-31", formatter));

        assertThat(promotion.isActive(DateTimes.now().toLocalDate())).isTrue();
        assertThat(promotion.isActive(LocalDate.parse("2023-12-31"))).isFalse();
    }
}
