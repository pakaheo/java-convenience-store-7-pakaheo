package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PromotionTermTest {

    @Test
    void 프로모션_기간_생성() {
        PromotionTerm promotionTerm = new PromotionTerm(LocalDate.parse("2024-01-01")
                , LocalDate.parse("2024-12-31"));

        assertThat(promotionTerm).isEqualTo(
                new PromotionTerm(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));
    }

    @ParameterizedTest
    @CsvSource(value = {"2024-02-01:true", "2023-12-31:false"}, delimiter = ':')
    void 유효한_프로모션인지_확인(String date, boolean expected) {
        PromotionTerm promotionTerm = new PromotionTerm(LocalDate.parse("2024-01-01")
                , LocalDate.parse("2024-12-31"));

        assertThat(promotionTerm.isValid(LocalDate.parse(date))).isEqualTo(expected);
    }
}
