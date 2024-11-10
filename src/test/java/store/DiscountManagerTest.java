package store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.product.Products;

public class DiscountManagerTest {

    private static final Products PRODUCTS = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1000,5,null"));
    private static final DiscountManager DISCOUNT_MANAGER = new DiscountManager(PRODUCTS);

    @Test
    void 총비용_계산() {
        assertThat(DISCOUNT_MANAGER.calculateTotal("콜라", 5)).isEqualTo(5_000);
    }

    @Test
    void 프로모션_할인_금액_계산() {
        assertThat(DISCOUNT_MANAGER.calculatePromotionDiscount("콜라", 3)).isEqualTo(1_000);
    }
}
