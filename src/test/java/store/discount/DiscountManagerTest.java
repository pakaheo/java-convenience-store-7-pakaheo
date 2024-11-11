package store.discount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import store.product.Products;

public class DiscountManagerTest {

    private static final Products PRODUCTS = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1000,10,null"));
    private static final DiscountManager DISCOUNT_MANAGER = new DiscountManager(PRODUCTS);

    @Test
    void 총합_계산() {
        Map<String, Integer> orders = new HashMap<>();
        orders.put("콜라", 3);
        orders.put("사이다", 2);

        assertThat(DISCOUNT_MANAGER.calculateTotal(orders)).isEqualTo(5_000);
    }

//    @Test
//    void 프로모션_할인_금액_계산() {
//        Map<String, Integer> orders = new HashMap<>();
//        orders.put("콜라", 3);
//
//        assertThat(DISCOUNT_MANAGER.calculatePromotionDiscount(orders)).isEqualTo(1_000);
//    }

    @Test
    void 고객이_멤버십을_원하지_않으면_할인을_적용하지_않음() {
        assertThat(DISCOUNT_MANAGER.calculateMemberShipDiscount(1_000, false)).isEqualTo(0);
    }

    @Test
    void 멤버십_할인_금액_계산() {
        assertThat(DISCOUNT_MANAGER.calculateMemberShipDiscount(1_000, true)).isEqualTo(300);
    }

    @Test
    void 멤버십_할인_최대한도는_8000원() {
        assertThat(DISCOUNT_MANAGER.calculateMemberShipDiscount(26_800, true)).isEqualTo(8_000);
    }
}
