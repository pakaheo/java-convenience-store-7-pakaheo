package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PromotionParserTest {

    @Test
    void 프로모션_리스트_생성() {
        List<String> promotionContents = List.of("탄산2+1,2,1,2024-01-01,2024-12-31");

        assertThat(PromotionParser.parse(promotionContents)).hasSize(1);
        assertThat(PromotionParser.parse(promotionContents).getFirst()).isInstanceOf(Promotion.class);
    }
}
