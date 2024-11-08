package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PromotionsTest {

    @Test
    void 프로모션들_생성() {
        List<String> productGroup = List.of("탄산2+1,2,1,2024-01-01,2024-12-31");

        assertThat(new Promotions(productGroup)).isEqualTo(new Promotions(productGroup));
    }
}
